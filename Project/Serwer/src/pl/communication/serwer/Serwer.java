
package pl.communication.serwer;
import pl.communication.serializableMessage.AuthMessage;
import pl.communication.serializableMessage.Message;
import pl.communication.serializableMessage.MoveMessage;
import pl.communication.serializableMessage.TextMessage;
import pl.communication.serializableMessage.ReturnMessage;
import pl.communication.serializableMessage.StatusMessage;
import pl.communication.database.DatabaseConnector;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import pl.communication.writerReader.Streamer;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * Klasa Serwera. Przyjmuje polaczenia od klientów. Pracuje na watkach.
 * Ma tez modul nasluchujacy na standardowym wejsciu 
 * pozwalajacy wywolac kilka akcji z poziomu serwera
 * @author Bartosz Surma
 */

public class Serwer {
private ConcurrentHashMap  <String , MyConnect > myMap = new ConcurrentHashMap <String,MyConnect>();
private ConcurrentHashMap  <Integer , GameRoom > myGameRoomMap = new ConcurrentHashMap <Integer,GameRoom>();
private int port;
private boolean isRunning;
private static int id = 1;
private ServerSocket sock;
private String loginy [] = new String[2];
private  DatabaseConnector dbc;
/**
 * Jedyny konstruktor. Tworzy objekt serwera na podstawie pliku serwer.properties
*/
    public Serwer() {
        Properties props = new Properties();
        InputStream in;
        try 
        {
            in = new FileInputStream("serwer.properties");
            props.load(in);
            int x =Integer.parseInt(props.getProperty("srv.port"));
            this.port = x;
            isRunning = true;
            this.dbc = new DatabaseConnector();
            GameRoom.setStartId(dbc.getNextGameId());
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Nie mozna uruchomic serwera");
            System.exit(1);
            } 
        catch (IOException ex) {
            Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }


    /**
     *  Uruchamia serwer. Po wywolaniu serwer pracuje w ciaglym nasluchu.
     *  Moze byc przerwana poprzez komende 'exit'
     */
    public void Lunch()
    {
        try {
            System.out.println("MyServer v 0.8");
            MyScanner scanner = new MyScanner();
            scanner.start();
            sock = new ServerSocket(port);
            while (isRunning)
            {
                Socket socket = new Socket();
                try
                {
                   socket = sock.accept(); 
                }
                catch(IOException e)
                {
                    break;
                }
                System.out.println("<CLIENT CONNECTED>");
                MyConnect mc = new MyConnect(socket,this);                
                mc.start();
            }   
        } catch (IOException ex) {
            Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("I'm closing");
    }

    /**
     * Rozlacza klienta od serwera. 
     * @param key Login klienta ktory ma zostac odlaczony od serwera
     * @return Komunikat odnosnie rozlaczenia klienta.  
     */
    public String disconnectClient(String key)
    {
        
        if (myMap.get(key) != null ) 
        {
            System.out.println(key + " Disconnecting");
            myMap.get(key).disconnect();
            myMap.remove(key);
            return ("User "+key+" disconnected");
        }

    return "No user identified by "+key;
    }

    /**
     *  Zamyka serwer. Rozlacza najpierw wszystkich klientow, usuwa Pokoje i zamyka sockety.
     *  
     */
    public void close()
    {
        isRunning = false;
        Iterator <Integer> is = myGameRoomMap.keySet().iterator();
        while (is.hasNext())
        {
            Integer key  =  is.next();
            closeGameRoom(key);
        }
        Iterator <String> i = myMap.keySet().iterator();
        while (i.hasNext())
        {
            String key  = i.next();
            disconnectClient(key);
        }
        myMap.clear();
        if (!sock.isClosed())
        try {
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Zamyka pokoj gry. Wywolywana jest w moemncie rozlaczenia obu klientow z pokoju, lub w przypadku zamykania serwera.
     * @param id Id pokoju gier, ktory ma zostac zamkniety a jego zasoby zwolnione.
     */
    public void closeGameRoom(int id)
        {
            if (myGameRoomMap!=null){
                if (myGameRoomMap.get(id)!=null)
                {
                    myGameRoomMap.remove(id);
                    System.out.println("<USUWAM POKOJ>");
                }
            }
        }

    /**
     *  Funkcja main - uruchamia serwer. 
     */
    public static void main(String args[]) {
           Serwer srv = new Serwer();
           srv.Lunch();
    }
    /**
     * Watek - miniserwer. Jeden obiekt tej klasy obsługuje w nowym watku jednego klienta
     */
    class MyConnect extends Thread{
        private Socket mySocket ; 
        private Streamer streamer;
        private boolean isRunning;
        private Message message;
        private AuthMessage authMessage;
        private TextMessage textMessage;
        private MoveMessage moveMessage;
        private String username;
        private String password;
        private GameRoom gm;
        private StatusMessage statusMessage;
        private Serwer srv;
        public MyConnect(Socket mySocket,Serwer srv)
        {
            try {
                this.mySocket = mySocket;    
                isRunning = true;
                streamer = new Streamer(mySocket.getOutputStream(),mySocket.getInputStream());
                this.srv = srv;
            } catch (IOException ex) {
                Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        public Streamer getStreamer()
        {
            return this.streamer;
        }
        public Socket getSocket()
        {
            return this.mySocket;
        }
        public void tryToSit()
        {
                boolean temp = false;
               
                Iterator <Integer> iTemp ;
                iTemp = myGameRoomMap.keySet().iterator();
                if (iTemp.hasNext() && temp==false)
                {
                     
                    Integer key = iTemp.next();
                    if (myGameRoomMap.get(key).isSpace())
                    {
                        gm = myGameRoomMap.get(key);
                        gm.sit(username,streamer);
                         myMap.put(username, this);
                        temp = true;     
                    }
                    else
                    {
                        String [] names = new String [2];
                        names = myGameRoomMap.get(key).getPlayers();
                        if ((names[0].equals(username)) || (names[1].equals(username)))
                        {
                            gm = myGameRoomMap.get(key);
                            gm.reSit(username, streamer);
                            myMap.put(username, this);
                            temp = true;
                        }
                    }
                }
                if (temp==false)
                {
                    this.gm =new GameRoom(srv,dbc);
                    gm.sit(username,streamer);
                   // gm.setRound(id);
                    myGameRoomMap.put(gm.getId(),gm);
                    myMap.put(username, this);
                }
                
        }
        public void disconnect()
        {
            this.isRunning = false;
            
            try {
                writeMessage(new ReturnMessage(ReturnMessage.CARD2));
                gm.getUp(username);
                streamer.closeStream();
                this.mySocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void writeMessage(Message msg)
        {
            if (mySocket.isConnected())
            {
                streamer.writeMessage(msg);
            }
        }
        public void writeMessageTo(String idOp,Message msg)
        {
            if (myMap.get(idOp).getSocket().isConnected())
            {
                streamer.writeMessageTo(myMap.get(idOp).getStreamer(), msg);
            }
        }
        public boolean Authorize()
        {
            if (authMessage.getAction()==AuthMessage.LOGOUT)
                {
                    writeMessage(new ReturnMessage(ReturnMessage.CARD2));
                    return false;   
                }   
            return true;
        }
        public void propagateChatMessage()
        {
            textMessage = (TextMessage) message;
            System.out.println("<"+username+">"+textMessage.getMessage());
            if (gm.getEnemyUsername(username)!=null)
            {
                writeMessageTo(gm.getEnemyUsername(username),new TextMessage("<"+username+">"+textMessage.getMessage()));
            }  
        }
        public void propagateMove()
        {
            if (message instanceof MoveMessage) System.out.println(message);
            moveMessage = (MoveMessage) message;
            String s = gm.getEnemyUsername(username);
            if (s!=null)
            {
                gm.changeTurn(username);
                dbc.makeMove(username,gm.getEnemyUsername(username),gm.getId(),moveMessage.getChess(), moveMessage.getPositionToMove(),DatabaseConnector.KILL);
                writeMessageTo(gm.getEnemyUsername(username), moveMessage);
            }
        }
        public void propagateStatus()
        {
            statusMessage = (StatusMessage) message;
             if (gm.getEnemyUsername(username)!=null)
            {
                if (statusMessage.getStatus()==StatusMessage.DRAW)
                {
                   writeMessageTo(gm.getEnemyUsername(username),new StatusMessage(StatusMessage.DRAW,username));
                   writeMessage(new StatusMessage(StatusMessage.DRAW,gm.getEnemyUsername(username))); 
                }
                else if (statusMessage.getStatus() == StatusMessage.YOUWIN)
                {
                   writeMessageTo(gm.getEnemyUsername(username),new StatusMessage(StatusMessage.YOULOSE,username));
                   writeMessage(new StatusMessage(StatusMessage.YOUWIN,gm.getEnemyUsername(username)));
                }
                else{
                   writeMessageTo(gm.getEnemyUsername(username),new StatusMessage(StatusMessage.YOUWIN,username));
                   writeMessage(new StatusMessage(StatusMessage.YOULOSE,gm.getEnemyUsername(username)));
                }
                
            }
        }
        public boolean readMessage(int i)
        {
               try
               {
                   message =  streamer.readMessage();
               }
               catch (IOException e)
               {
                    return false;    
               } 
               catch (ClassNotFoundException ex) 
               {
                    Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
               }
               if (message== null)
               {
                   disconnectClient(this.username);
                   return false;
               }
               switch (message.getType())
               {
                    case Message.MESAUTH:
                         authMessage = (AuthMessage) message;
                         if (authMessage.getAction()==AuthMessage.LOGIN || authMessage.getAction()==AuthMessage.LOGOUT)
                         {
                            if(!Authorize()) return false;
                         }
                         else
                         {
                            if(authMessage.getAction()==AuthMessage.GETUP)
                            {
                                gm.getUp(username);
                            }
                            if (authMessage.getAction()==AuthMessage.SITDOWN)
                            {
                                writeMessage(new ReturnMessage(ReturnMessage.CARD3));
                                tryToSit();
                            }
                         }
                        break;
                    case Message.MESTEXT:
                        propagateChatMessage();
                        break;
                    case Message.MESMOVE:
                        propagateMove();
                        break;
                    case Message.MESSTATUS:
                        propagateStatus();
                        break;
                       
               }  
               return true;
        }
        public boolean tryToLogin()
        {
            username = authMessage.getUsername();
            password = authMessage.getPassword();
            if (authMessage.getAction()==AuthMessage.LOGIN)
            {
                System.out.println("<LOGGING USER>: "+ username);
                if (myMap.get(username)==null)
                    if(dbc.loginUser(username, password))
                    {
                        return true;
                    }
            }
            else if (authMessage.getAction()==AuthMessage.REGISTER)
            {
               if (myMap.get(username)==null) 
                if (dbc.registerNewUser(username, password))
                {
                    return true;
                }
            }
            return false;
        }
        public void run() {
            if (!readMessage(0)) isRunning = false;
            else if(!tryToLogin())
            {
                isRunning  = false;
                System.out.println("<CONNECTION REFUSED>");
                writeMessage(new ReturnMessage(ReturnMessage.BADRET,"refused"));
            }
            else
            {
                System.out.println("<CONNECTION ACCEPTED>");
                writeMessage(new ReturnMessage(ReturnMessage.GOODRET,"accepted"));
                this.username = authMessage.getUsername();
            }   
            
            while (isRunning)
            {
               if(!readMessage(0)){
                    disconnectClient(username);
                    break;
               }
            } 
           System.out.println("<DISCONNECTING>");
        }
    }
    class MyScanner extends Thread{
        public void run()
        {
            Scanner in = new Scanner(System.in);
            boolean p = true;
            while(p)
            {
                String line = in.nextLine();
                if (line.equalsIgnoreCase("exit")) 
                {
                    close();
                    p=false;
                }
                else if (line.equalsIgnoreCase("disconnect all"))
                {
                    Iterator <String> i = myMap.keySet().iterator();
                    while(i.hasNext())
                    {
                        System.out.println(disconnectClient(i.next()));
                    }
                }
                else if (line.contains("disconnect"))
                {
                    if (line.length()>"disconnect".length()+1)
                    {
                        String user = line.substring("disconnect".length()+1, line.length());
                        System.out.println(disconnectClient(user));
                    }
                }
                else if (line.equalsIgnoreCase("list"))
                {
                    Iterator <String> i = myMap.keySet().iterator();
                    System.out.println("--List of users--");
                    while(i.hasNext())
                    {
                        System.out.println(i.next());
                    }
                    System.out.println("--------------------");
                }        
                
            }
        }
    }
}

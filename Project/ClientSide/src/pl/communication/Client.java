
package pl.communication;

import pl.communication.serializableMessage.MoveMessage;
import pl.communication.serializableMessage.ReturnMessage;
import pl.communication.serializableMessage.TextMessage;
import pl.communication.serializableMessage.ConfigMessage;
import pl.communication.serializableMessage.StatusMessage;
import pl.communication.serializableMessage.AuthMessage;
import pl.communication.serializableMessage.Message;
import pl.gui.NewJFrame;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.logic.management.Manager;

/**
 * Klasa obsługujaca połaczenie sieciowe z serwerem.
 * @author Bartosz Surma
 */
public class Client{ 
   private ObjectOutputStream out;
   private ObjectInputStream in;
   private int port;
   private String username;
   private String serwer;
   private Socket mySocket;
   private NewJFrame  myFrame;
   private MyListener myListener;
   private boolean isConnected;
   private String password;
   private Manager manager;
   /**
    * Konstruktor
    * @param j Obiekt NewJFrame, który jest obiektem nadrzędnym wobec tego obiekta.
    */
   public Client(NewJFrame j)
   {
       myFrame = j;
       isConnected = false;    
   }
   /**
    * Inicializuje obiekt podanymi wartościami, oraz wartosciami pobranymi z pliku client.properties
    * @param username  Nazwa użytkownika
    * @param password  Haslo użytkownika
    * @param manager   Obiekt managera, który pośredniczy w dystrybucji logiki
    */
   public void init(String username,String password,Manager manager)
   {
       try {
           Properties props = new Properties();
           InputStream in;
           in = new FileInputStream("client.properties");
           props.load(in);
           this.port = Integer.parseInt(props.getProperty("srv.port"));
           this.serwer = props.getProperty("srv.addr");
           this.username = username;
           this.password = password;
           this.manager = manager;
           tryToConnect();
       } catch (FileNotFoundException ex) {
           System.out.println("Nie mozna załadować ustawien");
           System.exit(1);
       } catch (IOException ex) {
           System.out.println("Nie mozna załadować ustawien");
           System.exit(1);
       }
   }
   /**
    * Próbuje połaczyć sie z serwerem.
    * @return true jeśli uda się połaczyc z serwerem, lub false jeśli nie.
    */
   public boolean tryToConnect()
   {
        try {
            mySocket = null;
            mySocket = new Socket(serwer,port);
            this.out = new ObjectOutputStream(mySocket.getOutputStream());
            this.in = new ObjectInputStream(mySocket.getInputStream());
            isConnected = true;
            if (isConnected())start();
            
       } catch (IOException ex) {
          isConnected = false;
          return false;
       }
       
       return true;
       
   }
   /**
    * Sprawdza polaczenie z serwerem
    * @return true jeśli jest połaczenie z serwerem, false jeśli nie
    */
   public boolean isConnected()
   {
       return isConnected;
   }
   /**
    * Ustawia nazwe użytkownika dla połaczenia
    * @param s Nazwa użytkownika
    */
   public void setUsername(String s)
   {
       this.username = s;
   }
   /**
    * Zwraca nazwe użytkownika
    * @return Nazwa użytkownika 
    */
   public String getUsername()
   {
       return this.username;
   }
   /**
    * Zwraca hasło użytkownika
    * @return Hasło użytkownika
    */
   public String getPassword()
   {
       return this.password;
   }
   /**
    * Ustawia hasło użytkownika
    * @param s Hasło użytkownika
    */
   public void setPassword(String s)
   {
       this.password = s;
   }
   /**
    * Wysyła wiadomość do strumienia wyjściowego
    * @param message Jeden z obiektów z wiadomości serializowanych.
    */
    public void writeMessage(Message message)
    {
       boolean connected = true;
       if (isConnected())
        {
               try 
                {
                    out.writeObject(message);
                } 
                catch (IOException ex) 
                {
                    isConnected = false;
                }
       }
       else
       {
           tryToConnect();
       }
    }
    /**
     * Podejmuje próbe autoryzacji połaczenia  z serwerem.
     * @return true jeśli uda się polaczyc, false jesli nie.
     */
   public boolean login()
   {

                boolean connected = true;
                if (!isConnected())
                {
                    connected = tryToConnect();
                }
                if (connected) {
                     AuthMessage m = new AuthMessage(AuthMessage.LOGIN,this.username,this.password);
                     writeMessage(m);
                     return true;
                }
              return false;
   }
   /**
    * Próbuje zarejestrować użytkownika na serwerze i zalogować.
    * @return Zwraca true jeśli się uda, lub false jesli nie.
    */
   public boolean register()
   {
       boolean connected = true;
       if (!isConnected())
       {
           connected = tryToConnect();
       }
       if (connected) {
            AuthMessage m = new AuthMessage(AuthMessage.REGISTER,this.username,this.password);
            writeMessage(m);
            return true;
       }
    return false;
   }
   /**
    * Uruchamia moduł nasłuchujacy na połaczeniu z serwerem.
    */
   public void start()         
   {
     new MyListener().start();
   }
    class MyListener extends Thread{
        private Message message;
        private TextMessage textMessage; 
        private ReturnMessage returnMessage;
        private MoveMessage moveMessage;
        private boolean loop = true;
        private ConfigMessage configMessage;
        private StatusMessage statusMessage;
        public void run()
       {
        try {
          message = (Message) in.readObject();
           switch(message.getType())
           {
                case Message.MESRETURN: 
                    returnMessage = (ReturnMessage) message;
                    if (returnMessage.getRetCode()==ReturnMessage.GOODRET)
                    {
                        writeMessage(new AuthMessage(AuthMessage.SITDOWN));
                    }
                    else 
                    {
                        loop = false;
                        isConnected = false;
                    }
                    break;
            }
          while(loop)
            {
                if (isConnected())
                {
                    message = (Message) in.readObject();
                    switch (message.getType())
                    {
                        case Message.MESTEXT:
                            textMessage = (TextMessage) message;
                            myFrame.putChatMessage(textMessage.getMessage());
                            break;
                        case Message.MESMOVE: 
                            moveMessage = (MoveMessage) message;
                            myFrame.putChatMessage(moveMessage.toString());
                            manager.makeMove(moveMessage.getChess(),moveMessage.getPositionToMove(),Manager.ENEMYROUND);    
                            break;
                        case Message.MESRETURN:
                            returnMessage = (ReturnMessage) message;
                            switch (returnMessage.getRetCode()) {
                                case ReturnMessage.CARD1:
                                    myFrame.changeCard("card1");
                                    break;
                                case ReturnMessage.CARD2:
                                    manager.cleanUp();
                                    myFrame.changeCard("card2");
                                    break;
                                case ReturnMessage.CARD3:
                                    myFrame.changeCard("card3");
                                    break;
                                    
                                case ReturnMessage.CARD4:
                                    manager.getUp();
                                    myFrame.changeCard("card4");
                                    break;
                               
                            }
                            break;
                        case Message.MESCONFIG:
                            configMessage = (ConfigMessage) message;
                            manager.initPlayer(configMessage.getMyChess(), configMessage.getEnemyChess(), configMessage.getColor(),configMessage.isMyTurn());
                            break;
                        case Message.MESSTATUS:
                            statusMessage = (StatusMessage) message;
                            String print = "";
                            if (statusMessage.getStatus()==StatusMessage.ENEMYGETUP)
                            {
                                manager.pause(true);
                                break;
                            }
                            else if (statusMessage.getStatus()==StatusMessage.ENEMYSITDOWN)
                            {
                                manager.pause(false);
                                break;
                            }
                            if (statusMessage.getStatus()==StatusMessage.DRAW) print = "Zremisowałeś z ";
                            else if (statusMessage.getStatus()==StatusMessage.YOUWIN) print = "Wygrales z ";
                            else if (statusMessage.getStatus()==StatusMessage.YOULOSE) print = "Przegrales z ";
                             manager.getUp();
                            myFrame.summationCard(print,statusMessage.getEnemyName());
                            break;
                    }
                }
                
            }
            }
            catch (IOException ex) {
                isConnected = false;
            } catch (ClassNotFoundException ex) {
                isConnected = false;
            }
        }
    }
    
}

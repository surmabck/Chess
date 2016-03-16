
package pl.communication.serwer;

import pl.boardPieces.chessmans.Bishop;
import pl.boardPieces.chessmans.ChessMan;
import pl.boardPieces.chessmans.King;
import pl.boardPieces.chessmans.Knight;
import pl.boardPieces.chessmans.Pawn;
import pl.boardPieces.chessmans.Queen;
import pl.boardPieces.chessmans.Rook;
import pl.communication.database.DatabaseConnector;
import pl.communication.serializableMessage.ConfigMessage;
import pl.communication.serializableMessage.ReturnMessage;
import pl.communication.serializableMessage.StatusMessage;
import pl.communication.writerReader.Streamer;
import java.util.Iterator;
import java.util.Vector;
/**
 * Klasa która pośredniczy pomiędzy komunikacja miedzy dwoma graczami. Dwaj sparowani gracze
 * komunikuja sie poprzez obiekt tej klasy.
 * @author Bartosz Surma
 */
public class GameRoom {
    
    private int myId;
    private static int id=1;
    private Player player1;
    private Player player2;
    private final Serwer srv;
    private final DatabaseConnector dbc;
    /**
     * Konstruktor 
     * @param srv Obiekt klasy nadrzędnej. 
     * @param dbc Obiekt klasy służacej do komunikacji z baza danych.
     */
    public GameRoom(Serwer srv,DatabaseConnector dbc) 
    {
        player1 = new Player();
        player2 = new Player();
        this.srv = srv;
        myId=id;
        id++;
        this.dbc = dbc;
    }
    /**
     * Pobiera nr ID obiektu
     * @return nr ID obiektu
     */
    public static int nextId()
    {
        return id;
    }
    /**
     * Ustawia nr ID obiketu
     * @param idd nr ID do ustawienia, jako ID obiektu
     */
    public static void setStartId(int idd)
    {
        id = idd;
    }
    /**
     * Zwraca wartość ID obiektu
     * @return wartosc ID obiektu
     */
    public int getId(){
        return this.myId;   
    }
    /**
     * Służy do pobrania nazwy przeciwnika w pokoju. 
     * @param username Nazwa użytkownika, dla którego ma zostać pobrany oponent.
     * @return Zwraca nazwe przeciwnika użytkownika podanego jako username
     */
    public String getEnemyUsername(String username)
    {
        if (username.equals(player1.getUsername()))
            return player2.getUsername();
        else if (username.equals(player2.getUsername()))
            return player1.getUsername();
        return null;
    }
    /**
     * Zmienia użytkownika który ma ruch.
     * Ustawia ture dla przeciwnika użytkownika podanego jako username
     * @param username Nazwa użytkownika, który miał uprzednio ruch.
     */
    public void changeTurn(String username)
    {
        if (player1.getUsername().equals(username))
        {
            player1.setTurn(false);
            player2.setTurn(true);
        }
        else if (player2.getUsername().equals(username))
        {
             player2.setTurn(false);
             player1.setTurn(true);
        }
    }
    /**
     * Sprawdza czy jest miejsce w pokoju
     * @return Zwraca true jeśli jest conajmniej jedno miejsce w pokoju lub false gdy pokój jest pełny.
     */
    public boolean isSpace()
    {
        if ((player1.getUsername()==null) || (player2.getUsername()==null))
        {
            return true;
        }
       return false;
    }
    /**
     * Zamyka pokój i wywołuje funkcje czyszczace.
     */
    private void close()
    {
        dbc.endGame(myId);
        srv.closeGameRoom(myId);
    }
    /**
     * Poprzez użycie tej funkcji gracz wstaje od stołu. Gdy wstanie, może wciaz do niego usiasc
     * a jego sesja zostanie przywrócona.
     * @param username Nazwa uzytkownika, który wstaje.
     */
    public void getUp(String username)
    {

        if (username.equals(player1.getUsername()))
        {
            player1.setOnlinesStatus(false);
            if (!player2.isOnline()) close();
            else
                player2.getStreamer().writeMessage(new StatusMessage(StatusMessage.ENEMYGETUP));
        }
        else if (username.equals(player2.getUsername()))
        {
            player2.setOnlinesStatus(false);
            if (!player1.isOnline()) close();
            else
                player1.getStreamer().writeMessage(new StatusMessage(StatusMessage.ENEMYGETUP));
        }
        
    }
    /**
     * Inicjalizuje graczy w momencie gdy obaj siada do stolu.
     */
    public void broadcastConfig()
    {
        dbc.createGame(myId,player1.getUsername(),player2.getUsername());
        Vector<ChessMan> v1 = player1.getChessMans();
        Vector<ChessMan> v2 = player2.getChessMans();
        Iterator i = v1.iterator();
        while(i.hasNext())
        {
            ChessMan c = (ChessMan) i.next();
            dbc.makeMove(player1.getUsername(), player2.getUsername(), myId, c, c.getPosition(),DatabaseConnector.NOKILL);
        }
        
        i = v2.iterator();
        while(i.hasNext())
        {
            ChessMan c = (ChessMan) i.next();
            dbc.makeMove(player2.getUsername(), player1.getUsername(), myId, c, c.getPosition(),DatabaseConnector.NOKILL);
        }
 
        player1.getStreamer().writeMessage(new ConfigMessage(v1,v2,player1.getColor()).setTurn(player1.getTurn()));
        player2.getStreamer().writeMessage(new ConfigMessage(v2,v1,player2.getColor()).setTurn(player2.getTurn()));
    }
    /**
     * Wywoluje ja gracz, który siada przy stole. Wybierane jest wolne miejsce w którym gracz może usiaść.
     * @param username Nazwa użytkownika, który siada.
     * @param st Obiekt klasy Streamer użytkownika, który siada przy stole.
     */
    public void sit(String username,Streamer st){
        if (player1.getUsername()==null) 
        {
            player1.setUsername(username);
            player1.setStreamer(st);
            player1.setColor(1);
            player1.setTurn(false);
            player1.setOnlinesStatus(true);
        }
        else if (player2.getUsername()==null) 
        {
            player2.setUsername(username);
            player2.setStreamer(st);
            player2.setColor(2);
            player2.setTurn(true);
            player2.setOnlinesStatus(true);
        }
        if ((player1.isOnline()) &&(player2.isOnline())) broadcastConfig();

    }
    /**
     * Wywołuje ja gracz, który siada ponownie do tego samego stołu. 
     * Jego sesja zostaje przywrócona z bazy danych.
     * @param username Nazwa uzytkownika który ponownie siada.
     * @param st Nowy obiekt klasy Streamer użytkownika który ponownie siada przy stole.
     */
    public void reSit(String username,Streamer st)
    {
        
        if (player1.getUsername().equals(username))
        {
            player1.setStreamer(st);
            player1.getStreamer().writeMessage(new ConfigMessage(dbc.getMyChessBoard(player1.getUsername(), myId),dbc.getMyChessBoard(player2.getUsername(), myId),player1.getColor()).setTurn(player1.getTurn()));
            player2.getStreamer().writeMessage(new StatusMessage(StatusMessage.ENEMYSITDOWN));
            player1.setOnlinesStatus(true);
            
        }
        else if (player2.getUsername().equals(username))
        {
            player2.setStreamer(st);
            player2.getStreamer().writeMessage(new ConfigMessage(dbc.getMyChessBoard(player2.getUsername(), myId),dbc.getMyChessBoard(player1.getUsername(), myId),player2.getColor()).setTurn(player2.getTurn()));
            player1.getStreamer().writeMessage(new StatusMessage(StatusMessage.ENEMYSITDOWN));
            player2.setOnlinesStatus(true);
        }
    }
    /**
     * Zwraca nazwy użytkowników, któzy siedza przy stole
     * @return Tablica dwu-elementowa, zawierajaca nicki graczy przy stole.
     */
    public String[] getPlayers(){
       String [] tab = new String[2];
       tab[0] = player1.getUsername();
       tab[1] = player2.getUsername();
       return tab;
    }
    class Player{
      protected Streamer streamer;
      protected Vector<ChessMan> chessmans;
      protected int color;
      protected boolean turn;
      protected boolean isOnline;
      private  String username;
      Player()
      {
          this(null,null);
      }
      Player(String username, Streamer streamer)
        {
            this.isOnline=false;
            this.username= username;
            this.streamer = streamer;
            this.color = 0;
        }
      public void setOnlinesStatus(boolean p)
      {
          this.isOnline=p;
      } 
      public boolean isOnline()
      {
          return this.isOnline;
      }
        protected void setTurn(boolean p)
        {
            turn = p;
        }
        protected boolean getTurn()
        {
            return turn;
        }
        protected void setColor(int c)
        {
            this.color = c;
        }
        protected int getColor()
        {
            return this.color;
        }
        public String getUsername()
        {
            String ret = username;
            return ret;
        }
        public void setUsername(String username)
        {
            this.username= username;
        }
        public void setStreamer(Streamer s)
        {
            this.streamer = s;
        }
        public Streamer getStreamer()
        {
            return this.streamer;
        }
        public Vector<ChessMan> getChessMans()
        {
            Vector<ChessMan> v;
            int i = 0;
            v = new Vector<ChessMan>();
            for (i = 0; i<8;i++)
            v.add(new Pawn(6,i,i+1));
            v.add(new Rook(7,0,++i));
            v.add(new Rook(7,7,++i));
            v.add(new Knight(7,1,++i));
            v.add(new Knight(7,6,++i));
            v.add(new Bishop(7,2,++i));
            v.add(new Bishop(7,5,++i));
            v.add(new Queen(7,3,++i));
            v.add(new King(7,4,++i));
            return v;
        }
      }
    
}
    


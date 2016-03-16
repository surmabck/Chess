
package pl.logic.management;

import pl.communication.serializableMessage.MoveMessage;
import pl.boardPieces.BoardPos;
import pl.gui.NewJFrame;
import pl.gui.Szachownica;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Vector;
import pl.boardPieces.chessmans.ChessMan;
import pl.communication.serializableMessage.AuthMessage;
import pl.communication.serializableMessage.StatusMessage;

/**
 * Klasa zarzadzajaca logika gry. Posredniczy pomiedzy wszystkimi decyzjami i ruchami.
 * @author Bartosz Surma
 */
public class Manager{
    private final NewJFrame frame;
    private final Szachownica szachownica;
    private int currColor;
    private  Player p1;
    private  Player p2;
    public static boolean yourTurn = false;
    public static final int PLAYERROUND = 1;
    public static final int ENEMYROUND = 2;
    private String lastLabel;
    /**
     * Konstruktor
     * @param j Obiekt  JFrame, która jest właścicielem tego obiektu
     * @param s Obiekt  Szachownicy
     * @param p1 Obiekt Gracza nr 1
     * @param p2 Obiekt Gracza nr 2
     */
    public Manager (final NewJFrame j,final Szachownica s,Player p1, Player p2)
    {
        this.frame = j;
        this.szachownica = s;
        this.p1 = p1;
        this.p2 = p2;
    }
    /**
     * Zmienia ture. True to tura gracza, false to tura przeciwnika.
     * @return zwraca aktualna ture. Jesli tura była równa true to zwraca false i na odwrót
     */
    public boolean changeTurn()
    {
        if (yourTurn == true) setTurn(false);
        else setTurn(true);
        return yourTurn;
    }
    /**
     * Ustawia ture na wartosc podana jako argument. True to tura gracza, false to tura przeciwnika.
     * @param p Wartość p, która będzie wartościa tury
     */
    public void setTurn(boolean p)
    {
        yourTurn=p;
    }
    /**
     * Wykonuje ruch 
     * @param chess Figura, która zmienia pozycje
     * @param position Pozycja, na która należy przesunac dana figure
     * @param p Stala okreslajaca kto wykonuje ruch. PLAYERROUND oznacza, że ruch wykonuje gracz, ENEMYROUND oznacza, że ruch wykonuje przeciwnik
     * @return zwraca true jesli uda się wykonać ruch, lub false w przeciwnym wypadku
     */
    public boolean makeMove(ChessMan chess,BoardPos position,int p)
    {
        Player player;
        Player enemy;
        if (p==PLAYERROUND)
        {
          player = p1;
          enemy = p2;
          szachownica.moveChess(chess, position, player.getColor());
          player.makeMove(chess.getPosition(),position);
        }
        else
        {
          player = p2;
          enemy = p1;
          szachownica.moveChess(chess.switchPos(), position.switchPos(), player.getColor());
          player.makeMove(chess.getPosition(),position);
        }
        
        
        boolean amount = enemy.tryToKill(position);
        String colorPlayer;
        String colorEnemy;
        if (player.getColor() == 1) colorPlayer = "BIALE";
            else colorPlayer = "CZARNE";
        if (enemy.getColor() == 1) colorEnemy= "BIALE";
            else colorEnemy = "CZARNE";
        if (amount==true)  
        {
            frame.putChatMessage("<SYSTEM> Wygrały "+colorPlayer);
            if (p==PLAYERROUND) frame.writeMessageToNetworkClient(new StatusMessage(StatusMessage.YOUWIN));
        }
        changeTurn();
        frame.setStatusLabel("RUCH MAJĄ "+colorEnemy);
        szachownica.setDefColor();
        szachownica.repaint(); 
        return true;
    }
    /**
     * Inicjalizuje zmienne wewnetrzne opisujace graczy.
     * @param playerBoard Vector figur, które będa ustawione dla zmiennej gracza
     * @param enemyBoard Vector figur, które będa ustawione dla zmiennej przeciwnika
     * @param color Color gracza
     * @param turn wartość true ozancza ture gracza, false ture przeciwnika
     */
    public void initPlayer(Vector<ChessMan> playerBoard ,  Vector<ChessMan> enemyBoard,int color,boolean turn)
    {
            p1 = new Player(playerBoard,color);
            
            yourTurn = turn;
            String label ="";
            int color2=0;
            if (color==1)
            {
                color2=2;
                if (yourTurn==true) label = "RUCH MAJA BIALE";
                else label = "RUCH MAJA CZARNE";
            }
            else if (color==2)
            {
                color2=1;
                if (yourTurn==true) label = "RUCH MAJA CZARNE";
                else label = "RUCH MAJA BIALE";
            }
            p2 = new Player(enemyBoard,color2);
            frame.setStatusLabel(label);

        Iterator i = p1.getChessMansPos().iterator();
        while(i.hasNext())
        {
            ChessMan p = (ChessMan) i.next();
            szachownica.setChess(p,p1.getColor());
        }
        i = p2.getChessMansPos().iterator();
        while(i.hasNext())
        {
            ChessMan p = (ChessMan) i.next();
            szachownica.setChess(p.switchPos(),p2.getColor());
        }
        szachownica.repaint();
    }
    /**
     * Metoda zawiesza lub odwiesza możliwość interakcji z czatem i szachownica
     * @param p true - blokuje , false - odblokowuje
     */
    public void pause(boolean p)
    {
        if (p==true)
        {
            lastLabel=frame.getStatusLabelText();
            frame.setStatusLabel("Przeciwnik się rozlaczyl");
            frame.gameFocus(false);
        }
        else
        {
            frame.setStatusLabel(lastLabel);
            frame.gameFocus(true);
        }
    }
    /**
     * Sprawdza czy jest możliwe wykonanie ruchu i jesli to mozliwe to to robi.
     * @param p 
     * @param position Pozycja, na która sprawdza czy można wykonac ruch.
     * @return true jesli wykonano ruch, false jesli nie
     */
    public boolean CheckForMove(int p,BoardPos position){

      if (yourTurn==true)
      {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                if (p1.getClicked()!=null)
                {
                    if (!p1.isInMovesSet(position))
                    {
                        /*
                        Kliknieta figura nie byla w mozliwych ruchach,
                        wiec sprawdzamy czy gracz nie chce zmienic aktywnej figury
                        */
                        Vector<BoardPos> p = p1.getChessMoves(position,p2.getChessPositions());
                        if (p!=null)
                        {
                            p1.setActualMoves(position, p);
                            szachownica.setDefColor();
                            Iterator i = p.iterator();
                            if (!p.isEmpty())
                            while (i.hasNext())
                            {
                                BoardPos ps =(BoardPos) i.next();
                                szachownica.setSquareColor(ps.getMyX(),ps.getMyY(),1);
                            }
                        }

                    }
                    else {
                        /*
                        Kliknieta figura byla w mozliwych ruchach, probujemy wykonac ruch
                        */
                        ChessMan ces = (ChessMan)p1.getClicked().clone();
                        MoveMessage m = new MoveMessage(ces,position,frame.getUsername()); 
                        frame.writeMessageToNetworkClient(m);
                        makeMove(p1.getClicked(),position,PLAYERROUND);
                        
                    }
                } else
                {
                    /*
                    Kliknieta figura nie byla w mozliwych ruchach i player nie mial wybranej zadnej
                    aktywnej figury. wybieramy aktywna figura i pobieramy mozliwe ruchy
                    */
                    Vector<BoardPos> p = p1.getChessMoves(position,p2.getChessPositions());
                        if (p!=null)
                        {
                            p1.setActualMoves(position, p);
                            szachownica.setDefColor();
                            Iterator i = p.iterator();
                            if (!p.isEmpty())
                            while (i.hasNext())
                            {
                                BoardPos ps =(BoardPos) i.next();
                                szachownica.setSquareColor(ps.getMyX(),ps.getMyY(),1);
                            }
                        }
                    if (p1.getChessAmount()==1 && p==null)
                    {
                        frame.writeMessageToNetworkClient(new StatusMessage(StatusMessage.YOULOSE));
                    }
                }
            }
        });
      }
        return true;
    }
    /**
     * Metoda czysci zawartości obiektów po poprzedniej rozgrywce
     */
    public void cleanUp()
    {
       if (p1!=null)p1.clean();
       if (p2!=null) p2.clean();
       szachownica.clean();  
       frame.clearChat();
       frame.repaint();
    }
    /**
     * Metoda powoduje ponowne dołaczenie do stołu na serwerze 
     */
    public void reSit()
    {
        cleanUp();
        getUp();
        sitDown();
    }
    /**
     * Metoda powoduje wstanie od stołu na serwerze
     */
    public void getUp()
    {
       frame.writeMessageToNetworkClient(new AuthMessage(AuthMessage.GETUP)); 
    }
    /**
     * Metoda powoduje dołaczenie do stolu na serwerze
     */
    public void sitDown()
    {
       frame.writeMessageToNetworkClient(new AuthMessage(AuthMessage.SITDOWN));   
    }
    /**
     * Metoda powoduje wylogowanie z serwera
     */
    public void logOut()
    {
       cleanUp(); 
       frame.writeMessageToNetworkClient(new AuthMessage(AuthMessage.GETUP)); 
       frame.writeMessageToNetworkClient(new AuthMessage(AuthMessage.LOGOUT)); 
       
    }
    
}

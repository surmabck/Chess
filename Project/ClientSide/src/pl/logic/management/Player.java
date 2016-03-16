
package pl.logic.management;

import pl.boardPieces.chessmans.King;
import pl.boardPieces.chessmans.ChessMan;
import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa opisuje gracza.
 * @author Bartosz Surma
 */
public class Player {
    private Vector<ChessMan> chessmans;
    private int color;
    private BoardPos clicked;
    private Vector <BoardPos> moves ;
    /**
     * Konstruktor
     */
    public Player(){
    }
    /**
     * Konstruktor
     * @param c Vector figur, które będa szachami gracza
     * @param color Color figur gracza
     */
    public Player(Vector<ChessMan> c,int color)
    {
        this.chessmans = c;
        Iterator i = c.iterator();
        while (i.hasNext())
        {
            ChessMan ca = (ChessMan) i.next();     
        }
        this.color = color;
    }
    /**
     * Zapisuje Vector z możliwymi ruchami do wykonania, przez figury znajdujaca sie na pozycji clicked
     * @param clicked Pozycja aktualnie kliknięta. Do niej odnosza sie ruchy z Vektora moves
     * @param moves Vector z możliwymi ruchami przez figure na pozycji clicked
     */
    public void setActualMoves(BoardPos clicked, Vector<BoardPos> moves)
    {
        this.clicked = clicked;
        this.moves = moves;
    }
    /**
     * Zwraca ilość szachów gracza na szachownicy
     * @return Ilość figur
     */
    public int getChessAmount()
    {
        return chessmans.size();
    }
    /**
     * Czyści wszystkie struktury opisujace ten obiekt.
     */
    public void clean()
    {
        this.chessmans=null;
        this.clicked = null;
        this.moves = null;
              
    }
    /**
     * Próbuje usunac figure gracza znajdujaca sie na pozycji p
     * @param p Pozycja z ktorej metoda spróbuje usunać figure
     * @return true, jeśli na pozycji p znajdowała się jakaś figura, false w przeciwnym wypadku
     */
    public boolean tryToKill(BoardPos p)
    {
        boolean ret = false;
        Iterator i = chessmans.iterator();
        while (i.hasNext())
        {
            ChessMan c = (ChessMan)i.next();
            if (c.getPosition().equals(p.switchPos()))
            {
                i.remove();
                if (c instanceof King) ret = true;
                break;
            }
        }
        return ret;
    }
    /**
     * Zwraca możliwe ruchy gracza, przez figure znajdujaca sie na pozycji clicked
     * @return Vector mozliwych ruchów
     */
    public Vector<BoardPos> getActualMoves(){
        return this.moves;
    }
    /**
     * Metoda sprawdza czy podana pozycja p jest w mozliwych ruchach przez figure na pozycji clicked
     * @param p Pozycja p
     * @return true jeśli pozycja p jest  w możliwych ruchach, false w przeciwnym wypadku
     */
    public boolean isInMovesSet(BoardPos p){
        Vector<BoardPos> vector = getActualMoves();
        if (vector!=null)
        {
            Iterator i = vector.iterator();
            while(i.hasNext())
            {
                BoardPos pp = (BoardPos) i.next();
                if (pp.equals(p)) return true;
            }
        }
        return false;
    }
    /**
     * Zwraca kolor figur gracza
     * @return Kolor figur
     */
    public int getColor(){
        return this.color;
    }
    /**
     * Zwraca vector figur gracza
     * @return Vector figur gracza 
     */
    public Vector<ChessMan> getChessMansPos ()
    {
        Vector<ChessMan> p = new Vector<ChessMan>();
        Iterator i = chessmans.iterator();
        while(i.hasNext()){
            ChessMan pos = (ChessMan)i.next();
            p.add(pos);
        }
        return p;
    }
    /**
     * Zwraca Figure na pozycji clicked
     * @return Figura, która znajduje się na pozycji clicked
     */
    public ChessMan getClicked()
    {
        
        Iterator i = chessmans.iterator();
        if (clicked!=null)
        while(i.hasNext())
        {
            ChessMan pos = (ChessMan)i.next();
            if (pos.getPosition().equals(clicked))
                return pos;
        }
        return null;
    }
    /**
     * Wykonuje ruch figury z pozycji activePos na pozycje p
     * @param activePos Pozycja figury do przesuniecia
     * @param p Pozycja na któa należy przesunac figure
     */
    public void makeMove(BoardPos activePos,BoardPos p){
        Iterator i = getChessMansPos().iterator();
        while (i.hasNext())
        {
            ChessMan c = (ChessMan) i.next();
            if (c.getPosition().equals(activePos))
            {
                c.setPosition(p);
                break;
            }
        }
        this.setActualMoves(null, null);
    }
    /**
     * Zwraca vector pozycji figur gracza
     * @return Vector pozycji figur gracza
     */
    public Vector<BoardPos> getChessPositions ()
    {
        Vector<BoardPos> p = new Vector<BoardPos>();
        Iterator i = chessmans.iterator();
        while(i.hasNext()){
            ChessMan pos = (ChessMan)i.next();
            p.add(pos.getPosition());
        }
        return p;
    }
    /**
     * Sprawdza czy jakas figura znajduje sie na pozycji p
     * @param p Pozycja p
     * @return zwraca Figure, która znajduje się na pozycji p, lub null, jeśli na tej pozycji nie ma figury
     */
    public ChessMan isInDeck(BoardPos p){
    Iterator i = chessmans.iterator();
        ChessMan pos = new ChessMan();
        while(i.hasNext()){
             pos= (ChessMan)i.next();
            if((pos.getPosition().getMyX()==p.getMyX()) && (pos.getPosition().getMyY()==p.getMyY()))
            {
                return pos;
            }
        } 
        return null;
    }
    /**
     * Zwraca vector pozycji, na które figura z pozycji p może sie przesunac
     * @param p Pozycja figury, dla której sprawdzamy ruchy
     * @param enemyPos Vector pozycji figur przeciwnika
     * @return Vector pozycji na które może się przesunac figura z pozycji p, lub null, jesli nie ma ruchów
     */
    public Vector<BoardPos> getChessMoves (BoardPos p,Vector<BoardPos> enemyPos){
        ChessMan c = isInDeck(p);
        if (c!=null)
            return c.getMoves(enemyPos,this.getChessPositions());
        return null;
    }
}

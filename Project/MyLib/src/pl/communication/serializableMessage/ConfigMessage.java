package pl.communication.serializableMessage;

import pl.boardPieces.chessmans.ChessMan;
import java.util.Vector;
/**
 * Klasa wiadomości serializowanej, która dziedziczy po klasie Message.
 * Służy do konfigurowania clientów przez serwer.
 * @author Krzysztof Surdy
 */
public class ConfigMessage extends Message{
public static final int YOURBOARD=1;
public static final int ENEMYBOARD=2;
    private int flags;
    private Vector<ChessMan> chessmans1;
    private Vector<ChessMan> chessmans2;
    private int c;
    private boolean turn;
    /**
     * Konstruktor jedno argumentowy
     */
    public ConfigMessage()
    {
        chessmans1 = null;
        chessmans1 = null;
    }
    /**
     * Konstruktor trój argumentowy. Przyjmuje 
     * @param c1 Vector figur, które będa ustawione jako figury gracza
     * @param c2 Vector figur, które będa ustawione jako figury przeciwnika
     * @param c Okresla kolor figur
     */
    public ConfigMessage(Vector<ChessMan> c1,Vector<ChessMan> c2,int c)
    {
        super(Message.MESCONFIG);
        this.chessmans1 = c1;
        this.chessmans2 = c2;
        this.c = c;
        turn = false;
    }
    /**
     * Ustawia ture 
     * @param p Zmienna boolean, true znaczy, że użytkownik ma ruch, false, że przeciwnik.
     * @return Zwraca zmodyfikowany obiekt tej samej wiadomości
     */
    public ConfigMessage setTurn(boolean p)
    {
        this.turn  = p;
        return this;
    }
    /**
     * Sprawdza czy jest tura użytkownika
     * @return true, jeśli jest tura użytkownika, do którego wysłana jest wiadomość, false w przeciwnym wypadku
     */
    public boolean isMyTurn()
    {
        return this.turn;
    }
    /**
     * Zwraca Vector figur użytkownika
     * @return Vector figur użytkownika
     */
    public Vector<ChessMan> getMyChess()
    {
        return chessmans1;
    }
    /**
     * Zwraca Vector figur przeciwnika
     * @return Vector figur przeciwnika
     */
    public Vector<ChessMan> getEnemyChess()
    {
        return chessmans2;
    }
    /**
     * Zwraca color figur
     * @return color figur
     */
    public int getColor()
    {
        return c;
    }
}
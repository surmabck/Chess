
package pl.communication.serializableMessage;

import java.io.Serializable;
/**
 * Klasa wiadomości serializowanej która implementuje interfejs Serializable.
 * Stanowi podstawe klas serializowanych, które dziedzicza po niej.
 * @author Krzysztof Surdy
 */
public class Message implements Serializable{
    public final static int MESAUTH = 1;
    public final static int MESTEXT = 3;
    public final static int MESMOVE = 4;
    public final static int MESSUBMIT = 5;
    public final static int MESRETURN = 6;
    public final static int MESCONFIG = 7;
    public final static int MESSTATUS= 8;


    private int type;
    private int returnCode;
    /**
     * Konstruktor 
     */
    public Message()
    {
        this(3);
    }
    /**
     * Konstruktor jedno argumentowy
     * @param type Okresla typ wiadomości. Przyjmuje stałe z tej klasy.
     */
    public Message(int type)
    {
        this.type = type;
    }
    /**
     * Zwraca przenoszony typ
     * @return typ wiadomości
     */
    public int getType()
    {
        return type;
    }
    /**
     * Ustawia typ przenoszony przez wiadomość
     * @param type Typ który ma być ustawiony
     */
    public void setType(int type)
    {
        this.type = type;
    }
    /**
     * Przekształca obiekt na tekst
     * @return Tekst opisujacy wiadomość
     */
    public String toString()
    {
        switch(this.type)
        {
            case MESAUTH: return "AUTHORIZATION";
            case MESMOVE: return "MOVE";
            case MESRETURN: return "RETURN MESSAGE";
            case MESTEXT: return "TEXT MESSAGE"; 
        }
        return "DUNNO";
    }
}
package pl.communication.serializableMessage;
/**
 * Klasa wiadomości serializowanej która dziedziczy po klasie Message. 
 * Służy do wysyłania wiadomości zwrotnych i kontrolnych
 * @author Krzysztof Surdy
 */
public class ReturnMessage extends Message{
    public final static int CARD1 = 1;
    public final static int CARD2 = 2;
    public final static int CARD3 = 3;
    public final static int CARD4 = 4;
    public final static int BADRET = 5;
    public final static int GOODRET = 6;
    private int retCode;
    private final String retMessage;
    /**
     * Konstruktor
     */
    public ReturnMessage ()
    {
        this(CARD2,"Positive response");
    }
    /**
     * Konstruktor
     * @param retCode Wartosc określajaca kod zwrotny przenoszony przez wiadomość. Przyjmuje jedna ze stalych tej klasy.
     */
    public ReturnMessage(int retCode)
    {
        this(retCode,"accepted");
    }
    /**
     * Konstruktor
     * @param retCode Wartosc określajaca kod zwrotny przenoszony przez wiadomość. Przyjmuje jedna ze stalych tej klasy.
     * @param retMessage Dodatkowa wiadomość przenoszona przez wiadomość.
     */
    public ReturnMessage(int retCode, String retMessage)
    {
        super(ReturnMessage.MESRETURN);
        this.retMessage = retMessage;
        this.retCode = retCode;
    }
    /**
     * Zwraca kod wiadomości
     * @return Kod zwrotny
     */
    public int getRetCode()
    {
        return this.retCode;
    }
    /**
     * Zwraca wiadomość przenoszona przez wiadomość
     * @return Wiadomość przenoszona przez wiadomość
     */
    public String getRetMessage()
    {
        return this.retMessage;
    }
    /**
     * Zamienia wiadomość na tekst
     * @return Tekst opisujacy wiadomość.
     */
    public String toString()
    {
        return retCode +":"+retMessage;
    }
}
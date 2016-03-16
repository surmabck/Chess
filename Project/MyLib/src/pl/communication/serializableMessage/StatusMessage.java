package pl.communication.serializableMessage;
/**
 * Klasa wiadomości serializowanej która dziedziczy po klasie Message. 
 * Służy do przesylania stanów gry.
 * @author Bartosz
 */
public class StatusMessage extends Message {

    public static final int YOUWIN =1;
    public static final int YOULOSE = 2;
    public static final int DRAW = 3;
    public final static int ENEMYGETUP =4;
    public final static int ENEMYSITDOWN =5;
    private int currStatus;
    private String enemyName;
    /**
     * Konstruktor
     */
    public StatusMessage()
    {
        this(YOUWIN);
    }
    /**
     * Konstruktor
     * @param currStatus Status wiadomości. Przyjmuje jedna ze stalych tej klasy.
     */
    public StatusMessage(int currStatus)
    {
        this(currStatus,"unknow");
    }
    /**
     * Konstruktor
     * @param currStatus Status wiadomości. Przyjmuje jedna ze stalych tej klasy.
     * @param enemyName Nazwa przeciwnika, adresata tej wiadomośći.
     */
    public StatusMessage(int currStatus, String enemyName)
    {
        super(Message.MESSTATUS);
        this.currStatus = currStatus;
        this.enemyName = enemyName;
    }
    /**
     * Zwraca status wiadomości
     * @return Status wiadomości.
     */
    public int getStatus()
    {
        return currStatus;
    }
    /**
     * Zwraca nazwe przeciwnika
     * @return Nazwa przeciwnika
     */
    public String getEnemyName()
    {
        return enemyName;
    }
}
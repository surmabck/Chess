package pl.communication.serializableMessage;
/**
 * Klasa wiadomości serializowanej która dziedziczy po klasie Message. 
 * Służy do przesyłania komunikatów uwierzytelniajacyh i zarzadzajacych sesja
 * @author Krzysztof Surdy
 */
public class AuthMessage extends Message {
    public final static int LOGIN = 1;
    public final static int REGISTER = 2;
    public final static int LOGOUT = 3;
    public final static int SITDOWN= 4;
    public final static int GETUP =5;
    private final int action;
    private final String username;
    private final String password;
    /**
     * Konstruktor bez argumentów
     */
    public AuthMessage()
    {
        this(AuthMessage.LOGIN,"test1","test1");
    }
    /**
     * Konstruktor z jednym argumentem.
     * @param action Określa akcje, która ma zostać wysłana. Przyjmuje jedna ze stalych
     */
    public AuthMessage(int action)
    {
        this(action,null,null);
    }
    /**
     * Konstruktor 3 argumentowy
     * @param action Określa akcje, która ma zostać wysłana. Przyjmuje jedna ze stalych
     * @param username Nazwa użytkownika, który wysyła wiadomość
     * @param password Haslo użytkownika, który wysyła wiadomość
     */
    public AuthMessage(int action,String username, String password)
    {
        super(AuthMessage.MESAUTH);
        this.action = action;
        this.username = username;
        this.password = password;
    }
    /**
     * Zwraca typ akcji, która przenosi wiadomość
     * @return Typ akcji
     */
    public int getAction()
    {
        return this.action;
    }
    /**
     * Zwraca użytkownika, która przenosi wiadomość
     * @return nazwa użytkownika
     */
    public String getUsername()
    {
        return this.username;
    }
    /**
     * Zwraca haslo, które przenosi wiadomość
     * @return haslo użytkownika
     */
    public String getPassword()
    {
        return this.password;
    }    
}
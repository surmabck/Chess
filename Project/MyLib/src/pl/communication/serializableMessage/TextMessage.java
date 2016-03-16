package pl.communication.serializableMessage;
/**
 * Klasa wiadomości serializowanej która dziedziczy po klasie Message. 
 * Służy do przenoszenia wiadomości tekstowych, wysyłanych przez czat.
 * @author Bartosz
 */
public class TextMessage extends Message {
    private String message;
    /**
     * Konstruktor
     */
    public TextMessage()
    {
        this("TEXTMESSAGE");
    }
    /**
     * Konstruktor
     * @param message Wiadomość tekstowa, która będzie przenosiła wiadomość 
     */
    public TextMessage(String message)
    {
        super(TextMessage.MESTEXT);
        this.message = message;
    }
    /**
     * Zwraca wiadomość tekstowa przenoszona przez obiekt.
     * @return Wiadomosc tekstowa
     */
    public String getMessage()
    {
        return this.message;
    }
    /**
     * Ustawia wiadomość, która przesyłana jest przez wiadomość.
     * @param s Wiadomość tekstowa
     */
    public void setMessage(String s)
    {
        this.message = s;
    }
    
}
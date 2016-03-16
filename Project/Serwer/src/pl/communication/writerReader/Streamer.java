
package pl.communication.writerReader;

import pl.communication.serializableMessage.Message;
import pl.communication.serwer.Serwer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sluży do wysylania i odbierania wiadomosci ze strumieni.
 * Kazdy nowo polaczony klient ma swoj obiekt tej klasy.
 * @author Bartosz Surma
 */
public class Streamer {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    /**
     * Jedyny konstruktor. Przyjmuje dwa argumenty.
     * @param out Strumien wyjsciowy.
     * @param in Strumien wejsciowy. 
     */
    public Streamer(OutputStream out, InputStream in)
    {
        try {
            this.out = new ObjectOutputStream(out);
            this.in =new ObjectInputStream( in);
        } catch (IOException ex) {
            Logger.getLogger(Streamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Sluzy do pobrania obiketu strumieni wyjsciowego przechowywanego w obiekcie tej klasy.
     * @return Obiekt strumienia wyjsciowego.
     */
    public ObjectOutputStream getOutStream()
    {
        return this.out;
    }
    /**
     * Sluży do wysylania danych do strumienia innego niż własny.
     * @param streamer Obiekt, do którego strumienia wyjsciowego ma zostać wyslana wiadomość
     * @param msg Wiadomość do wyslania. Obiekt serializowanej klasy Message lub pochodnej.
     */
    public void writeMessageTo(Streamer streamer,Message msg)
    {

        try {
            streamer.getOutStream().writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Serwer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Służy do wysłania wiadomośći do strumienia przechowywanego w tym obiekcie.
     * @param msg Wiadomość do wyslania. Obiekt serializowanej klasy Message lub pochodnej.
     */
    public void writeMessage(Message msg)
    {
        try {
            out.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Streamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Odczytuje dane ze strumienia wejściowego. Wyrzuca wyjatki IOException oraz ClassNotFoundException
     * @return Zwraca odczytane dane ze strumienia wejsciowego.
     * @throws IOException Wyjatek wejscia wyjscia
     * @throws ClassNotFoundException Wyjatek np. zlej sciezki do klasy
     */
    public Message readMessage() throws IOException, ClassNotFoundException
    {
       return  (Message) in.readObject(); 
    }
    /**
     * Zamyka strumienie wejścia i wyjścia.
     */
    public void closeStream()
    {
        try {
            out.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Streamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


package pl.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasa dziedziczaca po JPanel
 * Udostepnia możliwość ustawienia tła
 * @author Bartosz Surma
 */
public class JMainFrame extends JPanel{
    /**
     * Konstruktor
     */
    public JMainFrame(){
        this(null);
    }
    /**
     * Konstruktor
     * @param x Nazwa pliku, zawierajcego plik tła
     */
    public JMainFrame(String x)
    {
        if (x!=null)
        {    
            try{
                img = ImageIO.read(this.getClass().getResource("/pl/image/"+x));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
    /**
     * Metoda do rysowania, używana przez swing
     * @param g 
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (img!=null)
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
    
    private Image img;
}


package pl.gui;

import pl.boardPieces.chessmans.ChessMan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import pl.boardPieces.BoardPos;
import pl.logic.management.Player;

/**
 * Klasa dziedziczaca po klasie JPanel. 
 * Kontener biblioteki swing. Odpowiada za wyswietlanie szachownicy i Figur na niej.
 * @author Bartosz Surma
 */
public class Szachownica extends JPanel {
    private Image img;
    private Square b[][] = new Square[8][8];
    /**
     * Konstruktor
     */
    public Szachownica()
    {
        this("szachownica.png");
    }
    /**
     * Konstruktor
     * @param x Nazwa pliku graficznego z paczki image zawierajacego grafike tla.
     */
    public Szachownica(String x)
    {
        if (x!=null)
        {    
            try{
                img = ImageIO.read(this.getClass().getResource("/image/"+x));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
     this.setOpaque(true);
     this.setBackground(new Color(51,49,49));

     setLayout(new BorderLayout(0,0));
     JMainFrame panel = new JMainFrame();
     panel.setLayout(new GridLayout(8,8));
     panel.setOpaque(false);
     panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
     Color colorWhite1 = new Color(156,156,156);
     Color colorWhite2 = new Color(223,223,223);
     Color colorBlack1 = new Color(90,90,90);
     Color colorBlack2 = new Color(29,37,117);
     String labelsTop = "abcdefgh";
     Font f = new Font("Arial", 1, 18);
     for (int i=0;i<8;i++)
     {
          
         for (int j=0;j<8;j++)
         {
             Square but;
             
             if ((i+j)%2==0)  
             {
                 but = new Square(i,j,colorBlack1,colorBlack2);
                 but.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
             }
             else  
             {
                 but = new Square(i,j,colorWhite1,colorWhite2);
                 but.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
             }
             but.setOpaque(true);
             b[i][j] = but;

             panel.add(b[i][j]);

         }
         add(panel);
     }
    }
    /**
     * Czysci ustawienia szachownicy. Wszystkie szachy stojace na niej, oraz ustawia standardowy Kolor
     */
    public void clean()
    {
    for (int i=0;i<8;i++)
        for (int j=0;j<8;j++)              
        {
            b[i][j].setColor(0);
            b[i][j].deleteChessMan();
        }  
    this.repaint();
    }
    /**
     * Ustawia podana figure na szachownicy, o podanym kolorze
     * @param p Figura, która będzie ustawiona na szachownicy
     * @param color Kolor figury
     */
    public void setChess(ChessMan p,int color){
        BoardPos pp = p.getPosition();
        b[pp.getMyX()][pp.getMyY()].setChessMan(p,color);
    }
    /**
     * Ustawia obramowanie pól na szachownicy
     * @param x Poyzcja x pola
     * @param y Pozycja y pola
     * @param c Kolor pola
     */
    public void setSquareBorder(int x, int y,Color c){
    int left = 1;
    int top = 1;
    int right = 1;
    int bottom = 1;
    if (x==0) top = 0;
    if (x==7) bottom = 0;
    if (y==0) left = 0;
    if (y==7) right = 0;
        b[x][y].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), BorderFactory.createMatteBorder(top, left, bottom, right, c)));
    }
    /**
     * Zmienia pozycje figury
     * @param c Figura
     * @param next Pozycja do przesuniecia
     * @param color Kolor do rysowania
     */
    public void moveChess(ChessMan c, BoardPos next, int color)
    {
       b[c.getPosition().getMyX()][c.getPosition().getMyY()].deleteChessMan();
       b[next.getMyX()][next.getMyY()].setChessMan(c, color);
    }
    /**
     * Ustawia standardowy kolor dla wszystkich pól
     */
    public void setDefColor()
    {
        for (int i=0;i<8;i++)
            for (int j=0;j<8;j++)              
                b[i][j].setColor(0);
    }
    /**
     * Ustawia kolor pola danego przez x i y
     * @param x Pozycja x pola
     * @param y Pozycja y pola
     * @param color Kolor do ustawienia
     */
    public void setSquareColor(int x, int y,int color)
    {
        b[x][y].setColor(color);
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
    /**
     * Ustawia możliwość klikniecia poszczególnych pól
     * @param p true - można kliknac pole, false - nie można kliknac pola
     */
    public void setFocus(boolean p)
    {
        System.out.println(p);
        for (int i=0;i<8;i++)
            for (int j=0;j<8;j++)
                b[i][j].setFocus(p);
    }

}

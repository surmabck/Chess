
package pl.gui;

import pl.boardPieces.chessmans.King;
import pl.boardPieces.chessmans.Pawn;
import pl.boardPieces.chessmans.Rook;
import pl.boardPieces.chessmans.Queen;
import pl.boardPieces.chessmans.Knight;
import pl.boardPieces.chessmans.Bishop;
import pl.boardPieces.chessmans.ChessMan;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 * Klasa rozszerze klase JPanel.
 * Dostarcza mechanizmów do wyswietlania i zarzadzania polem szachownicy.
 * @author Bartosz Surma
 */
public class Square extends JPanel {
    private Color[] colors;
    private int currColor;
    private boolean focusable;
    /**
     * Konstruktor
     */
    public Square(){
        this(0,0,Color.white,Color.lightGray);
    }
    /**
     * Konstruktor
     * @param x Pozycja x pola na szachownicy
     * @param y Pozycja y pola na szachownicy
     * @param c1 Zwykły kolor pola
     * @param c2 Kolor pola po podswietleniu
     */
    public Square(int x,int y,Color c1,Color c2){
        this("figury2.png",x,y,c1,c2);
    }
    /**
     * Konstruktor
     * @param x Nazwa pliku graficznego, zawierajacy palete figur
     * @param i Pozycja x pola na szachownicy
     * @param j Pozycja y pola na szachownicy
     * @param c1 Zwykły kolor pola
     * @param c2 Kolor pola po podswietleniu
     */
    public Square(String x,int i, int j,Color c1,Color c2){
        if (x!=null)
            {    
            try{
                img = ImageIO.read(this.getClass().getResource("/image/"+x));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            focusable = true;
        }
        colors = new Color[2];
        colors[0] = c1;
        colors[1] = c2;
        this.x = i;
        this.y = j;
        currentPos = 0;
        currentColor = 0;
        chessManSet = false;
        setBackground(colors[0]);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clicked();
            }
        });
     this.p = p;
    }
    /**
     * Ustawia możliwość klikniecia pola
     * @param p true - można kliknac pole, false - nie mozna kliknac pola
     */
    public void setFocus(boolean p)
    {
        this.focusable = p;
    }
    /**
     * Ustawia kolor pola
     * @param i Nr kolor , 1-to zwykly kolor, 2-kolor podswietlenia
     */
    public void setColor(int i)
    {
        this.setBackground(colors[i]);
    }
    /**
     * Metoda wywolywana po kliknieciu panelu. 
     * Propaguje informacje o kliknieciu do głównej ramki NewJFrame
     */
    public void clicked(){
        if (focusable)
        {
            NewJFrame j =  (NewJFrame) this.getTopLevelAncestor();
            j.squareClicked(x,y);
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
            if (chessManSet)
            {
               
                int myWidth =Math.round( getWidth()*0.75f);
                int myHeight = Math.round( getHeight()*0.75f);
                int myStartx = Math.round(myWidth*1.25f - myWidth)/2;
                int myStarty = Math.round(myHeight*1.25f - myHeight)/2;

                g.drawImage(
                        img.getSubimage(currentPos, currentColor, 150, 150), myStartx,  myStarty,
                        myWidth, myHeight, this
                );
            }
            
    }
    /**
     * Ustawia aktualna figure, ktora powinna byc wyswietlana na panelu
     * @param p Figura ktora ma byc wyswietlana
     * @param color Kolor figury, 2-czarny,1-bialy
     */
    public void setChessMan(ChessMan p,int color)
    {
     if (p instanceof Pawn)
         currentPos = PAWNPOS;
     if (p instanceof Bishop)
         currentPos = BISHOPPOS;
     if (p instanceof King)
         currentPos = KINGPOS;
     if (p instanceof Knight)
         currentPos = KNIGHTPOS;
     if (p instanceof Queen)
         currentPos = QUEENPOS;
     if (p instanceof Rook)
         currentPos = ROOKPOS;
     if (color == 2)
         currentColor = COLORBLACK;
     else currentColor = COLORWHITE;
     this.chessManSet = true;
    }
    public void deleteChessMan(){
        this.chessManSet = false;
    }
    private static int KINGPOS = 450;
    private static int QUEENPOS = 300;
    private static int ROOKPOS = 0;
    private static int BISHOPPOS = 150;
    private static int KNIGHTPOS = 600;
    private static int PAWNPOS = 750;
    private static int COLORWHITE = 150;
    private static int COLORBLACK = 0;
    private Szachownica p;
    private BufferedImage img;
    private boolean chessManSet;
    private int currentPos;
    private int currentColor;
    private int x,y;
}

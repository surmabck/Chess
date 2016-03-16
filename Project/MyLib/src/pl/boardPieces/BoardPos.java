package pl.boardPieces;

import java.io.Serializable;

/**
 * Klasa opisujaca pozycje figury na szachownicy. Implementuje interfejs Serializable
 * @author Bartosz Surma
 */
public class BoardPos implements Serializable {
    private int x;
    private int y;
   /**
    * Konstruktor
    */
 public BoardPos(){
     this(1,1);
 }   
 /**
  * Konstruktor
  * @param x Pozycja x na szachownicy
  * @param y Pozycja y na szachownicy
  */
 public BoardPos( int x, int y){
     this.x = x;
     this.y = y;
 }
 /**
  * Konstruktor, który tworzy obiekt na podstawie innego obiektu tej samej klasy
  * @param o Obiekt tej samej klasy, opisujacy pozycje na szachownicy.
  */
 public BoardPos(BoardPos o){
     this.x = o.getMyX();
     this.y = o.getMyY();
 }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
 public BoardPos switchPos()
 {
     return new BoardPos(7-getMyX(),7-getMyY());
 }
 /**
  * Ustawia pozycja figury na szachownicy
  * @param x Pozycja x
  * @param y Pozycja y
  */
 public void setPos(int x, int y){
     this.x = x;
     this.y = y;
 }
 /**
  * Wykonuje głęboka kopie obiektu
  * @return Zwraca obiekt tej samej klasy po skopiowaniu
  */
 public BoardPos copy(){
     return new BoardPos(this.x,this.y);
 }
 /**
  * Zwraca pozycje x 
  * @return Pozycja x
  */
 public int getMyX(){
     return this.x;
 }
 /**
  * Zwraca pozycje y
  * @return Pozycja y
  */
 public int getMyY(){
     return this.y;
 }
 /**
  * Porównuje ten obiekt z obiektem przekazanym jako argument
  * @param o Obiekt klasy BoardPos z którym ma być porównany obiekt właściciel 
  * @return true gdy obiekty maja taka sama pozycje, false w przeciwnym wypadku.
  */
    public boolean equals(BoardPos o)
    {
    if (o.getMyX() == this.getMyX())
        if(o.getMyY() == this.getMyY())
            return true;
     return false;
    }

 
 
}

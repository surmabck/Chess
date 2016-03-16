package pl.boardPieces.chessmans;



import pl.boardPieces.BoardPos;
import java.io.Serializable;
import java.util.Vector;

/**
 * Klasa figury, która opisuje figure. Klasa abstrakcyjna.
 * @author Bartosz Surma
 */
public class ChessMan implements Serializable{
    private BoardPos position;
    private int myId;
    private static int id=1;
    /**
     * Konstruktor
     */
 public ChessMan(){
    this(1,1);
 }
/**
 * Konstruktor
 * @param x Pozycja x figury na szachownicy
 * @param y Pozycja y figury na szachownicy
 */
 public ChessMan(int x, int y){
     this(x,y,1);
 }
 /**
  * Konstruktor
  * @param x Pozycja x figury na szachownicy
  * @param y Pozycja y figury na szachownicy
  * @param idd Identyfikator figury
  */
 public ChessMan(int x, int y, int idd){
     position = new BoardPos(x,y); 
     setId(idd);
 }
 /**
  * Ustawia kolejne ID obiektu
  */
 public void setId()
 {
        myId = id;
        id++;
 }
 /**
  * Ustawia podany argument jako ID obiektu 
  * @param idd Identyfikator, który ma zostać ustalony
  */
 public void setId(int idd)
 {
     myId = idd;
 }
 /**
  * Zwraca wartość ID figury
  * @return ID figury
  */
 public int getMyId()
 {
     return myId;
 }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
 public ChessMan switchPos(){
    return new ChessMan(7-position.getMyX(),7-position.getMyY());
}
 /**
  * Zwraca pozycje figury na szachownicy
  * @return Pozycja figury
  */
 public BoardPos getPosition(){
     return position.copy();
 }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
 public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos,Vector<BoardPos> myPos){
     return null;
 }
 /**
  * Ustawia pozycje figury na szachownicy
  * @param x Pozycja x na szachoniwcy
  * @param y Pozycja y na szachownicy
  */
 public void setPosition(int x,int y){
     this.position = new BoardPos(x,y);
 }
 /**
  * Ustawia pozycje figury na szachownicy
  * @param p Obiekt klasy BoardPos zawierajacy pozycje figury na szachownicy
  */
 public void setPosition(BoardPos p){
     this.position.setPos(p.getMyX(), p.getMyY());
 }
     /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    @Override
 public Object clone(){
     ChessMan ret = new ChessMan(this.getPosition().getMyX(),this.getPosition().getMyY());
     ret.setId(getMyId());
     return ret;
 }
    
}

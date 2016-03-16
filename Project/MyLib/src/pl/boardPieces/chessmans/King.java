package pl.boardPieces.chessmans;



import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;


/**
 * Klasa figury, która opisuje figure Króla.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class King extends ChessMan{
    /**
     * Konstruktor
     */
    public King(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public King(int x,int y)
    {
        this(x,y,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param idd Identyfikator figury
     */
    public King(int x, int y,int idd){
        super(x,y,idd);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public King switchPos(){
    return new King(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
    public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
        BoardPos thispos = super.getPosition();
        Vector <BoardPos> ret = new Vector<BoardPos>();
        ret.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()));
        ret.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()-1));
        ret.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()+1));
        ret.add(new BoardPos(thispos.getMyX(),thispos.getMyY()+1));
        ret.add(new BoardPos(thispos.getMyX(),thispos.getMyY()-1));
        ret.add(new BoardPos(thispos.getMyX()+1,thispos.getMyY()));
        ret.add(new BoardPos(thispos.getMyX()+1,thispos.getMyY()-1));
        ret.add(new BoardPos(thispos.getMyX()+1,thispos.getMyY()+1));
        Iterator i = ret.iterator();
        while(i.hasNext())
        {
            BoardPos pp = (BoardPos) i.next();
            if (pp.getMyX()<0 || pp.getMyX()>7 || pp.getMyY()<0 || pp.getMyY()>7)
            {
                i.remove();
            }
        }
        i = myPos.iterator();
        while(i.hasNext())
        {
            BoardPos p = (BoardPos) i.next();
            Iterator j = ret.iterator();
            
            while(j.hasNext())
            {
                BoardPos g = (BoardPos) j.next();
                if (p.equals(g)) j.remove();
            }
        }
        
        
        return ret;
    }
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        King ret = new King(this.getPosition().getMyX(),this.getPosition().getMyY());
        return ret;
    }
}

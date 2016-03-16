package pl.boardPieces.chessmans;

import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa figury, która opisuje figure gońca.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class Knight extends ChessMan{
    /**
     * Konstruktor
     */
    public Knight(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public Knight(int x, int y){
        this(x,y,1);
        
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param id Identyfikator figury
     */
    public Knight(int x,int y,int id)
    {
        super(x,y,id);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public Knight switchPos(){
        return new Knight(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
     public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
         Iterator i = enemyPos.iterator();
         BoardPos thispos = super.getPosition();
         Vector<BoardPos> ret = new Vector<BoardPos>();
         ret.add(new BoardPos(thispos.getMyX()-2,thispos.getMyY()-1));
         ret.add(new BoardPos(thispos.getMyX()-2,thispos.getMyY()+1));
         ret.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()-2));
         ret.add(new BoardPos(thispos.getMyX()+1,thispos.getMyY()-2));
         ret.add(new BoardPos(thispos.getMyX()+2,thispos.getMyY()-1));
         ret.add(new BoardPos(thispos.getMyX()+2,thispos.getMyY()+1));
         ret.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()+2));
         ret.add(new BoardPos(thispos.getMyX()+1,thispos.getMyY()+2));
         Iterator j = ret.iterator();
         while(j.hasNext())
            {
            BoardPos pp = (BoardPos) j.next();
            if (pp.getMyX()<0 || pp.getMyX()>7 || pp.getMyY()<0 || pp.getMyY()>7)
            {
                j.remove();
            }
            }
         i = myPos.iterator();
         while(i.hasNext())
         {
            BoardPos p = (BoardPos)i.next();
            j = ret.iterator();
            while(j.hasNext()){
                BoardPos g = (BoardPos) j.next();
                if (g.equals(p)) {
                    j.remove();
                }
                    
            }
             
         }
         return ret;
     }
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        Knight ret = new Knight(this.getPosition().getMyX(),this.getPosition().getMyY());
        ret.setId(getMyId());
        return ret;
    }
}

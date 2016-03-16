package pl.boardPieces.chessmans;

import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa figury, która opisuje figure gońca.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class Pawn extends ChessMan{
    /**
     * Konstruktor
     */
    public Pawn(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public Pawn(int x,int y)
    {
        this(x,y,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param id Identyfikator figury
     */
    public Pawn(int x, int y,int id){
        super(x,y,id);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public Pawn switchPos(){
        return new Pawn(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
    public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
     boolean oneDel = false;
     boolean twoDel = false;
     Vector <BoardPos> p = new Vector<BoardPos>();
     Iterator i = enemyPos.iterator();
     BoardPos thispos = super.getPosition();
     
     p.add(new BoardPos(thispos.getMyX()-1,thispos.getMyY()));
     if (thispos.getMyX()==6)
         p.add(new BoardPos(thispos.getMyX()-2,thispos.getMyY()));
     i = enemyPos.iterator();
     while (i.hasNext())
     {
         BoardPos currPos = (BoardPos) i.next();
         currPos = currPos.switchPos();
         if ((currPos.getMyX()+1==thispos.getMyX())&&
                 (currPos.getMyY()-1 == thispos.getMyY() || 
                 currPos.getMyY()+1 == thispos.getMyY())){
             p.add(currPos);
         }
         if(currPos.getMyY() == thispos.getMyY()){
            if (currPos.getMyX()+1 == thispos.getMyX()){
                twoDel = true;
                oneDel = true;
            }
            if (currPos.getMyX()+2==thispos.getMyX() && thispos.getMyX()==6){
                oneDel = true;
            }   
         }

     }
     i = myPos.iterator();
     while (i.hasNext())
     {
         BoardPos currPos = (BoardPos) i.next();
         if (currPos.getMyY() == thispos.getMyY())
         {
             if (currPos.getMyX()+1 == thispos.getMyX())
             {
                 twoDel = true;
                 oneDel = true;
             }
             else if (currPos.getMyX()+2 == thispos.getMyX()){
                 oneDel = true;
             }
         }
     }
     i = p.iterator();
     while(i.hasNext())
     {
         BoardPos pp = (BoardPos) i.next();
         if (pp.getMyY() == thispos.getMyY())
         {
            if (twoDel)
            {
                if(pp.getMyX()+1 == thispos.getMyX())
                {
                    i.remove();
                    twoDel = false;
                    continue;
                }
            }
            if (oneDel)
            {
                if (pp.getMyX()+2 == thispos.getMyX())
                {
                    i.remove();
                    oneDel = false;
                }
            }
         }
         if (pp.getMyX()<0 || pp.getMyX()>7 || pp.getMyY()<0 || pp.getMyY()>7)
         {
             i.remove();
         }
     }
     return p;
    }
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        Pawn ret = new Pawn(this.getPosition().getMyX(),this.getPosition().getMyY());
        ret.setId(getMyId());
        return ret;
    }
    
}

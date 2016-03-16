package pl.boardPieces.chessmans;

import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;


/**
 * Klasa figury, która opisuje figure gońca.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class Rook extends ChessMan {
   /**
     * Konstruktor
     */
    public Rook(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public Rook(int x,int y)
    {
        this(x,y,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param id Identyfikator figury
     */
    public Rook(int x, int y,int id){
        super(x,y,id);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public Rook switchPos(){
    return new Rook(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
    public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
        BoardPos   myLeft= new BoardPos(0,-1);
        BoardPos   myRight = new BoardPos(0,8);
        BoardPos   myTop = new BoardPos(-1,0);
        BoardPos   myBottom = new BoardPos(8,0);
        BoardPos thispos = super.getPosition();
        Iterator i = myPos.iterator();
        while(i.hasNext())
        {
           BoardPos p = (BoardPos) i.next();
            if (p.getMyX() == thispos.getMyX())
            {
               if (p.getMyY()<thispos.getMyY())
               {
                        if(p.getMyY()>myLeft.getMyY()) 
                            myLeft = p;
                }
                if (p.getMyY()>thispos.getMyY())
                {
                        if(p.getMyY()<myRight.getMyY()) 
                            myRight = p;
                }
               
            }
            else if ( p.getMyY() == thispos.getMyY())
            {
               if (p.getMyX()<thispos.getMyX())
               {
                        if(p.getMyX()>myTop.getMyX()) 
                            myTop = p;
                }
                if (p.getMyX()>thispos.getMyX())
                {
                        if(p.getMyX()<myBottom.getMyX()) 
                            myBottom = p;
                }                
            }
        }
        myLeft.setPos(myLeft.getMyX(), myLeft.getMyY()+1);
        myRight.setPos(myRight.getMyX(), myRight.getMyY()-1);
        myTop.setPos(myTop.getMyX()+1, myTop.getMyY());
        myBottom.setPos(myBottom.getMyX()-1, myBottom.getMyY());
         
        i = enemyPos.iterator();
        while(i.hasNext())
        {
           BoardPos p = (BoardPos) i.next();
           p = p.switchPos();
            if (p.getMyX() == thispos.getMyX())
            {
               if (p.getMyY()<thispos.getMyY())
               {
                        if(p.getMyY()>=myLeft.getMyY()) 
                            myLeft = p;
                }
                if (p.getMyY()>thispos.getMyY())
                {
                        if(p.getMyY()<=myRight.getMyY()) 
                            myRight = p;
   
                }
               
            }
            else if ( p.getMyY() == thispos.getMyY())
            {
               if (p.getMyX()<thispos.getMyX())
               {
                        if(p.getMyX()>=myTop.getMyX()) 
                            myTop = p;
                }
                if (p.getMyX()>thispos.getMyX())
                {
                        if(p.getMyX()<myBottom.getMyX()) 
                            myBottom = p;
                }                
            }
        }
        Vector <BoardPos>ret = new Vector<BoardPos>();
        for (int it = 0; it<8;it++)
        {
           if (it < thispos.getMyY() && it>=myLeft.getMyY())
               ret.add(new BoardPos(thispos.getMyX(),it));
           if (it > thispos.getMyY() && it<=myRight.getMyY())
               ret.add(new BoardPos(thispos.getMyX(),it));
           if (it < thispos.getMyX() && it>=myTop.getMyX())
               ret.add(new BoardPos(it,thispos.getMyY()));
           if (it > thispos.getMyX() && it<=myBottom.getMyX())
               ret.add(new BoardPos(it,thispos.getMyY()));
        }
        
        return ret;
    }
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        Rook ret = new Rook(this.getPosition().getMyX(),this.getPosition().getMyY());
        ret.setId(getMyId());
        return ret;
    }
}

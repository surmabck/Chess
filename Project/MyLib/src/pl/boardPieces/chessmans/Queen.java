package pl.boardPieces.chessmans;

import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;
/**
 * Klasa figury, która opisuje figure gońca.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class Queen extends ChessMan {
    /**
     * Konstruktor
     */
    public Queen(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public Queen(int x, int y)
    {
        this(x,y,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param id Identyfikator figury
     */
    public Queen(int x, int y,int id){
        super(x,y,id);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public Queen switchPos(){
        return new Queen(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    private Vector<BoardPos> getMovesDiagonal(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
        Vector <BoardPos> ret = new Vector<BoardPos>();     
        BoardPos   myLeft2bottom = new BoardPos(8,0);
        BoardPos   myRight2bottom = new BoardPos(8,0);
        BoardPos   myRight2top = new BoardPos(-1,0);
        BoardPos   myLeft2top = new BoardPos(-1,0);
        
        BoardPos thispos = super.getPosition();
        Iterator k = myPos.iterator();
        while(k.hasNext())
        {
            BoardPos p = (BoardPos) k.next();
            if (Math.abs(p.getMyX()-thispos.getMyX()) == Math.abs(p.getMyY()-thispos.getMyY()))
            {
                if (p.getMyX()<thispos.getMyX())
                {
                    if (p.getMyY()<thispos.getMyY())
                    {
                        //left2top
                            if (myLeft2top.getMyX()<p.getMyX())
                                myLeft2top = p;
                    }
                    else if (p.getMyY()>thispos.getMyY())
                    {
                        //right2top
                            if (myRight2top.getMyX()<p.getMyX())
                                myRight2top = p;                     
                    }
                }
                else if (p.getMyX()>thispos.getMyX())
                {
                    if (p.getMyY()<thispos.getMyY())
                    {
                            if (myLeft2bottom.getMyX()>p.getMyX())
                                myLeft2bottom = p;
                    }
                    else if (p.getMyY()>thispos.getMyY())
                    {
                        //right2bottom
                            if (myRight2bottom.getMyX()>p.getMyX())
                                myRight2bottom = p;
                    }
                    
                }
            }
        }
        myLeft2top.setPos(myLeft2top.getMyX()+1, myLeft2top.getMyY());
        myLeft2bottom.setPos(myLeft2bottom.getMyX()-1, myLeft2bottom.getMyY());
        myRight2top.setPos(myRight2top.getMyX()+1, myRight2top.getMyY());
        myRight2bottom.setPos(myRight2bottom.getMyX()-1, myRight2bottom.getMyY());
        
        k = enemyPos.iterator();
        while(k.hasNext())
        {
            BoardPos p = (BoardPos) k.next();
            p = p.switchPos();
            if (Math.abs(p.getMyX()-thispos.getMyX()) == Math.abs(p.getMyY()-thispos.getMyY()))
            {
                if (p.getMyX()<thispos.getMyX())
                {
                    if (p.getMyY()<thispos.getMyY())
                    {
                        //left2top
                            if (myLeft2top.getMyX()<=p.getMyX())
                                myLeft2top = p;
                    }
                    else if (p.getMyY()>thispos.getMyY())
                    {
                        //right2top
                            if (myRight2top.getMyX()<=p.getMyX())
                                myRight2top = p;                    
                    }
                }
                else if (p.getMyX()>thispos.getMyX())
                {
                    if (p.getMyY()<thispos.getMyY())
                    {
                        //left2bottom
                            if (myLeft2bottom.getMyX()>=p.getMyX())
                                myLeft2bottom = p;
                    }
                    else if (p.getMyY()>thispos.getMyY())
                    {
                        //right2bottom
                            if (myRight2bottom.getMyX()>=p.getMyX())
                                myRight2bottom = p;
                    }
                    
                }
            }
        }
            for (int i=0;i<8;i++)
            {
                for (int j = 0;j<8;j++)
                {
                    if (Math.abs(thispos.getMyX()-i) == Math.abs(thispos.getMyY()-j))
                    {
                        if (i<thispos.getMyX() && j<thispos.getMyY())
                        {
                            if (i>=myLeft2top.getMyX())
                                ret.add(new BoardPos(i,j));
                        }
                        if (i<thispos.getMyX() && j>thispos.getMyY())
                        {
                            if (i>=myRight2top.getMyX())
                                ret.add(new BoardPos(i,j));
                        }
                        if (i>thispos.getMyX() && j<thispos.getMyY())
                        {
                            if (i<=myLeft2bottom.getMyX())
                                ret.add(new BoardPos(i,j));
                        }
                        if (i>thispos.getMyX() && j>thispos.getMyY())
                        {
                            if (i<=myRight2bottom.getMyX())
                                ret.add(new BoardPos(i,j));
                        }
                    }
                        

                }
            }
        return ret;
    }
    private Vector<BoardPos> getMovesStraight(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
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
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
    public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos)
    {
        Vector <BoardPos>ret = new Vector<BoardPos>();
        ret.addAll(getMovesStraight(enemyPos,myPos));
        ret.addAll(getMovesDiagonal(enemyPos,myPos));    
        return ret;
    }
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        Queen ret = new Queen(this.getPosition().getMyX(),this.getPosition().getMyY());
        ret.setId(getMyId());
        return ret;
    }
}

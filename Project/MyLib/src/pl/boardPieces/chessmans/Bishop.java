package pl.boardPieces.chessmans;

import pl.boardPieces.BoardPos;
import java.util.Iterator;
import java.util.Vector;


/**
 * Klasa figury, która opisuje figure gońca.
 * Dziedziczy po klasie ogólnej opisujacej figury, tzn. ChessMan
 * @author Bartosz Surma
 */
public class Bishop extends ChessMan {
    /**
     * Konstruktor
     */
    public Bishop(){
        this(1,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     */
    public Bishop(int x, int y){
        this(x,y,1);
    }
    /**
     * Konstruktor
     * @param x Pozycja x figury
     * @param y Pozycja y figury
     * @param id Identyfikator figury
     */
    public Bishop(int x,int y, int id)
    {
        super(x,y,id);
    }
    /**
     * Odbicie lustrzane położenia figury na szachownicy.
     * @return Obiekt tej klasy po odbiciu.
     */
    public Bishop switchPos(){
        return new Bishop(7-super.getPosition().getMyX(),7-super.getPosition().getMyY());
    }
    /**
     * Zwraca możliwe ruchy, które może wykonać ta figura
     * @param enemyPos Vector obiektów figur przeciwnika
     * @param myPos Vector obiektów figur gracza
     * @return Vector zawierajacy możliwe ruchy
     */
    public Vector<BoardPos> getMoves(Vector<BoardPos> enemyPos, Vector<BoardPos> myPos){
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
    /**
     * Wykonuje głębokie kopiowanie
     * @return Zwraca obiekt po skopiowaniu.
     */
    public Object clone(){
        Bishop ret = new Bishop(this.getPosition().getMyX(),this.getPosition().getMyY());
        ret.setId(getMyId());
        return ret;
    }
}

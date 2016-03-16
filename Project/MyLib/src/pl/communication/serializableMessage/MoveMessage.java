package pl.communication.serializableMessage;

import pl.boardPieces.BoardPos;
import pl.boardPieces.chessmans.ChessMan;

/**
 * Klasa wiadomości serializowanej która dziedziczy po klasie Message. 
 * Służy do przesyłania wiadomości na temat wykonanego ruchu.
 * @author Krzysztof Surdy
 */
public class MoveMessage extends Message {
    private final ChessMan chess;
    private final BoardPos positionToMove;
    private final String username;
    /**
     * Konstruktor
     * @param chess Figura która zmienia pozycje
     * @param position Pozycja, na która przesuwa się figura
     * @param username Nazwa użytkownika, który wykonał ruch
     */
    public MoveMessage(ChessMan chess, BoardPos position,String username)
    {
        super(MESMOVE);
        this.chess = chess;
        this.positionToMove = position;
        this.username = username;
    }
    /**
     * Zwraca figure, która zmienia pozycja
     * @return Obiekt ChessMan, który zmienia pozycje
     */
    public ChessMan getChess(){
        return this.chess;
    }
    /**
     * Zwraca pozycje na która zostanie przesunięta figura chessman
     * @return Pozycja do przesunięcia figury
     */
    public BoardPos getPositionToMove()
    {
        return this.positionToMove;
    }
    /**
     * Zwraca nazwe użytkownika, który wysyła wiadomość
     * @return 
     */
    public String getUsername(){
        return this.username;
    }
    /**
     * Zamienia wiadomość na Tekst
     * @return Tekst opisujacy wiadomość
     */
    @Override
    public String toString()
    {
        char label[]  =  {'A','B','C','D','E','F','G','H'};
        char l[] = new char[2];
        BoardPos p = chess.getPosition();
        for (int i=0;i<2;i++)
        {
            switch (p.getMyX())
            {
                case 0: l[i] = label[7]; break;
                case 1: l[i] = label[6]; break;
                case 2: l[i] = label[5]; break;
                case 3: l[i] = label[4]; break;
                case 4: l[i] = label[3]; break;
                case 5: l[i] = label[2]; break;
                case 6: l[i] = label[1]; break;
                case 7: l[i] = label[0]; break; 
            }
            p = positionToMove;
        }
        return "<"+username+"> "+"["+(7-chess.getPosition().getMyX())+l[0]+"]"+"->"+"["+(7-positionToMove.getMyX())+l[1]+"]"; 
    }
     
    
}

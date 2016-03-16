/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.communication.database;

import pl.boardPieces.chessmans.Bishop;
import pl.boardPieces.chessmans.King;
import pl.boardPieces.chessmans.ChessMan;
import pl.boardPieces.chessmans.Pawn;
import pl.boardPieces.chessmans.Knight;
import pl.boardPieces.chessmans.Rook;
import pl.boardPieces.chessmans.Queen;
import pl.boardPieces.BoardPos;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa która służy do komunikacji z baza danych. Tworzy swoj obiekt na podstawie pliku databaseConnection.properties
 * @author Krzysztof Surdy
 */
public class DatabaseConnector {
    private  String dbdriv ;
    private String dburl;
    private String dbusr ;
    private String dbpass ;
    /**
     * Stala okreslajaca możliwosc zbicia innych pionków przy wykonywaniu ruchu
     */
    public static final boolean NOKILL = false; 
    /**
     * Stala okreslajaca niemożność zbicia innych pionków przy wykonywaniu ruchu
     */
    public static final boolean KILL = true;
    private  Connection con;
    private PreparedStatement loginStatement;
    private PreparedStatement registerStatement;
    private PreparedStatement gameStatement;
    private PreparedStatement gameEndStatement;
    private PreparedStatement moveStatement;
    private PreparedStatement positionStatement;
    private PreparedStatement myPositionStatement;
    /**
     * Konstruktor. Konstruuje obiekt na podstawie pliku databaseConnection.properties
     */
    public DatabaseConnector ()
    {
        try { 
            Properties props = new Properties();
            InputStream in = new FileInputStream("databaseConnection.properties");
            props.load(in);
            dburl = props.getProperty("jdbc.url");
            dbusr = props.getProperty("jdbc.username");
            dbpass = props.getProperty("jdbc.password");
            dbdriv = props.getProperty("jdbc.drivers");
            if (dbdriv!=null)
                System.setProperty("jdbc.drivers", dbdriv);
                con = DriverManager.getConnection(dburl, dbusr, dbpass);
            loginStatement = con.prepareStatement("Select login, haslo from gracz where login=? and haslo=?");
            registerStatement = con.prepareStatement("Insert into gracz (login,haslo) values (?,?)");
            gameStatement = con.prepareStatement("Insert into gra(id,id_gracz1,id_gracz2,data_rozpoczecia,data_zakonczenia)"
                    + " values(?,?,?,?,?)");
            gameEndStatement = con.prepareStatement("Update gra SET data_zakonczenia =? where id=?");
            moveStatement = con.prepareStatement("Insert into pionki_w_grze"
                    + "(id_gra,id_gracz,id_figura,x,y,data_postawienia,typ,id_figury_gracza)"
                    + " values (?,?,?,?,?,?,?,?)");
            positionStatement = con.prepareStatement("select typ,id_figura,id_figury_gracza,max(data_postawienia) dat "
                    + " from pionki_w_grze where id_gracz =  ? and id_gra = ? and x = ? and y=? group by typ,id_figura,id_figury_gracza");
            myPositionStatement = con.prepareStatement(
                    " select t.id_figura, t.id_figury_gracza,p.x,p.y,max (data_postawienia) dat from " +
                    "    (select id_figura,id_figury_gracza,id_gra,id_gracz" +
                    "    from pionki_w_grze " +
                    "    where id_gracz =  ? and id_gra = ?  and typ='ruch' " +
                    "    minus " +
                    "    select id_figura,id_figury_gracza,id_gra,id_gracz" +
                    "    from pionki_w_grze " +
                    "    where id_gracz =  ? and id_gra = ?  and typ='zbicie'" +
                    "    ) t join pionki_w_grze p on p.id_gra = t.id_gra and p.id_gracz = t.id_gracz and p.id_figura = t.id_figura and p.id_figury_gracza = t.id_figury_gracza" +
                    "    group by t.id_figura,t.id_figury_gracza,p.x,p.y order by t.id_figury_gracza,dat desc");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
         
        }
        catch (IOException ex) {
               System.out.println("Nie mozna polaczyc sie z baza danych");
               System.exit(0);
        }
        catch (NullPointerException e){
               System.out.println("Nie mozna polaczyc sie z baza danych");
               System.exit(0);
        }
    }
    /**
     * Uwierzytelnia użytkownika poprzez pare login i password. 
     * @param login Login użytkownia. Jedna zmienna z pary identyfikujacej użytkownika.
     * @param password Haslo użytkownika. Druga zmienna z pary identyfikujacej użytkownika.
     * @return true w przypadku gdy użytkownik podany przez pare login, passowrd jest w bazie danych, false w przeciwnym wypadku
     */
    public boolean loginUser(String login,String password)
    {
        if (isInDatabase(login,password)==1)
        {
            return true;
        }
        return false;
    }
    private int isInDatabase(String login, String password)
    {
        int amount=0;
        String log ="",pas="";
        try {
            loginStatement.setString(1, login);
            loginStatement.setString(2, password);
            ResultSet rs = loginStatement.executeQuery();
            if (rs.next()) {    
                  log = rs.getString("LOGIN");
                  pas = rs.getString("HASLO");
                  amount++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }
    /**
     * Sprawdza możliwosc rejestracji nowego użytkownika. Gdy jest taka możliwość, robi to, a następnie loguje użytkownika
     * @param login Login użytkownika.
     * @param password Haslo użytkownika.
     * @return true w przypadku poprawnej rejestracji, false w przeciwnym wypadku.
     */
    public boolean registerNewUser(String login, String password)
    {
        int ret = 0;
        boolean bret = false;
        if (isInDatabase(login,password)==0)
        {
            try {
                registerStatement.setString(1, login);
                registerStatement.setString(2, password);
                ret = registerStatement.executeUpdate();
                loginUser(login,password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (ret==0) bret = false;
        else bret=true;
        return bret;
    }
    /**
     * Tworzy nowa gre w bazie danych.
     * @param gameId ID gry, która ma być stworzona. 
     * @param username1 Nazwa użytkownika nr1 w pokoju
     * @param username2 Nazwa użytkownika nr2 w pokoju
     */
    public void createGame(int gameId,String username1,String username2)
    {
        int ret =0;
        java.util.Date myDate = new java.util.Date();
        java.sql.Timestamp myT = new java.sql.Timestamp(myDate.getTime());
        try {
            gameStatement.setInt(1, gameId);
            gameStatement.setString(2, username1);
            gameStatement.setString(3, username2);
            gameStatement.setTimestamp(4, myT);
            gameStatement.setTimestamp(5, null);
            ret = gameStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Kończy gre, dodaje date zakończenia w bazie danych
     * @param gameId ID gry która ma zostać zakończona
     */
    public void endGame(int gameId)
    {
        java.util.Date myDate = new java.util.Date();
        java.sql.Timestamp myT = new java.sql.Timestamp(myDate.getTime());
        try {
            gameEndStatement.setTimestamp(1, myT);
            gameEndStatement.setInt(2, gameId);
            gameEndStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Zwraca następny w kolejności nr id gry. 
     * @return nr ID gry następnej w kolejności.
     */
    public int getNextGameId()
    {
        int nextId=1;
        try {
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select max(id) from gra");
            if (rs.next()) {  
                nextId = rs.getInt(1);
                nextId++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextId;
    }
    private int checkPosition(String name,int gameId, BoardPos pos,java.sql.Timestamp myT )
    {
        boolean toErase = false;
        String typ = "";
        int chessId=0;
        int privateChessId=0;
        BoardPos currPos = pos.switchPos();
        try {
            positionStatement.setString(1,name);
            positionStatement.setInt(2,gameId);
            positionStatement.setInt(3, currPos.getMyX());
            positionStatement.setInt(4, currPos.getMyY());
            ResultSet rs = positionStatement.executeQuery();            
            if(rs.next())
            {
                typ = rs.getString(1);
                chessId = rs.getInt(2);
                privateChessId = rs.getInt(3);
                toErase = true;
            }
            if (toErase)
            {
                if (typ.equalsIgnoreCase("ruch"));
                {
                    moveStatement.setInt(1, gameId);
                    moveStatement.setString(2,name);
                    moveStatement.setInt(3, chessId);
                    moveStatement.setInt(4, currPos.getMyX());
                    moveStatement.setInt(5, currPos.getMyY());
                    moveStatement.setTimestamp(6, myT);
                    moveStatement.setString(7, "zbicie");
                    moveStatement.setInt(8, privateChessId);
                    moveStatement.executeUpdate(); 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    /**
     * Zwraca uklad szachownicy danego użytkownika w danej grze.
     * @param name Nazwa użytkownika dla którego ma zostać zwrócona szachownica
     * @param gameId ID gry, dla której ma zostać zwrócona szachownica
     * @return Vector z obiektami ChessMan opisujacy układ szachownicy.
     */
    public Vector<ChessMan> getMyChessBoard(String name, int gameId)
    {
            Vector<ChessMan> v;
            v = new Vector<ChessMan>();
        try {
            int idF;
            int idFG=0;
            int x,y;
            java.sql.Timestamp myT = null;
            myPositionStatement.setString(1, name);
            myPositionStatement.setInt(2, gameId);
            myPositionStatement.setString(3, name);
            myPositionStatement.setInt(4, gameId);
            ResultSet rs = myPositionStatement.executeQuery(); 
            while(rs.next())
            {
                if (idFG==rs.getInt(2))
                {
                    continue;
                }
                    idF=rs.getInt(1);
                    idFG=rs.getInt(2);
                    x=rs.getInt(3);
                    y=rs.getInt(4);
                    myT =rs.getTimestamp(5);
                    if (idF==1) v.add(new King(x,y,idFG));
                    else if(idF==2) v.add(new Queen(x,y,idFG));
                    else if(idF==3) v.add(new Pawn(x,y,idFG));
                    else if(idF==4) v.add(new Rook(x,y,idFG));
                    else if(idF==5) v.add(new Bishop(x,y,idFG));
                    else if(idF==6) v.add(new Knight(x,y,idFG));  

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    /**
     * Zapisuje w bazie danych ruch użytkownika. Funkcja jest synchronizowana.
     * @param name Nazwa użytkownika, dla którego ma zostać wpisany ruch
     * @param enemyName Nazwa przeciwnika
     * @param gameId Nr ID gry.
     * @param chessman Obiekt ChessMan, który wykonuje ruch
     * @param posToMove Pozycja, na która ma zostać przesunięty obiekt chessman
     * @param flag flaga określa, czy przy ruchu może dojść do zbicia. Przyjmuje stałe NOKILL oraz KILL
     */
    public synchronized void makeMove(String name,String enemyName,int gameId,ChessMan chessman, BoardPos posToMove,boolean flag)
    {
            int chessId =0;
            if (chessman instanceof Pawn) chessId = 3;
            else if (chessman instanceof King) chessId = 1;
            else if (chessman instanceof Queen) chessId=2;
            else if (chessman instanceof Rook) chessId = 4;
            else if (chessman instanceof Bishop) chessId = 5;
            else if (chessman instanceof Knight) chessId=6;
            java.util.Date myDate = new java.util.Date();
            java.sql.Timestamp myT = new java.sql.Timestamp(myDate.getTime());
        try {

            moveStatement.setInt(1, gameId);
            moveStatement.setString(2,name);
            moveStatement.setInt(3, chessId);
            moveStatement.setInt(4, posToMove.getMyX());
            moveStatement.setInt(5, posToMove.getMyY());
            moveStatement.setTimestamp(6, myT);
            moveStatement.setString(7, "ruch");
            moveStatement.setInt(8, chessman.getMyId());
            moveStatement.executeUpdate();
            
            if (flag)
            {
                int idC;
                idC=checkPosition(enemyName,gameId,posToMove,myT);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gui;

import pl.boardPieces.BoardPos;
import pl.boardPieces.chessmans.ChessMan;
import pl.communication.Client;
import pl.communication.serializableMessage.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pl.logic.management.Manager;
import pl.logic.management.Player;

/**
 * Klasa głównego okna. Dizedziczy po klasie swinga JFrame.
 * @author Bartosz Surma
 */
public class NewJFrame extends JFrame {
    private int xMouse;
    private int yMouse;
    private CardLayout cl;
    private Client client;
    private Player player;
    private Player enemy;
    private Manager manager;

    /**
     * Konstruktor. Tworzy nowa ramkę.
     */
    public NewJFrame() {

        client = new Client(this); 
        initComponents();

        manager = new Manager(this,szachownica,player,enemy);
        client.init("login","password",manager);
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMainFrame1 = new pl.gui.JMainFrame("background.jpg");
        movealbeLabel = new javax.swing.JLabel();
        minimizeButton = new pl.gui.JMainFrame("min.png");
        closeButton = new pl.gui.JMainFrame("close.png");
        jMainFrame4 = new pl.gui.JMainFrame();
        jMainFrame2 = new pl.gui.JMainFrame();
        loginLabel = new javax.swing.JLabel();
        submitLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        loginField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        szachownica = new pl.gui.Szachownica();
        jPanel3 = new javax.swing.JPanel();
        chatField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();
        statusLabel = new javax.swing.JLabel();
        gameNameLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        gratzLabel = new javax.swing.JLabel();
        enemyLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jMainFrame1.setPreferredSize(new java.awt.Dimension(800, 600));

        movealbeLabel.setPreferredSize(new java.awt.Dimension(10, 10));
        movealbeLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                movealbeLabelMouseDragged(evt);
            }
        });
        movealbeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                movealbeLabelMousePressed(evt);
            }
        });

        minimizeButton.setOpaque(false);
        minimizeButton.setPreferredSize(new java.awt.Dimension(25, 25));
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout minimizeButtonLayout = new javax.swing.GroupLayout(minimizeButton);
        minimizeButton.setLayout(minimizeButtonLayout);
        minimizeButtonLayout.setHorizontalGroup(
            minimizeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        minimizeButtonLayout.setVerticalGroup(
            minimizeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        closeButton.setOpaque(false);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout closeButtonLayout = new javax.swing.GroupLayout(closeButton);
        closeButton.setLayout(closeButtonLayout);
        closeButtonLayout.setHorizontalGroup(
            closeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        closeButtonLayout.setVerticalGroup(
            closeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMainFrame4.setBackground(new java.awt.Color(0, 0, 0));
        jMainFrame4.setOpaque(false);
        jMainFrame4.setLayout(new java.awt.CardLayout());

        jMainFrame2.setOpaque(false);
        jMainFrame2.setPreferredSize(new java.awt.Dimension(800, 600));

        loginLabel.setFont(new java.awt.Font("Arial", 1, 42)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(153, 29, 29));
        loginLabel.setText("Zaloguj");

        submitLabel.setFont(new java.awt.Font("Arial", 1, 42)); // NOI18N
        submitLabel.setForeground(new java.awt.Color(153, 30, 30));
        submitLabel.setText("Zarejestruj");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        loginField.setBackground(new java.awt.Color(23, 23, 23));
        loginField.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        loginField.setForeground(new java.awt.Color(204, 204, 204));
        loginField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginField.setText("LOGIN");
        loginField.setDoubleBuffered(true);

        passwordField.setBackground(new java.awt.Color(23, 23, 23));
        passwordField.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        passwordField.setForeground(new java.awt.Color(204, 204, 204));
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordField.setText("PASSWORD");

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setBackground(new java.awt.Color(51, 49, 49));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(205, 205, 205));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Gra Chess-Master autorstwa Bartosz Surma i Krzysztofa Surdy. ");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextArea1.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(jTextArea1);

        loginButton.setBackground(new java.awt.Color(48, 46, 46));
        loginButton.setText("Login");
        loginButton.setAlignmentY(0.0F);
        loginButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 14, 2, 14));
        loginButton.setFocusPainted(false);
        loginButton.setRolloverEnabled(false);
        loginButton.setVerifyInputWhenFocusTarget(false);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setBackground(new java.awt.Color(48, 46, 46));
        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jMainFrame2Layout = new javax.swing.GroupLayout(jMainFrame2);
        jMainFrame2.setLayout(jMainFrame2Layout);
        jMainFrame2Layout.setHorizontalGroup(
            jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainFrame2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitLabel))
                .addGap(55, 55, 55))
            .addGroup(jMainFrame2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(registerButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMainFrame2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {loginField, passwordField});

        jMainFrame2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {loginButton, registerButton});

        jMainFrame2Layout.setVerticalGroup(
            jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainFrame2Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginLabel)
                    .addComponent(submitLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainFrame2Layout.createSequentialGroup()
                        .addComponent(loginField)
                        .addGap(18, 18, 18)
                        .addComponent(passwordField)
                        .addGap(76, 76, 76))
                    .addGroup(jMainFrame2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jMainFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerButton)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103))
            .addGroup(jMainFrame2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMainFrame2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {loginButton, registerButton});

        jMainFrame4.add(jMainFrame2, "card2");

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 600));

        szachownica.setOpaque(false);

        jPanel3.setOpaque(false);

        chatField.setBackground(new java.awt.Color(23, 23, 23));
        chatField.setForeground(new java.awt.Color(156, 156, 156));
        chatField.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(143, 143, 143)));
        chatField.setOpaque(true);
        chatField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatFieldMouseClicked(evt);
            }
        });

        sendButton.setBackground(new java.awt.Color(48, 46, 46));
        sendButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        sendButton.setText("SEND");
        sendButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane2.setBorder(null);

        chatTextArea.setEditable(false);
        chatTextArea.setBackground(new java.awt.Color(23, 23, 23));
        chatTextArea.setColumns(20);
        chatTextArea.setForeground(new java.awt.Color(156, 156, 156));
        chatTextArea.setLineWrap(true);
        chatTextArea.setRows(5);
        chatTextArea.setWrapStyleWord(true);
        chatTextArea.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(143, 143, 143)));
        chatTextArea.setFocusable(false);
        chatTextArea.setOpaque(true);
        jScrollPane2.setViewportView(chatTextArea);

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(205, 205, 205));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("TRWA PAROWANIE GRACZY");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chatField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chatField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        gameNameLabel.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        gameNameLabel.setForeground(new java.awt.Color(205, 205, 205));
        gameNameLabel.setText("MyChessMaster");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(gameNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(26, Short.MAX_VALUE)
                    .addComponent(szachownica, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameNameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(szachownica, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        jMainFrame4.add(jPanel2, "card3");

        jPanel4.setOpaque(false);

        gratzLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        gratzLabel.setForeground(new java.awt.Color(205, 205, 205));
        gratzLabel.setText("Wygrałeś z ");

        enemyLabel.setFont(new java.awt.Font("Tahoma", 3, 56)); // NOI18N
        enemyLabel.setForeground(new java.awt.Color(153, 29, 29));
        enemyLabel.setText("enemyname");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(205, 205, 205));
        jLabel1.setText("MyChessMaster");

        jButton1.setBackground(new java.awt.Color(48, 46, 46));
        jButton1.setText("Graj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(48, 46, 46));
        jButton2.setText("Wyloguj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel6.setOpaque(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jScrollPane3.setBorder(null);

        jTextArea2.setBackground(new java.awt.Color(51, 49, 49));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(205, 205, 205));
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Naciśnij 'GRAJ'  aby znaleźć nowego przeciwnika i zmierzyć się z nim!");
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea2);

        jScrollPane5.setBorder(null);

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new java.awt.Color(51, 49, 49));
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextArea4.setForeground(new java.awt.Color(205, 205, 205));
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText("Naciśnij 'WYLOGUJ' aby zmienić konto na którym grasz!");
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setFocusable(false);
        jScrollPane5.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(gratzLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(39, 39, 39)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enemyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane3, jScrollPane5});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gratzLabel)
                            .addComponent(enemyLabel))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(552, 552, 552))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane3, jScrollPane5});

        jMainFrame4.add(jPanel4, "card4");

        javax.swing.GroupLayout jMainFrame1Layout = new javax.swing.GroupLayout(jMainFrame1);
        jMainFrame1.setLayout(jMainFrame1Layout);
        jMainFrame1Layout.setHorizontalGroup(
            jMainFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainFrame1Layout.createSequentialGroup()
                .addGroup(jMainFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainFrame1Layout.createSequentialGroup()
                        .addComponent(movealbeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jMainFrame4, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jMainFrame1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, minimizeButton});

        jMainFrame1Layout.setVerticalGroup(
            jMainFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainFrame1Layout.createSequentialGroup()
                .addGroup(jMainFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movealbeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jMainFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jMainFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMainFrame4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMainFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMainFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Metoda obsługujaca klikniecie przycisku wyślij(send)
     * @param evt Obiekt zdarzenia
     */
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        client.writeMessage(new TextMessage(chatField.getText()));
        putChatMessage("<"+client.getUsername()+">"+chatField.getText());
        chatField.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed
    /**
     * Metoda Obslugujaca klikniecie w chat area
     * @param evt Obiekt zdarzenia
     */
    private void chatFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatFieldMouseClicked
        chatTextArea.setFocusable(false);
    }//GEN-LAST:event_chatFieldMouseClicked
    /**
     * Metoda obsługujaca klikniecie w przycisk logujacy
     * @param evt Obiekt zdarzenia
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        new Thread()
        {
            public void run()
            {
                client.setUsername(loginField.getText());
                client.setPassword(new String(passwordField.getPassword()));
                client.login();
            }
        }.start();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void closeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMousePressed
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_closeButtonMousePressed
    @Override
    protected void processWindowEvent(java.awt.event.WindowEvent e)
    {
        if(e.getID() == WindowEvent.WINDOW_CLOSING){
            if (client.isConnected())
            {
                client.writeMessage(new AuthMessage(AuthMessage.LOGOUT,client.getUsername(),client.getPassword()));
            }
            dispose();
            System.exit(0);
            
        }
    }
    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
        this.setState(NewJFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    private void movealbeLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movealbeLabelMousePressed
        xMouse=evt.getX();
        yMouse=evt.getY();
    }//GEN-LAST:event_movealbeLabelMousePressed

    private void movealbeLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movealbeLabelMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xMouse, y-yMouse);
    }//GEN-LAST:event_movealbeLabelMouseDragged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        manager.reSit();    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        new Thread()
        {
            public void run()
            {
                client.setUsername(loginField.getText());
                client.setPassword(new String(passwordField.getPassword()));
                client.register();
            }
        }.start();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        manager.logOut();
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Ustawia tekst etykiety statusu
     * @param text Tekst do ustawienia
     */
    public void setStatusLabel(String text)
    {
        statusLabel.setText(text);
    }
    /**
     * Czysci czat.
     */
    public void clearChat()
    {
        setStatusLabel("TRWA PAROWANIE GRACZY");
        chatTextArea.setText("");
    }
    /**
     * Zwraca tekst z etykiety statusu.
     * @return Text etykiety statusu
     */
    public String getStatusLabelText()
    {
        return statusLabel.getText();
    }
    /**
     * Ustawia możliwość focusu na elementy gry.
     * @param p true ustawia możliwość focusu na elementy gry, false usuwa tak możliwość.
     */
    public void gameFocus(boolean p)
    {
        szachownica.setFocus(p);
        chatField.setFocusable(p);
        chatTextArea.setFocusable(p);
        sendButton.setFocusable(p);
    }
    /**
     * Ustawia karte podsumowania
     * @param sum Ustawia etykiete podsumowania (gratuliacji)
     * @param enemy Ustawia etykiete przeciwnika ( z nazwa Przeciwnika)
     */
    public void summationCard(String sum,String enemy)
    {
        gratzLabel.setText(sum);
        enemyLabel.setText(enemy);
        changeCard("card4");
    }
    /**
     * Zmienia aktualnie wyświetlana karte na ta podana w parametrze
     * @param card Nazwa karty do ustawienia
     */
    public void changeCard(String card)
    {
       
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                CardLayout cd = (CardLayout)jMainFrame4.getLayout();
                if (card.equals("next")) cd.next(jMainFrame4);
                else if (card.equals("previous")) cd.previous(jMainFrame4);
                else 
                {
                    cd.show(jMainFrame4, card);
                }
            }
        });
        
    }    
    /**
     * Wysyła podana wiadomość do serwera
     * @param message Wiadomosc. Obiekt jednej z klasy serializowanej.
     */
    public void writeMessageToNetworkClient(Message message)
    {
        client.writeMessage(message);
    }
    /**
     * Pobiera nazwe użytkownika.
     * @return Nazwa użytkownika
     */
    public String getUsername()
    {
        return client.getUsername();
    }
  
    /**
     * Głowna metoda main. Uruchamia Okno programu
     * @param args argumenty wywolania [nie uzywane]
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
              //  "javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    /**
     * Dodaje podana wiadomosc do okna czatu
     * @param s Wiadomość do ustawienia
     */
    public void putChatMessage(String s)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                chatTextArea.append(s);
                chatTextArea.append(System.getProperty("line.separator")); 
            }
        });
    }
    /**
     * Metoda obsługuje zdarzenie kliknięcia w pole szachownicy. Do tej metody propagowane jest zdarzenie kliknięcia w panel szachownicy.
     * @param x Parametr x pola szachownicy
     * @param y Parametr y pola szachownicy
     */
    public void squareClicked(int x, int y){
        manager.CheckForMove(1, new BoardPos(x,y));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chatField;
    private javax.swing.JTextArea chatTextArea;
    private pl.gui.JMainFrame closeButton;
    private javax.swing.JLabel enemyLabel;
    private javax.swing.JLabel gameNameLabel;
    private javax.swing.JLabel gratzLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private pl.gui.JMainFrame jMainFrame1;
    private pl.gui.JMainFrame jMainFrame2;
    private pl.gui.JMainFrame jMainFrame4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private pl.gui.JMainFrame minimizeButton;
    private javax.swing.JLabel movealbeLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel submitLabel;
    private pl.gui.Szachownica szachownica;
    // End of variables declaration//GEN-END:variables
}

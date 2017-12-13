package com.memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.*;


public class memoryGameGUI extends JFrame implements MouseListener, MouseMotionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Paquet  paquet;

    private JLayeredPane layeredPane;
    private JPanel memoryBoard;
    private JLabel memoryPiece;
    private JLabel carte;
    private JPanel panel;
    private int xAdjustment;
    private int yAdjustment;
    private ControleurJeu controler;
    private Component saveLocation;
    private File g;
    private int state;
    private JPanelCustom tempPanel;

    public memoryGameGUI(String frameName, ControleurJeu controleurJeu, Dimension dim) {
        Dimension boardSize = dim;
        this.controler = controleurJeu;
        paquet = controler.getPaquet();
        int[] mat = paquet.getMatrixMaxs();
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        memoryBoard = new JPanel();
        layeredPane.add(memoryBoard, JLayeredPane.DEFAULT_LAYER);
        memoryBoard.setLayout(new GridLayout(mat[0]+1, mat[1]+1,10,10));
        memoryBoard.setPreferredSize(boardSize);
        memoryBoard.setBounds(0, 0, boardSize.width, boardSize.height);
        ArrayList<Carte> carte = paquet.getList();
        int l =0;

        for (int y = 0; y < mat[1]+1; y++) {

            for (int x = 0; x < mat[0]+1; x++) {

                JPanelCustom square = new JPanelCustom(new BorderLayout(), x, y,carte.get(l));
                square.setBackground(Color.GRAY);
                square.setBorder(BorderFactory.createEmptyBorder(10 , 10, 10, 10));
                memoryBoard.add(square);
                l++;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        Component c = memoryBoard.findComponentAt(e.getX(), e.getY());


        if (c instanceof JPanelCustom){
            saveLocation =   c.getParent();
            BufferedImage myPicture = null;
            try {
                g = new File(
                        "C:\\Users\\Johnpi\\Documents\\Ecolodge\\Projet_IRC\\4AIRC-Memory\\media\\"
                        +"carte"+((JPanelCustom) c).carte.valeurCarte + ".png");
                myPicture = ImageIO.read(g);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            ((JPanelCustom) c).add(picLabel,BorderLayout.CENTER);
            memoryBoard.invalidate();
            memoryBoard.validate();
            memoryBoard.repaint();
            state = controler.RetournerCarte(((JPanelCustom) c).x,((JPanelCustom) c).y);


            switch (state){
                case State.firstCardChoosed:
                    tempPanel = ((JPanelCustom) c);
                    break;

                case State.coupGagnant:
                    System.out.println("coup gagnant");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    tempPanel.removeAll();
                        ((JPanelCustom) c).removeAll();
                        break;

                case State.coupPerdant:
                    System.out.println("coup perdant");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    tempPanel.removeAll();
                    ((JPanelCustom) c).removeAll();
                    break;
                    }
            }


        }



    //Move the chess carte around

    public void mouseDragged(MouseEvent me) {

    }

    //Drop the chess carte back onto the chess board

    public void mouseReleased(MouseEvent e) {
    }





    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }



}
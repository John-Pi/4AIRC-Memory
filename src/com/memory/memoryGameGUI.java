package com.memory;

import java.awt.*;
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


public class memoryGameGUI extends JPanel implements MouseListener, MouseMotionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String theme;
    private  FenetreAcceuil fenetreAcceuil;

    private Paquet paquet;

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
    private Component c;
    private int tempValide;


    public memoryGameGUI(ControleurJeu controleurJeu, Dimension dim, FenetreAcceuil fenetreAcceuil, String theme) {
        fenetreAcceuil.setCoupRestant(controleurJeu.getCoupRestants());
        this.theme = theme;
        this.fenetreAcceuil = fenetreAcceuil;
        Dimension boardSize = dim;
        this.controler = controleurJeu;
        paquet = controler.getPaquet();
        int[] mat = paquet.getMatrixMaxs();
        layeredPane = new JLayeredPane();
        add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        memoryBoard = new JPanel();
        layeredPane.add(memoryBoard, JLayeredPane.DEFAULT_LAYER);
        memoryBoard.setLayout(new GridLayout(mat[0] + 1, mat[1] + 1, 10, 10));
        memoryBoard.setPreferredSize(boardSize);
        memoryBoard.setBounds(0, 0, boardSize.width, boardSize.height);
        ArrayList<Carte> cartes = paquet.getList();
        int l = 0;

        for (int y = 0; y < mat[1] + 1; y++) {

            for (int x = 0; x < mat[0] + 1; x++) {

                JPanelCustom square = new JPanelCustom(new BorderLayout(), cartes.get(l).x, cartes.get(l).y, cartes.get(l));
                square.setBackground(Color.GRAY);
                square.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                memoryBoard.add(square);
                l++;
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        c = memoryBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanelCustom) {
            tempValide = controler.isValide(((JPanelCustom) c).x, ((JPanelCustom) c).y);

            if (tempValide == State.CarteValide) {
                Image myPicture = null;
                try {
                    g = new File(
                            theme
                                    + "carte" + ((JPanelCustom) c).carte.valeurCarte + ".png");
                    myPicture = ImageIO.read(g);
                    myPicture = myPicture.getScaledInstance(c.getWidth(),c.getHeight(), Image.SCALE_DEFAULT);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                picLabel.setSize(c.getSize());
                ((JPanelCustom) c).add(picLabel, BorderLayout.CENTER);
                memoryBoard.invalidate();
                memoryBoard.validate();
                memoryBoard.repaint();

                state = controler.RetournerCarte(((JPanelCustom) c).x, ((JPanelCustom) c).y);

            }

        }
    }

    private void process() {
        switch (state) {
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
                tempPanel.setBackground(Color.white);
                c.setBackground(Color.white);
                memoryBoard.invalidate();
                memoryBoard.validate();
                memoryBoard.repaint();
                break;

            case State.coupPerdant:
                System.out.println("coup perdant");
                new Audio("erreur.wav",theme);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                tempPanel.removeAll();
                ((JPanelCustom) c).removeAll();
                memoryBoard.invalidate();
                memoryBoard.validate();
                memoryBoard.repaint();

                break;
            case State.victory:
                System.out.println("coup gagnant");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                tempPanel.removeAll();
                ((JPanelCustom) c).removeAll();
                tempPanel.setBackground(Color.white);
                c.setBackground(Color.white);
                memoryBoard.invalidate();
                memoryBoard.validate();
                memoryBoard.repaint();
                fenetreAcceuil.endPopup(true);
            case State.defeat:
                new Audio("defaite.wav",theme);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                tempPanel.removeAll();
                ((JPanelCustom) c).removeAll();
                memoryBoard.invalidate();
                memoryBoard.validate();
                memoryBoard.repaint();
                fenetreAcceuil.endPopup(false);
                break;
        }
        fenetreAcceuil.setCoupRestant(controler.getCoupRestants());

    }


    //Move the chess carte around

    public void mouseDragged(MouseEvent me) {

    }

    //Drop the chess carte back onto the chess board

    public void mouseReleased(MouseEvent e) {
        if (c instanceof JPanelCustom) {
            if(tempValide == State.CarteValide)process();
        }
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
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
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;



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

    public memoryGameGUI(String frameName, ControleurJeu controleurJeu, Dimension dim) {
        Dimension boardSize = dim;
        this.controler = new ControleurJeu();
        //  Use a Layered Pane for this this application
        paquet = controleurJeu.getPaquet();
        int[] mat = paquet.getMatrixMaxs();
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        memoryBoard = new JPanel();
        layeredPane.add(memoryBoard, JLayeredPane.DEFAULT_LAYER);
        memoryBoard.setLayout(new GridLayout(mat[0], mat[1]));
        memoryBoard.setPreferredSize(boardSize);
        memoryBoard.setBounds(0, 0, boardSize.width, boardSize.height);
        int l =0;

        for (int y = 0; y < mat[1]; y++) {

            for (int x = 0; x < mat[0]; x++) {
                l++;
                JPanelCustom square = new JPanelCustom(new BorderLayout(), x, y, Integer.toString(l));
                memoryBoard.add(square);

            }
        }
    }

    public void mousePressed(MouseEvent e) {
        memoryPiece = null;
        Component c = memoryBoard.findComponentAt(e.getX(), e.getY());


        if (c instanceof JPanelCustom)
            return;

        saveLocation =   c.getParent();
        this.pieceClicked = (JPanelCustom) c.getParent();

        if (!controler.isPlayerOK(pieceClicked.getCoord()))
            return;
        isPossible(pieceClicked.getCoord());
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        memoryPiece = (JLabel) c;
        memoryPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        memoryPiece.setSize(memoryPiece.getWidth(), memoryPiece.getHeight());
        layeredPane.add(memoryPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess carte around

    public void mouseDragged(MouseEvent me) {
        if (memoryPiece == null) return;
        memoryPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess carte back onto the chess board

    public void mouseReleased(MouseEvent e) {
        recolor();
        if (memoryPiece == null) return;


        Component c = memoryBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {
            c = c.getParent();
        }
        this.controler.move(this.pieceClicked.getCoord(), ((JPanelCustom) c).getCoord());
            memoryPiece.setVisible(false);
        


    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void update(Observable o, Object arg) {

        for (int i = 0; i < 8 * 8; i++) {
            this.panel = (JPanel) memoryBoard.getComponent(i);
            this.panel.removeAll();
        }

        List<PieceIHM> list = (List<PieceIHM>) arg;

        for (PieceIHM pieceIHM : list) {
            this.carte = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(pieceIHM.getNamePiece(), pieceIHM.getCouleur())));
            this.panel = (JPanel) memoryBoard.getComponent(8 * pieceIHM.getY() + pieceIHM.getX());
            this.panel.add(this.carte);
            this.revalidate();
        }
    }

    public void isPossible(Coord coord) {
        JPanelCustom panneau;
        for (int i = 0; i < 8 * 8; i++) {
            panneau = (JPanelCustom) memoryBoard.getComponent(i);
            if (controler.isMoveOk(coord.x, coord.y, panneau.getCoord().x, panneau.getCoord().y)) {
                panneau.setBackground(Color.cyan);
            }

        }
    }

    public void recolor() {
        int i = 0;
        JPanelCustom square;

        for (int y = 0; y < 8; y++) {

            for (int x = 0; x < 8; x++) {
                square = (JPanelCustom) memoryBoard.getComponent(i);
                i++;


                int row = y % 2;
                if (row == 0)
                    square.setBackground(((y * 8) + x) % 2 == 0 ? Color.black : Color.white);
                else
                    square.setBackground(((y * 8) + x) % 2 == 0 ? Color.white : Color.black);
            }
        }
    }
}
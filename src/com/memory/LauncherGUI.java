
package com.memory;

import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * @author francoise.perrin
 * Lance l'exÃ©cution d'un jeu d'Ã©chec en mode graphique.
 * La vue (memoryGameGUI) observe le modÃ¨le (ChessGame)
 * les Ã©changes passent par le contrÃ´leur (ChessGameControlers)
 */
public class LauncherGUI {

    /**
     * @param args
     */
    public static void main(String[] args) {


        JFrame frame;
        Dimension dim;

        dim = new Dimension(700, 700);


        frame = new memoryGameGUI("Jeu d'échec", new ControleurJeu(), dim);
        frame.setTitle("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.pack();
        frame.setVisible(true);
    }
}
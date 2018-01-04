package com.memory;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.CLOSED_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class FenetreAcceuil extends JFrame {

    Panneau panneau = new Panneau();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu newGame = new JMenu("Nouvelle Partie");
    private JMenu options = new JMenu("Options");
    private JMenuItem start = new JMenuItem("Start");

    public FenetreAcceuil() {
        this.setTitle("Jeu du Memory");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.menuBar.add(newGame);
        this.menuBar.add(options);
        this.setJMenuBar(menuBar);
        this.newGame.add(start);
        this.setContentPane(panneau);

        this.setVisible(true);
        new Audio("Generique");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });
    }

    private void launchGame() {
        getContentPane().removeAll();
        setContentPane(new memoryGameGUI(new ControleurJeu(), getContentPane().getSize(), this));
        revalidate();
    }

    public void endPopup(boolean victory) {
        String result;
        if (victory) {
            result = ("Victoire !!! ");
        } else {
            result = ("Defaite !!! ");
        }
        int n = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous recommencer ?",
                result,
                JOptionPane.YES_NO_OPTION);
        switch (n) {
            case YES_OPTION:
                launchGame();
                break;
            case NO_OPTION:
                endGame();
                break;
            case CLOSED_OPTION:
                endGame();
                break;

        }
    }

    private void endGame() {
        System.exit(0);
    }
}
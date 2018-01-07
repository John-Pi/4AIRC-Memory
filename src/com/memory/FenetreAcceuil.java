package com.memory;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;

public class FenetreAcceuil extends JFrame {

    private  String theme;
    Panneau panneau;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu newGame = new JMenu("Nouvelle Partie");
    private JMenuItem options = new JMenuItem("Options");
    private JMenuItem score  = new JMenuItem("         ");
    private JMenuItem start = new JMenuItem("Start");
    private int nbessais = 24;

    public FenetreAcceuil() {

        Object[] options2 = {"La 7éme compagnie",
                "OSS 117"};
        int n = JOptionPane.showOptionDialog(this,
                "Quel theme de jeu emblématique voulez-vous ?",
                "Bienvenue",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options2,
                options2[1]);
        switch (n){
            case YES_OPTION: theme = "media/";
                break;
            case NO_OPTION: theme = "media2/";
                break;
            case CLOSED_OPTION:
                endGame();
                break;
        }
        panneau = new Panneau(theme);

        this.setTitle("Jeu du Memory");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.menuBar.add(newGame);
        this.menuBar.add(options);
        this.menuBar.add(score);
        this.setJMenuBar(menuBar);
        this.newGame.add(start);
        this.setContentPane(panneau);

        this.setVisible(true);
        new Audio("Generique.wav",theme);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });

        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showOptions();
            }
        });
    }

    private void showOptions() {
        Object[] options = {"Facile : 30 coups",
                "moyen : 20 coups",
                "MEMORY EXTREM : 12 coups"};
        int n = JOptionPane.showOptionDialog(this,
                "Choisissez votre niveaux",
                "Choisir la Difficulté",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        switch (n){
            case YES_OPTION: nbessais = 30;
            break;
            case NO_OPTION: nbessais = 20;
            break;
            case CANCEL_OPTION: nbessais = 12;
            break;
        }
        launchGame();

    }

    private void launchGame() {
        getContentPane().removeAll();
        setContentPane(new memoryGameGUI(new ControleurJeu(nbessais), getContentPane().getSize(), this,theme));
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

    public void setCoupRestant(int n){
        score.setText("Coup restants : "+n);
    }
}
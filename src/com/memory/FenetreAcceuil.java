package com.memory;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;

public class FenetreAcceuil extends JFrame{

    Panneau panneau = new Panneau();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu newGame = new JMenu("Nouvelle Partie");
    private JMenu options = new JMenu("Options");

    public FenetreAcceuil() {
        this.setTitle("Jeu du Memory");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.menuBar.add(newGame);
        this.menuBar.add(options);
        this.setJMenuBar(menuBar);

        this.setContentPane(panneau);

        this.setVisible(true);
    }
}
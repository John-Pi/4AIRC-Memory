package com.memory;

import java.awt.Dimension;

import javax.swing.*;

public class LauncherGUI {


    public static void main(String[] args) {


        JFrame frame;
        Dimension dim;

        dim = new Dimension(600, 400);

        frame = new memoryGameGUI("Jeu du Memory", new ControleurJeu(), dim);
        frame.setTitle("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.pack();
        frame.setVisible(true);
    }
}
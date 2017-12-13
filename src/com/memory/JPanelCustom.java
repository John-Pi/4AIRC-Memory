package com.memory;

import java.awt.*;

import javax.swing.*;

public class JPanelCustom extends JPanel {
    private static final long serialVersionUID = 1L;
    Carte carte;
    int x, y;

    public JPanelCustom(LayoutManager lm, int x, int y,Carte carte) {
        super(lm);
        this.x = x;
        this.y = y;
        this.carte = carte;
        this.setLayout(new BorderLayout());
    }

    public int[] getCoord() {
        int[] tab = new int[2];
        tab[0] = this.x;
        tab[1] = this.y;
        return tab;
    }


}
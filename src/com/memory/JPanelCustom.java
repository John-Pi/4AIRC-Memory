package com.memory;

import java.awt.*;

import javax.swing.*;

public class JPanelCustom extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image img;

    private int x, y;

    public JPanelCustom(LayoutManager lm, int x, int y,String img) {
        super(lm);
        this.x = x;
        this.y = y;
        this.img = new ImageIcon(img).getImage();
    }

    public int[] getCoord() {
        int[] tab = new int[2];
        tab[0] = this.x;
        tab[1] = this.y;
        return tab;
    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
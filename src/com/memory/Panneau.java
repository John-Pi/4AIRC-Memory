package com.memory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    private final String theme;

    public Panneau(String theme) {
        this.theme = theme;
    }

    public void paintComponent(Graphics g){

        try {
            Image img = ImageIO.read(new File(
                    theme+"couv1.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

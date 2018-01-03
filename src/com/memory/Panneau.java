package com.memory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    public void paintComponent(Graphics g){

        try {
            Image img = ImageIO.read(new File(
                    "C:\\Users\\PC-Jeremy\\Documents\\GitHub\\4AIRC-Memory\\media\\couv1.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

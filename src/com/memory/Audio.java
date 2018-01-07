package com.memory;

        import javax.sound.sampled.SourceDataLine;


        import java.applet.Applet;
        import java.applet.*;
        import java.io.*;
        import java.net.MalformedURLException;
        import java.net.URL;
        import javax.sound.sampled.*;

public class Audio {


    AudioInputStream audioInputStream = null;
    SourceDataLine line;
    String file;


    public Audio(String file, String theme) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(theme+file).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}



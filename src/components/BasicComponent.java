package src.components;

import src.view.GameFrame;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                checkChess();
                onMouseClicked();
                onMouseClicked();
                playMusic();
                repaint();
            }
        });
    }
    public static void playMusic(){
        try {
            URL url=new URL("file:C:\\theda\\project\\6683.wav");
            AudioClip ac= Applet.newAudioClip(url);
            ac.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public abstract boolean checkChess();
    public abstract void onMouseClicked();
}

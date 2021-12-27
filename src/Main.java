package src;
import src.view.GameFrame;

import javax.swing.*;

//import static src.components.BasicComponent.playMusic;

public class Main{
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GameFrame mainFrame = new GameFrame(800);
            mainFrame.setVisible(true);
            GameFrame.playMusic1();
            GameFrame.controller.deleteDir("F:\\lab2\\src\\project\\process\\\\");
            GameFrame.controller.copyfiledata("F:\\lab2\\src\\project\\initialize\\0\\0", "F:\\lab2\\src\\project\\process\\0");
        });
    }
}

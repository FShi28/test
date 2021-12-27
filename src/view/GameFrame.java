package src.view;


import src.components.ChessGridComponent;
import src.controller.GameController;
import src.model.ChessPiece;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GameFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    public static void playMusic1(){
        try {
            URL url=new URL("file:C:\\theda\\project\\1.wav");
            AudioClip ac= Applet.newAudioClip(url);
            ac.play();
            ac.loop();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public GameFrame(int frameSize) {


        this.setTitle("2021F CS102A Project Reversi");


        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();

        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);
        this.setLayout(null);
        this.setLocationRelativeTo(null);


        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);

        JButton undoBtn = new JButton("Undo");
        undoBtn.setSize(100, 40);
        undoBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2- 110, (this.getHeight() + chessBoardPanel.getHeight()) / 2- 650);
        add(undoBtn);
        undoBtn.addActionListener(e -> {
            if (ChessGridComponent.sc <= 0){
                JOptionPane.showMessageDialog(null, "Already the first step!");
                ChessGridComponent.setsc(0);
            }else {
                System.out.println("click undo Btn");

                String filename = String.format("F:\\lab2\\src\\project\\process\\%s", ChessGridComponent.sc);
                File file = new File(String.format("F:\\lab2\\src\\project\\process\\%s.txt", ChessGridComponent.sc));
                file.delete();
//                System.out.printf("sc为%d",ChessGridComponent.sc);
                controller.readFileData(String.format("F:\\lab2\\src\\project\\process\\%s", ChessGridComponent.sc - 1));
                repaint();
                controller.setCheatmode(controller.getCheatmode());
                statusPanel.setCheatText(controller.getCheatmode());
                if(controller.getCurrentPlayer()==ChessPiece.BLACK){
                    chessBoardPanel.blackCanMove(ChessPiece.BLACK,0);
                }else if(controller.getCurrentPlayer()==ChessPiece.WHITE){
                    chessBoardPanel.whiteCanMove(ChessPiece.WHITE,0);
                }
                repaint();
            }
        });

        JButton SPBtn = new JButton("Show Process");
        SPBtn.setSize(117, 40);
        SPBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2- 118, (this.getHeight() + chessBoardPanel.getHeight()) / 2- 550);
        add(SPBtn);
        SPBtn.addActionListener(e -> {

            GameFrame.controller.deleteDir("F:\\lab2\\src\\project\\process\\\\");
            System.out.println("clicked Load Btn");
            String filename = JOptionPane.showInputDialog(this, "Enter the filename to load:");
            if (filename == null){
            }else{
                String filePath = String.format("F:\\lab2\\src\\project\\game\\%s\\%s", filename, filename);
//                System.out.println(controller.getsid(filePath));

                controller.setCheatmode(controller.getCheatmode());
                statusPanel.setCheatText(controller.getCheatmode());
                Thread aThread;
                for(int i=1;i<controller.getsid(filePath);i++) {

                    int q = i;
                    aThread =new Thread(new Runnable()  {
                        @Override
                        public void run(){
                            controller.readFileData(String.format("%s%d", filePath, q));
                            System.out.printf("Step%d loaded\n", q);
                            controller.setCheatmode(controller.getCheatmode());
                            statusPanel.setCheatText(controller.getCheatmode());
                        }
                    });
//        File file = new File(String.format("%s%d.txt", filename, i));


                    aThread.start();
                    try {
                        aThread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2-80, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");
            chessBoardPanel.initial();
            controller.changePlayer();
            controller.countScore();
            chessBoardPanel.initialGame();
            controller.setCheatmode(0);
            statusPanel.setCheatText(0);
            repaint();
            controller.copyfiledata("F:\\lab2\\src\\project\\initialize\\0\\0", "F:\\lab2\\src\\project\\process\\0");
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            GameFrame.controller.deleteDir("F:\\lab2\\src\\project\\process\\\\");
            System.out.println("clicked Load Btn");
            String filename = JOptionPane.showInputDialog(this, "Enter the filename to load:");
            if (filename == null){
            }else if (filename.equals("")){
                JOptionPane.showMessageDialog(null, "An empty name! Please enter a valid filename");
            }
            else{
                String filePath = String.format("F:\\lab2\\src\\project\\game\\%s\\%s", filename, filename);
//                System.out.println(controller.getsid(filePath));
                controller.readFileData(String.format("%s", filePath));
                controller.setCheatmode(controller.getCheatmode());
                statusPanel.setCheatText(controller.getCheatmode());
                if(controller.getCurrentPlayer()==ChessPiece.BLACK){
                    chessBoardPanel.blackCanMove(ChessPiece.BLACK,0);
                }else if(controller.getCurrentPlayer()==ChessPiece.WHITE){
                    chessBoardPanel.whiteCanMove(ChessPiece.WHITE,0);
                }
                repaint();
//                controller.readFileData(String.format("%s%d", filePath, 1));
//                controller.setCheatmode(controller.getCheatmode());
//                statusPanel.setCheatText(controller.getCheatmode());
//                repaint();
            }
            for (int i = 1; i < ChessGridComponent.sc; i++) {
                controller.copyfiledata(String.format("F:\\lab2\\src\\project\\game\\%s\\%s%d", filename, filename, i), String.format("F:\\lab2\\src\\project\\process\\%s", i));
            }
            controller.copyfiledata("F:\\lab2\\src\\project\\initialize\\0\\0", "F:\\lab2\\src\\project\\process\\0");
            ChessGridComponent.setsc(ChessGridComponent.sc - 1);
        });

        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Enter the filename to save:");
            File folder = new File(String.format("F:\\lab2\\src\\project\\game\\%s", filename));
            if (filename == null){
            }else {
                folder.mkdir();
                File file = new File(String.format("F:\\lab2\\src\\project\\game\\%s\\%s.txt", filename, filename));
                if (file.exists()) {
                    JOptionPane.showMessageDialog(null, "File already exist! Please enter another filename");
                } else if (filename.equals("")) {
                    JOptionPane.showMessageDialog(null, "An empty name! Please enter a valid filename");
                } else {
                    try {
                        boolean exist = file.createNewFile();
                        System.out.println(exist);
                        JOptionPane.showMessageDialog(null, "Successfully saved!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("clicked Save Btn");
                    String filePath = String.format("F:\\lab2\\src\\project\\game\\%s\\%s", filename, filename);
                    controller.writeDataToFile(filePath);
                    for (int i = 1; i <= ChessGridComponent.sc; i++) {
                        controller.copyfiledata(String.format("F:\\lab2\\src\\project\\process\\%s", i), String.format("F:\\lab2\\src\\project\\game\\%s\\%s%d", filename, filename, i));
                    }
                }            }
        });

        JButton cheatingBtn = new JButton("Cheat");
        cheatingBtn.setSize(120, 50);
        cheatingBtn.setLocation((saveGameBtn.getX()+restartBtn.getWidth()+30), restartBtn.getY());
        add(cheatingBtn);
        cheatingBtn.addActionListener(e -> {
            if(controller.getCheatmode()==0){
                controller.setCheatmode(1);
            }else if(controller.getCheatmode()==1){
                controller.setCheatmode(0);
            }
            controller.countScore();
            if(controller.getCheatmode()==1) {
                controller.repaint1();
            }else {
                if(controller.getCurrentPlayer()==ChessPiece.BLACK)controller.getGamePanel().blackCanMove(ChessPiece.BLACK,0);
                if(controller.getCurrentPlayer()==ChessPiece.WHITE)controller.getGamePanel().whiteCanMove(ChessPiece.WHITE,0);
                controller.countScore();
                repaint();
            }
            statusPanel.setCheatText(GameFrame.controller.getCheatmode());
            System.out.println(controller.getCheatmode());
        });

        JButton swapBtn = new JButton("Swap");
        swapBtn.setSize(120, 50);
        swapBtn.setLocation((cheatingBtn.getX()+restartBtn.getWidth()+30), restartBtn.getY());
        add(swapBtn);
        swapBtn.addActionListener(ex -> {
            if(GameFrame.controller.getCheatmode()==1){
                GameFrame.controller.swapPlayer();
            }
        });
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

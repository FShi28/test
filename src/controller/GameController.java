package src.controller;

import src.components.ChessGridComponent;
import src.model.ChessPiece;
import src.view.*;
import src.view.ChessBoardPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GameController {

    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;
    private int cheatmode=0;
    private int g;
    private int c = 0;
    private int H;
    private int L;
    private int sid;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
    }
    public boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {//判断是否待删除目录是否存在
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            } else {
                if (!temp.delete()) {//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return true;
    }
    public int getsid(String filename){
        List<String> fileData = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(String.format("%s.txt", filename));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            /**/
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
//            fileData.forEach(System.out::println);
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sid = Integer.parseInt(fileData.get(65));
        return sid;
    }
    public void changePlayer(){
        currentPlayer=ChessPiece.BLACK;
        statusPanel.setPlayerText(ChessPiece.BLACK.name());
    }
    public void swapPlayer() {
        countScore();
        if(currentPlayer==ChessPiece.BLACK){
            currentPlayer=ChessPiece.WHITE;
        }else{
            currentPlayer=ChessPiece.BLACK;
        }
        statusPanel.setPlayerText(currentPlayer.name());
    }

    public String result(){
        if(blackScore>whiteScore)return "BLACK WIN!";
        else if(blackScore<whiteScore)return "WHITE WIN";
        else return "DRAW GAME";
    }
    public void  countScore() {
        //todo: modify the countScore method
            blackScore = gamePanel.getBlackNumber();
            whiteScore = gamePanel.getWhiteNumber();
//            System.out.println(blackScore);
//            System.out.println(whiteScore);
        statusPanel.setScoreText(blackScore, whiteScore);
    }
    public void copyfiledata(String src, String trg){
        File destDir = new File(String.format("%s.txt", trg));
        try {
            destDir.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(String.format("%s.txt", src));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            /**/
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
//            fileData.forEach(System.out::println);
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(String.format("%s.txt", trg));
            BufferedWriter writer = null;
            writer = new BufferedWriter(myWriter);
            for (int i=0;i<fileData.size();i++){
                writer.write(fileData.get(i));
                writer.newLine();
            }
            writer.close();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyfile(String srcPathStr, String desPathStr)
    {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void repaint1(){
        gamePanel.repaint1();
    }
    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getCheatmode(){return cheatmode;}
    public void setCheatmode(int num){cheatmode=num;}
    public void readFileData(String fileName) {
        //todo: read date from file
        List<String> fileData = new ArrayList<>();
        File file = new File(String.format("%s.txt", fileName));
        c = 0;
        try {
            if (file.exists()) {
                c = 0;
            } else {
                c = 4;
                JOptionPane.showMessageDialog(null, "104: File Format Error");
                gamePanel.initial();
                changePlayer();
                countScore();
                gamePanel.initialGame();
                setCheatmode(0);
                statusPanel.setCheatText(0);
            }
            FileReader fileReader = new FileReader(String.format("%s.txt", fileName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            /**/
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
//            fileData.forEach(System.out::println);
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int e = 0; e < fileData.size(); e++) {
            if (fileData.get(e).equals("$")) {
                g = e;
            }
        }
        if (g != 64 && c == 0) {
            c = 1;
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (fileData.get(8 * i + j).equals("-1")) {
                        getGamePanel().setChessGrids(i, j, ChessPiece.BLACK);
                    } else if (fileData.get(8 * i + j).equals("0")) {
                        getGamePanel().setChessGrids(i, j, null);
                    } else if (fileData.get(8 * i + j).equals("1")) {
                        getGamePanel().setChessGrids(i, j, ChessPiece.WHITE);
                    } else if (fileData.get(8 * i + j).equals("2") && c == 0) {
                        H = i;
                        L = j;
                        c = 2;

                    }
                }

            }
            ChessGridComponent.sc = Integer.parseInt(fileData.get(65));

            cheatmode = Integer.parseInt(fileData.get(66));
            if (fileData.get(67).equals("-1")) {
                currentPlayer = ChessPiece.BLACK;
                gamePanel.blackCanMove(ChessPiece.BLACK, GameFrame.controller.getCheatmode());
                statusPanel.setPlayerText(ChessPiece.BLACK.name());
            } else if (fileData.get(67).equals("1")) {
                currentPlayer = ChessPiece.WHITE;
                gamePanel.blackCanMove(ChessPiece.WHITE, GameFrame.controller.getCheatmode());
                statusPanel.setPlayerText(ChessPiece.WHITE.name());
            } else if (c == 0) {
                statusPanel.setPlayerText("NULL");
                c = 3;
            }
            blackScore = (Integer.parseInt(fileData.get(68)));
            whiteScore = Integer.parseInt(fileData.get(69));
            countScore();
        }
    }

        public void writeDataToFile(String fileName) {
            //todo: write data into file
            int [][] intChessGrids = getGamePanel().IntArray(getGamePanel().getChessGrids());
            ArrayList <String> savePanel = new ArrayList<>();
            for (int i = 0;i < intChessGrids.length;i ++){
                for (int e = 0;e < intChessGrids[0].length;e ++){
                    savePanel.add(String.format("%s", (intChessGrids[i][e])));
                }
//            savePanel.add("\n");
            }
            savePanel.add("$");
            savePanel.add(String.format("%d", ChessGridComponent.sc));
            savePanel.add(String.format("%d",cheatmode));
            if (getCurrentPlayer()==ChessPiece.BLACK){
                savePanel.add("-1");
            } else if(getCurrentPlayer()==ChessPiece.WHITE){
                savePanel.add("1");
            }
//        savePanel.add("\n");
            savePanel.add(Integer.toString(getBlackScore()));
//        savePanel.add("\t");
            savePanel.add(Integer.toString(getWhiteScore()));
//        savePanel.add("\n");
            try {
                FileWriter myWriter = new FileWriter(String.format("%s.txt", fileName));
                BufferedWriter writer = null;
                writer = new BufferedWriter(myWriter);
                for (int i=0;i<savePanel.size();i++){
                    writer.write(savePanel.get(i));
                    writer.newLine();
                }
                writer.close();
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    private int getBlackScore() {
        return blackScore;
    }
    private int getWhiteScore(){
        return whiteScore;
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
}

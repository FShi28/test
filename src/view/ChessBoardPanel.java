package src.view;

import src.components.ChessGridComponent;
import src.model.ChessPiece;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    private int [][][]chessBoard=new int[10][10][10];
    private int blackNumber;
    private int whiteNumber;
    private ChessPiece currentPlayer;

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initial(){
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                chessGrids[i][j].setChessPiece(null);
            }
        }
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
        repaint();
        GameFrame.controller.deleteDir("F:\\lab2\\src\\project\\process");
        ChessGridComponent.sc = 1;

    }
    public void setChessGrids(int i, int j, ChessPiece color) {
        if (color == ChessPiece.BLACK){
            chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
        }else if (color == ChessPiece.WHITE){
            chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
        }else {
            chessGrids[i][j].setChessPiece(null);
        }
        repaint();
    }
    public int[][] IntArray(ChessGridComponent[][] chessGrids) {
        int [][] intArray = new int[chessGrids.length][chessGrids[0].length];
        for (int i = 0;i < chessGrids.length;i ++){
            for (int e = 0; e < chessGrids[0].length;e ++){
                if (chessGrids[i][e].getChessPiece()==ChessPiece.BLACK) {
                    intArray[i][e] = -1;
                }else if (chessGrids[i][e].getChessPiece()==ChessPiece.WHITE){
                    intArray[i][e] = 1;
                }else{
                    intArray[i][e] = 0;
                }
            }
        }
        return intArray;
    }
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        blackNumber=2;
        whiteNumber=2;
//        for(int i=0;i<8;i++){
//            chessGrids[0][i].setChessPiece(ChessPiece.WHITE);
//            chessGrids[1][i].setChessPiece(ChessPiece.WHITE);
//        }
//        for(int i=1;i<7;i++){
//            chessGrids[2][i].setChessPiece(ChessPiece.BLACK);
//            chessGrids[3][i].setChessPiece(ChessPiece.BLACK);
//        }
//        for(int i=2;i<7;i++){
//            chessGrids[4][i].setChessPiece(ChessPiece.BLACK);
//        }
//        chessGrids[3][0].setChessPiece(ChessPiece.WHITE);
//        chessGrids[2][0].setChessPiece(ChessPiece.WHITE);
//        chessGrids[2][7].setChessPiece(ChessPiece.WHITE);
//        chessGrids[3][7].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
            chessGrids[3][5].setChessPiece(ChessPiece.LITTLEBLACK);
            chessGrids[2][4].setChessPiece(ChessPiece.LITTLEBLACK);
            chessGrids[5][3].setChessPiece(ChessPiece.LITTLEBLACK);
            chessGrids[4][2].setChessPiece(ChessPiece.LITTLEBLACK);
    }
    public ChessGridComponent[][] getChessGrids(){
        return chessGrids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    public int getBlackNumber(){
//        System.out.println(blackNumber);
        return blackNumber;

    }
    public int getWhiteNumber(){
//        System.out.println(whiteNumber);
        return whiteNumber;
    }
    public void repaint1(){
        int[][] chess = new int[10][10];
        for (int j = 0; j < 10; j++) {
            chess[0][j] = 9;
            chess[j][0] = 9;
            chess[9][j] = 9;
            chess[j][9] = 9;
        }
        for(int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                    chess[i + 1][j + 1] = -1;
                } else if (chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                    chess[i + 1][j + 1] = 1;
                } else{
                    chess[i + 1][j + 1] = 0;
                }
            }
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chess[i+1][j+1]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }else if(chess[i+1][j+1]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }else chessGrids[i][j].setChessPiece(null);
            }
        }
        repaint();
    }
    public boolean whiteCanMove(ChessPiece currentPlayer,int cheatmode){
        int[][] chess = new int[10][10];
        int num=0;
        int[] t = new int[9];
        for (int j = 0; j < 10; j++) {
            chess[0][j] = 9;
            chess[j][0] = 9;
            chess[9][j] = 9;
            chess[j][9] = 9;
        }
        for(int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                    chess[i + 1][j + 1] = -1;
                } else if (chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                    chess[i + 1][j + 1] = 1;
                } else{
                    chess[i + 1][j + 1] = 0;
                }
            }
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for (int m = 1; m <= 8; m++) {
                    t[m] = 1;
                }
                if(chess[i+1][j+1]==0){
        for (; ; ) {
            if (chess[i+1 + t[1]][j+1] == -1) {
                int t1=t[1];
                t[1]++;
                if (chess[i+1 + t[1]][j+1] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1 - t[2]][j+1] == -1) {
                int t2=t[2];
                t[2]++;
                if (chess[i+1 - t[2]][j+1] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1][j+1 - t[3]] == -1) {
                int t3=t[3];
                t[3]++;
                if (chess[i+1][j+1 - t[3]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1][j+1 + t[4]] == -1) {
                int t4=t[4];
                t[4]++;
                if (chess[i+1][j+1 + t[4]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1 + t[5]][j+1 + t[5]] == -1) {
                int t5=t[5];
                t[5]++;
                if (chess[i+1 + t[5]][j+1 + t[5]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1 - t[6]][j+1 + t[6]] == -1) {
                int t6=t[6];
                t[6]++;
                if (chess[i+1 - t[6]][j+1 + t[6]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1 - t[7]][j+1 - t[7]] == -1) {
                int t7=t[7];
                t[7]++;
                if (chess[i+1 - t[7]][j+1 - t[7]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
        for (; ; ) {
            if (chess[i+1 + t[8]][j+1 - t[8]] == -1) {
                int t8=t[8];
                t[8]++;
                if (chess[i+1 + t[8]][j+1 - t[8]] == 1) {
                    chess[i+1][j+1]=2;
                    num=1;
                }
            } else break;
        }
            }
            }
                }
//        for(int j=1;j<=8;j++){
//            for(int k=1;k<=8;k++){
//                System.out.printf("%3d",chess[j][k]);
//            }
//            System.out.print("\n");}
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chess[i+1][j+1]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }else if(chess[i+1][j+1]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }else if(chess[i+1][j+1]==2&&cheatmode==0){
                    chessGrids[i][j].setChessPiece((ChessPiece.LITTLEWHITE));
                }else chessGrids[i][j].setChessPiece(null);
            }
        }
        repaint();
        if(num==1){
            return true;
        }else return false;
    }
    public boolean blackCanMove(ChessPiece currentPlayer,int cheatmode){
        int[][] chess = new int[10][10];
        int num=0;
        int[] t = new int[9];
        for (int j = 0; j < 10; j++) {
            chess[0][j] = 2;
            chess[j][0] = 2;
            chess[9][j] = 2;
            chess[j][9] = 2;
        }
        for(int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                    chess[i + 1][j + 1] = -1;
                } else if (chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                    chess[i + 1][j + 1] = 1;
                } else {
                    chess[i + 1][j + 1] = 0;
                }
            }
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for (int m = 1; m <= 8; m++) {
                    t[m] = 1;
                }
                if(chess[i+1][j+1]==0) {
                    for (; ; ) {
                        if (chess[i + 1 + t[1]][j + 1] == 1) {
                            int t1 = t[1];
                            t[1]++;
                            if (chess[i + 1 + t[1]][j + 1] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1 - t[2]][j + 1] == 1) {
                            int t2 = t[2];
                            t[2]++;
                            if (chess[i + 1 - t[2]][j + 1] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1][j + 1 - t[3]] == 1) {
                            int t3 = t[3];
                            t[3]++;
                            if (chess[i + 1][j + 1 - t[3]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1][j + 1 + t[4]] == 1) {
                            int t4 = t[4];
                            t[4]++;
                            if (chess[i + 1][j + 1 + t[4]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1 + t[5]][j + 1 + t[5]] == 1) {
                            int t5 = t[5];
                            t[5]++;
                            if (chess[i + 1 + t[5]][j + 1 + t[5]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1 - t[6]][j + 1 + t[6]] == 1) {
                            int t6 = t[6];
                            t[6]++;
                            if (chess[i + 1 - t[6]][j + 1 + t[6]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1 - t[7]][j + 1 - t[7]] == 1) {
                            int t7 = t[7];
                            t[7]++;
                            if (chess[i + 1 - t[7]][j + 1 - t[7]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }
                    for (; ; ) {
                        if (chess[i + 1 + t[8]][j + 1 - t[8]] == 1) {
                            int t8 = t[8];
                            t[8]++;
                            if (chess[i + 1 + t[8]][j + 1 - t[8]] == -1) {
                                chess[i+1][j+1] = -2;
                                num = 1;
                            }
                        } else break;
                    }

                }
                }
        }
//        for(int j=1;j<=8;j++){
//            for(int k=1;k<=8;k++){
//                System.out.printf("%3d",chess[j][k]);
//            }
//            System.out.print("\n");}
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chess[i+1][j+1]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }else if(chess[i+1][j+1]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }else if(chess[i+1][j+1]==-2&&cheatmode==0){
                    chessGrids[i][j].setChessPiece(ChessPiece.LITTLEBLACK);
                }else chessGrids[i][j].setChessPiece(null);
            }
        }repaint();
        if(num==1)return true;
        else return false;
    }


    public boolean cheatmove(int row, int col, ChessPiece currentPlayer) {
        this.currentPlayer = currentPlayer;
        int num = 0;
        if (chessGrids[row][col].getChessPiece() == ChessPiece.BLACK || chessGrids[row][col].getChessPiece() == ChessPiece.WHITE) {
            return false;
        }else{
        int[] t = new int[9];
        int[][] chess = new int[10][10];
        blackNumber=0;
        whiteNumber=0;
        for (int j = 0; j < 10; j++) {
            chess[0][j] = 2;
            chess[j][0] = 2;
            chess[9][j] = 2;
            chess[j][9] = 2;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                    chess[i + 1][j + 1] = -1;
                } else if (chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                    chess[i + 1][j + 1] = 1;
                } else {
                    chess[i + 1][j + 1] = 0;
                }
            }
        }
        if (currentPlayer == ChessPiece.WHITE) {
            for (int m = 1; m <= 8; m++) {
                for (int y = 1; y <= 8; y++) {
                    chess[m][y] = chess[m][y] * (-1);
                }
            }
        }
        chess[row + 1][col + 1] = -1;
        for (int m = 1; m <= 8; m++) {
            t[m] = 1;
        }
        for (; ; ) {
            if (chess[row + 1 + t[1]][col + 1] == 1) {
                int t1 = t[1];
                t[1]++;
                if (chess[row + 1 + t[1]][col + 1] == -1) {
                    for (int k = 0; k <= t[1]; k++) {
                        chess[row + 1 + k][col + 1] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1 - t[2]][col + 1] == 1) {
                int t2 = t[2];
                t[2]++;
                if (chess[row + 1 - t[2]][col + 1] == -1) {
                    for (int k = 0; k <= t[2]; k++) {
                        chess[row + 1 - k][col + 1] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1][col + 1 - t[3]] == 1) {
                int t3 = t[3];
                t[3]++;
                if (chess[row + 1][col + 1 - t[3]] == -1) {
                    for (int k = 0; k <= t[3]; k++) {
                        chess[row + 1][col + 1 - k] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1][col + 1 + t[4]] == 1) {
                int t4 = t[4];
                t[4]++;
                if (chess[row + 1][col + 1 + t[4]] == -1) {
                    for (int k = 0; k <= t[4]; k++) {
                        chess[row + 1][col + 1 + k] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1 + t[5]][col + 1 + t[5]] == 1) {
                int t5 = t[5];
                t[5]++;
                if (chess[row + 1 + t[5]][col + 1 + t[5]] == -1) {
                    for (int k = 0; k <= t[5]; k++) {
                        chess[row + 1 + k][col + 1 + k] = -1;
                        num = 1;

                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1 - t[6]][col + 1 + t[6]] == 1) {
                int t6 = t[6];
                t[6]++;
                if (chess[row + 1 - t[6]][col + 1 + t[6]] == -1) {
                    for (int k = 0; k <= t[6]; k++) {
                        chess[row + 1 - k][col + 1 + k] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1 - t[7]][col + 1 - t[7]] == 1) {
                int t7 = t[7];
                t[7]++;
                if (chess[row + 1 - t[7]][col + 1 - t[7]] == -1) {
                    for (int k = 0; k <= t[7]; k++) {
                        chess[row + 1 - k][col + 1 - k] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        for (; ; ) {
            if (chess[row + 1 + t[8]][col + 1 - t[8]] == 1) {
                int t8 = t[8];
                t[8]++;
                if (chess[row + 1 + t[8]][col + 1 - t[8]] == -1) {
                    for (int k = 0; k <= t[8]; k++) {
                        chess[row + 1 + k][col + 1 - k] = -1;
                        num = 1;
                    }
                }
            } else break;
        }
        if (currentPlayer == ChessPiece.WHITE) {
            for (int m = 1; m <= 8; m++) {
                for (int y = 1; y <= 8; y++) {
                    chess[m][y] = chess[m][y] * (-1);
                }
            }
        }
//        for(int j=1;j<=8;j++){
//            for(int k=1;k<=8;k++){
//                System.out.printf("%3d",chess[j][k]);
//            }
//            System.out.print("\n");}
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess[i + 1][j + 1] == -1) {
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                } else if (chess[i + 1][j + 1] == 1) {
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
            }
        }
        repaint();
            for(int i=1;i<=8;i++){
                for(int j=0;j<=8;j++){
                    if(chess[i][j]==-1)blackNumber++;
                    if(chess[i][j]==1)whiteNumber++;
                }
            }
//        System.out.println(blackNumber);
//        System.out.println(whiteNumber);
        return true;}


    }
    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        //todo: complete this method
        int num=0;
        this.currentPlayer=currentPlayer;
        if(chessGrids[row][col].getChessPiece()==ChessPiece.BLACK||chessGrids[row][col].getChessPiece()==ChessPiece.WHITE){
            return false;
        }
        blackNumber=0;
        whiteNumber=0;
        int[] t = new int[9];
        int[][] chess = new int[10][10];
            for (int j = 0; j < 10; j++) {
                chess[0][j] = 2;
                chess[j][0] = 2;
                chess[9][j] = 2;
                chess[j][9] = 2;
            }
            for(int i=0;i<8;i++){
                for (int j = 0; j < 8; j++) {
                    if (chessGrids[i][j].getChessPiece() == ChessPiece.BLACK) {
                        chess[i + 1][j + 1] = -1;
                    } else if (chessGrids[i][j].getChessPiece() == ChessPiece.WHITE) {
                        chess[i + 1][j + 1] = 1;
                    } else {
                        chess[i + 1][j + 1] = 0;
                    }
                }
            }
        if (currentPlayer==ChessPiece.WHITE) {
                for (int m = 1; m <= 8; m++) {
                    for (int y = 1; y <= 8; y++) {
                        chess[m][y] = chess[m][y] * (-1);
                    }
                }
            }
            for (int m = 1; m <= 8; m++) {
                t[m] = 1;
            }
                for (; ; ) {
                    if (chess[row+1 + t[1]][col+1] == 1) {
                        int t1=t[1];
                        t[1]++;
                        if (chess[row+1 + t[1]][col+1] == -1) {
                            for(int k=0;k<=t[1];k++) {
                                chess[row + 1 + k ][col + 1] = -1;
                                num = 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1 - t[2]][col+1] == 1) {
                        int t2=t[2];
                        t[2]++;
                        if (chess[row+1 - t[2]][col+1] == -1) {
                            for(int k=0;k<=t[2];k++){
                                chess[row+1 - k][col+1] =-1;
                                num = 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1][col+1 - t[3]] == 1) {
                        int t3=t[3];
                        t[3]++;
                        if (chess[row+1][col+1 - t[3]] == -1) {
                            for (int k=0;k<=t[3];k++){
                                chess[row+1][col+1 - k] =-1;
                                num = 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1][col+1 + t[4]] == 1) {
                        int t4=t[4];
                        t[4]++;
                        if (chess[row+1][col+1 + t[4]] == -1) {
                            for(int k=0;k<=t[4];k++){
                                chess[row+1][col+1 + k] =-1;
                                num = 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1 + t[5]][col+1 + t[5]] == 1) {
                        int t5=t[5];
                        t[5]++;
                        if (chess[row+1 + t[5]][col+1 + t[5]] == -1) {
                            for (int k=0;k<=t[5];k++){
                                chess[row+1 + k][col+1 + k] =-1;
                                num = 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1 - t[6]][col+1 + t[6]] == 1) {
                        int t6=t[6];
                        t[6]++;
                        if (chess[row+1 - t[6]][col+1 + t[6]] == -1) {
                            for(int k=0;k<=t[6];k++){
                                chess[row+1 - k][col+1 + k] =-1;
                                num= 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1 - t[7]][col+1 - t[7]] == 1) {
                        int t7=t[7];
                        t[7]++;
                        if (chess[row+1 - t[7]][col+1 - t[7]] == -1) {
                            for(int k=0;k<=t[7];k++){
                                chess[row+1 - k][col+1 - k] =-1;
                                num= 1;
                            }
                        }
                    } else break;
                }
                for (; ; ) {
                    if (chess[row+1 + t[8]][col+1 - t[8]] == 1) {
                        int t8=t[8];
                        t[8]++;
                        if (chess[row+1 + t[8]][col+1 - t[8]] == -1) {
                            for(int k=0;k<=t[8];k++){
                                chess[row+1 + k][col+1 - k] =-1;
                                num= 1;
                            }
                        }
                    } else break;
                }
        if (currentPlayer==ChessPiece.WHITE) {
            for (int m = 1; m <= 8; m++) {
                for (int y = 1; y <= 8; y++) {
                    chess[m][y] = chess[m][y] * (-1);
                }
            }
        }
//        for(int j=1;j<=8;j++){
//            for(int k=1;k<=8;k++){
//                System.out.printf("%3d",chess[j][k]);
//            }
//            System.out.print("\n");}
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chess[i+1][j+1]==-1){
                    chessGrids[i][j].setChessPiece(ChessPiece.BLACK);
                }else if(chess[i+1][j+1]==1){
                    chessGrids[i][j].setChessPiece(ChessPiece.WHITE);
                }
            }
        }
        repaint();
        for(int i=1;i<=8;i++){
            for(int j=0;j<=8;j++){
                if(chess[i][j]==-1)blackNumber++;
                if(chess[i][j]==1)whiteNumber++;
            }
        }
//        System.out.println(blackNumber);
//        System.out.println(whiteNumber);
        if(num==1){
            return true;
        }else  {
            System.out.println("105");
            return false;
        }
    }
}

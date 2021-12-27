package src.components;
import javax.swing.JOptionPane;

import src.model.*;
import src.view.GameFrame;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);
    public static int sc = 0;
    public int sid;


    private ChessPiece chessPiece;
    private int row;
    private int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }
    public static void setsc(int sid) {
        sc = sid;
    }
    public void writefile(){
        sc++;
        sid = sc;
        String filename = String.format("%d", sid);
        File file = new File(String.format("F:\\lab2\\src\\project\\process\\%s.txt", filename));
        try {
            boolean exist = file.createNewFile();
            System.out.println(exist);
            System.out.printf("Step%d successfully saved", sid);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String filePath = String.format("F:\\lab2\\src\\project\\process\\%s", filename);
        GameFrame.controller.writeDataToFile(filePath);

    }

    @Override
    public boolean checkChess(){
//        if(GameFrame.controller.getCheatmode()==0){
            if(GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK,GameFrame.controller.getCheatmode())||GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE,GameFrame.controller.getCheatmode())){
                return false;
            }else {JOptionPane.showMessageDialog(null,GameFrame.controller.result(),"对局结果",JOptionPane.INFORMATION_MESSAGE);
                return true;
//        }else if(GameFrame.controller.getCheatmode()==1){
//            if(GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK,GameFrame.controller.getCheatmode())||GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE,GameFrame.controller.getCheatmode())){
//
            }
    }

    @Override
    public void onMouseClicked() {

        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //todo: complete mouse click method
        if (!checkChess()) {
            if (GameFrame.controller.getCheatmode() == 0) {
                if (GameFrame.controller.canClick(row, col)) {
                    writefile();
                    GameFrame.controller.swapPlayer();

                    if (GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) {
                        GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK, GameFrame.controller.getCheatmode());
                        System.out.println("black");
                    } else if (GameFrame.controller.getCurrentPlayer() == ChessPiece.WHITE) {
                        GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE, GameFrame.controller.getCheatmode());
                        System.out.println("white");
                    }
                } else {
                    if (GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) {
                        if (!GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK, GameFrame.controller.getCheatmode())) {
                            if (GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE, GameFrame.controller.getCheatmode())) {
                                GameFrame.controller.swapPlayer();
                                GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE, GameFrame.controller.getCheatmode());
                            }
                        }
                    }
                    if (GameFrame.controller.getCurrentPlayer() == ChessPiece.WHITE) {
                        if (!GameFrame.controller.getGamePanel().whiteCanMove(ChessPiece.WHITE, GameFrame.controller.getCheatmode())) {
                            if (GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK, GameFrame.controller.getCheatmode())) {
                                GameFrame.controller.swapPlayer();
                                GameFrame.controller.getGamePanel().blackCanMove(ChessPiece.BLACK, GameFrame.controller.getCheatmode());
                            }
                        }
                    }
                }
            } else if (GameFrame.controller.getCheatmode() == 1) {
                GameFrame.controller.getGamePanel().cheatmove(row, col, GameFrame.controller.getCurrentPlayer());
                GameFrame.controller.countScore();
                writefile();
            }
        }


    }



    public ChessPiece getChessPiece () {
            return chessPiece;
        }

        public void setChessPiece (ChessPiece chessPiece){
            this.chessPiece = chessPiece;
        }

        public int getRow () {
            return row;
        }

        public int getCol () {
            return col;
        }

        public void drawPiece (Graphics g){
            g.setColor(gridColor);
            g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
            if (this.chessPiece == ChessPiece.BLACK || this.chessPiece == ChessPiece.WHITE) {
                g.setColor(chessPiece.getColor());
                g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
            }
            if (this.chessPiece == ChessPiece.LITTLEBLACK || this.chessPiece == ChessPiece.LITTLEWHITE) {
                g.setColor((chessPiece.getColor()));
                g.fillOval((gridSize - 20) / 2, (gridSize - 20) / 2, chessSize / 4, chessSize / 4);
            }
        }


        @Override
        public void paintComponent (Graphics g){
            super.printComponents(g);
            drawPiece(g);
        }


}

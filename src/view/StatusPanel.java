package src.view;

import src.controller.GameController;
import src.model.ChessPiece;
import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JLabel cheatLabel;
    public StatusPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);


        this.playerLabel = new JLabel();
        this.playerLabel.setLocation(0, 10);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        add(playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.4), 10);
        this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setScoreText(2,2);
        add(scoreLabel);

        this.cheatLabel = new JLabel();
        this.cheatLabel.setLocation((int) (width * 0.8), 10);
        this.cheatLabel.setSize((int) (width * 0.5), height);
        this.cheatLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setCheatText(0);
        add(cheatLabel);
    }

    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }
    public void setCheatText(int cheatmode) {
        if (cheatmode== 1){
            this.cheatLabel.setText("Cheat:On");
        }else {
            this.cheatLabel.setText("Cheat:Off");
        }

    }
}

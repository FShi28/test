package src.model;

import java.awt.*;

public enum ChessPiece {
    BLACK(Color.BLACK), WHITE(Color.WHITE),LITTLEBLACK(Color.BLACK),LITTLEWHITE(Color.WHITE),YELLOW(Color.YELLOW);

    private final Color color;

    ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

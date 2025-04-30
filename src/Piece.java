// Piece.java
import java.awt.*;

interface Piece {
    void draw(Graphics g, int width, int height);
    Color getColor();
    boolean isKing();
    Piece makeKing();
}


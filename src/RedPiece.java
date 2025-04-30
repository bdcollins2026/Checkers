// RedPiece.java
import java.awt.*;

class RedPiece implements Piece {
    private boolean king;

    public RedPiece(boolean king) {
        this.king = king;
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.RED);
        g.fillOval(width / 4, height / 4, width / 2, height / 2);
        if (king) {
            g.setColor(Color.YELLOW);
            g.drawString("K", width / 2 - 4, height / 2 + 4);
        }
    }

    public Color getColor() {
        return Color.RED;
    }

    public boolean isKing() {
        return king;
    }

    public Piece makeKing() {
        return new RedPiece(true);
    }
}
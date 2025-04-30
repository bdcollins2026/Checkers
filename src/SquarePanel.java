// SquarePanel.java
import javax.swing.*;
import java.awt.*;

class SquarePanel extends JPanel {
    public final int row;
    public final int col;
    private Piece piece = null;

    public SquarePanel(int row, int col, boolean isDark) {
        this.row = row;
        this.col = col;
        setBackground(isDark ? Color.DARK_GRAY : Color.LIGHT_GRAY);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint();
    }

    public void removePiece() {
        this.piece = null;
        repaint();
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (piece != null) {
            piece.draw(g, getWidth(), getHeight());
        }
    }
}
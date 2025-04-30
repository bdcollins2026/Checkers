import javax.swing.*;
import java.awt.*;

public class CheckersBoard extends JFrame implements CheckersWindow {
    private SquarePanel selectedSquare = null;
    private PieceFactory pieceFactory = new PieceFactory();

    public CheckersBoard() {
        // Existing constructor code...
        System.out.println("Initializing CheckersBoard (expensive operation)...");
        setTitle("Checkers - Click to Move");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isDark = (row + col) % 2 != 0;
                SquarePanel square = new SquarePanel(row, col, isDark);
                add(square);

                if (isDark) {
                    if (row < 3) {
                        square.setPiece(pieceFactory.createPiece("BLACK"));
                    } else if (row > 4) {
                        square.setPiece(pieceFactory.createPiece("RED"));
                    }
                }

                square.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        handleClick(square);
                    }
                });
            }
        }
    }

    private void handleClick(SquarePanel square) {
        if (selectedSquare == null) {
            if (square.hasPiece()) {
                selectedSquare = square;
                square.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }
        } else {
            // Trigger move attempt via the interface
            attemptMove(selectedSquare.row, selectedSquare.col, square.row, square.col);
            selectedSquare.setBorder(null);
            selectedSquare = null;
        }
    }

    @Override
    public void display() {
        setVisible(true);
    }

    @Override
    public void attemptMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Find the source and target squares
        SquarePanel fromSquare = getSquare(fromRow, fromCol);
        SquarePanel toSquare = getSquare(toRow, toCol);

        if (fromSquare != null && toSquare != null && fromSquare.hasPiece()) {
            toSquare.setPiece(fromSquare.getPiece());
            fromSquare.removePiece();
        }
    }

    private SquarePanel getSquare(int row, int col) {
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof SquarePanel panel) {
                if (panel.row == row && panel.col == col) {
                    return panel;
                }
            }
        }
        return null;
    }
}
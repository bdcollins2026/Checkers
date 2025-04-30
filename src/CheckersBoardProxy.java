import java.awt.*;

public class CheckersBoardProxy implements CheckersWindow {
    private CheckersBoard realCheckersBoard;
    private String currentTurn = "RED"; // Track whose turn it is (RED or BLACK)

    public CheckersBoardProxy() {
        // Delay initialization of CheckersBoard
    }

    @Override
    public void display() {
        if (realCheckersBoard == null) {
            realCheckersBoard = new CheckersBoard();
        }
        realCheckersBoard.display();
    }

    @Override
    public void attemptMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (realCheckersBoard == null) {
            realCheckersBoard = new CheckersBoard();
        }

        // Validate the move
        if (isLegalMove(fromRow, fromCol, toRow, toCol)) {
            realCheckersBoard.attemptMove(fromRow, fromCol, toRow, toCol);
            // Switch turns after a valid move
            currentTurn = currentTurn.equals("RED") ? "BLACK" : "RED";
        } else {
            System.out.println("Illegal move attempted!");
            // Optionally, notify the user (e.g., via a dialog or UI feedback)
        }
    }

    private boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Get the source and target squares
        SquarePanel fromSquare = getSquare(fromRow, fromCol);
        SquarePanel toSquare = getSquare(toRow, toCol);

        if (fromSquare == null || toSquare == null || !fromSquare.hasPiece()) {
            return false; // No piece to move
        }

        if (toSquare.hasPiece()) {
            return false; // Target square is occupied
        }

        Piece piece = fromSquare.getPiece();
        // Check if it's the correct player's turn
        if (!piece.getColor().toString().equalsIgnoreCase(currentTurn)) {
            return false; // Not the player's turn
        }

        // Check for valid diagonal move
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        if (rowDiff != colDiff || (rowDiff != 1 && rowDiff != 2)) {
            return false; // Must move diagonally by 1 or 2 squares
        }

        // Direction check based on piece type and king status
        boolean isRed = piece.getColor().equals(Color.RED);
        boolean isKing = piece.isKing();
        int rowDirection = toRow - fromRow;

        if (!isKing) {
            if (isRed && rowDirection >= 0) {
                return false; // Red pieces move up (negative row direction)
            }
            if (!isRed && rowDirection <= 0) {
                return false; // Black pieces move down (positive row direction)
            }
        }

        // Jump validation (if moving two squares)
        if (rowDiff == 2) {
            int midRow = (fromRow + toRow) / 2;
            int midCol = (fromCol + toCol) / 2;
            SquarePanel midSquare = getSquare(midRow, midCol);
            if (midSquare == null || !midSquare.hasPiece()) {
                return false; // No piece to jump over
            }
            // Check if the jumped piece is of the opposite color
            Piece jumpedPiece = midSquare.getPiece();
            if (jumpedPiece.getColor().equals(piece.getColor())) {
                return false; // Cannot jump own piece
            }
        }

        return true; // Move is legal
    }

    private SquarePanel getSquare(int row, int col) {
        if (realCheckersBoard == null) {
            return null;
        }
        for (Component comp : realCheckersBoard.getContentPane().getComponents()) {
            if (comp instanceof SquarePanel panel) {
                if (panel.row == row && panel.col == col) {
                    return panel;
                }
            }
        }
        return null;
    }
}
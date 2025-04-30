import javax.swing.JFrame;

public interface CheckersWindow {
    void display(); // Method to show the checkers board
    void attemptMove(int fromRow, int fromCol, int toRow, int toCol); // Method to attempt a move
}
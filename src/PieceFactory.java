// PieceFactory.java
class PieceFactory {
    public Piece createPiece(String type) {
        return switch (type) {
            case "RED" -> new RedPiece(false);
            case "BLACK" -> new BlackPiece(false);
            default -> null;
        };
    }
}


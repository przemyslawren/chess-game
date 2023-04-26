public class Rook extends Piece {
    private final char icon;
    public Rook(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♜' : '♖';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
       return Helpers.isHorizontalVerticalValidMove(position, newPosition);
    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "Rook ";
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

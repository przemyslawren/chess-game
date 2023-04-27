public class Queen extends Piece {
    private final char icon;
    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♛' : '♕';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        return Helpers.isCrossValidMove(position, newPosition) || Helpers.isHorizontalVerticalValidMove(position, newPosition);
    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "Queen ";
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

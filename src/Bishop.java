public class Bishop extends Piece{
    private final char icon;
    public Bishop(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♗' : '♝';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        return Helpers.isCrossValidMove(position, newPosition);
    }

    @Override
    public String toString() {
            return playerColor.name() + " " + "Bishop ";
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

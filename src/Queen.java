public class Queen extends Piece {
    private final char icon;
    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.WHITE) ? '♕' : '♛';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        return false;
    }

    @Override
    public void move(Position newPosition) {

    }

    @Override
    public char getIcon() {
        return 0;
    }
}

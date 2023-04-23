public class Queen extends Piece {
    public Queen(PlayerColor playerColor, Position position) {
        super(playerColor, position);
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        return false;
    }

    @Override
    public void move(Position newPosition) {

    }
}

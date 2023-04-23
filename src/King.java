public class King extends Piece {

    public King(PlayerColor playerColor, Position position) {
        super(playerColor, position);
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        //roszada
        return false;
    }

    @Override
    public void move(Position newPosition) {

    }
}

public class King extends Piece {
    private final char icon;
    public King(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.WHITE) ? '♔' : '♚';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        //roszada
        return false;
    }

    @Override
    public void move(Position newPosition) {

    }

    @Override
    public char getIcon() {
        return icon;
    }
}

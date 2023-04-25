public class King extends Piece {
    private final char icon;
    public King(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♔' : '♚';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        //roszada
        var offsetX = newPosition.x - position.x;
        var offsetY = newPosition.y - position.y;

        var isValidMove = Math.abs(offsetX) <= 1 && Math.abs(offsetY) <= 1;

        return isValidMove && ChessGame.GetInstance().GetBoard().isFreePosition(newPosition);
    }

    @Override
    public void move(Position newPosition) {

    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "King ";
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

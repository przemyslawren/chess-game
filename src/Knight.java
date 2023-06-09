public class Knight extends Piece {
    private final char icon;
    public Knight(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♞' : '♘';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        if(Math.abs(newPosition.x - position.x) == 2){
            return Math.abs(newPosition.y - position.y) == 1;
        }
        else if(Math.abs(newPosition.y - position.y) == 2){
            return Math.abs(newPosition.x - position.x) == 1;
        }

        return false;
    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "Knight ";
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

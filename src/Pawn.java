
public class Pawn extends Piece {
    private boolean isMoved = false;
    private final char icon;
    public Pawn(PlayerColor color, Position position) {
        super(color, position);
        icon = (playerColor == PlayerColor.WHITE) ? '♙' : '♟';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        var direction = playerColor == PlayerColor.WHITE ? 1 : -1;

        var offsetY = newPosition.y - position.y;
        var validOffsetY = isMoved ? 1 : 2;

        if(offsetY * direction > validOffsetY){
            return false;
        }

        var offsetX = newPosition.x - position.x;

        if(Math.abs(offsetX) > 1){
            return false;
        }

        if(offsetX == 0){
            for(int i=1; i <= offsetY * direction; i++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(newPosition.x, position.y + (i * direction)))){
                    return false;
                }
            }
        }
        else if(offsetY * direction > 1){
            return false;
        }

        return true;
    }

    @Override
    public void move(Position newPosition) {
        // bicie w przelocie
        this.isMoved = true;
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

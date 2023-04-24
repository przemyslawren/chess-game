public class Rook extends Piece {
    private final char icon;
    public Rook(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.WHITE) ? '♖' : '♜';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        var offsetX = newPosition.x - position.x;
        var offsetY = newPosition.y - position.y;

        var dirX = offsetX > 0 ? 1 : -1;
        var dirY = offsetY > 0 ? 1 : -1;

        if(offsetX != 0){
            if(offsetY != 0) {
                return false;
            }

            for(int x=1; x < Math.abs(offsetX); x++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(position.x + (x * dirX), position.y))){
                    return false;
                }
            }
        }
        else {
            for(int y=1; y < Math.abs(offsetY); y++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(position.x, position.y + (y * dirY)))){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void move(Position newPosition) {

    }

    @Override
    public char getIcon() {
        return icon;
    }
}

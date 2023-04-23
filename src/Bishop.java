public class Bishop extends Piece {
    public Bishop(PlayerColor playerColor, Position position) {
        super(playerColor, position);
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        var offsetX = newPosition.x - position.x;
        var offsetY = newPosition.y - position.y;

        var dirX = offsetX > 0 ? 1 : -1;
        var dirY = offsetY > 0 ? 1 : -1;

        if(Math.abs(offsetX) != Math.abs(offsetY)){
            return false;
        }

        for(int x=1; x <= Math.abs(offsetX); x++){
            for(int y=1; y <= Math.abs(offsetY); y++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(position.x + (x * dirX), position.y + (y * dirY)))){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void move(Position newPosition) {

    }
}
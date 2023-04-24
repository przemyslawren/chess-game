public abstract class Piece implements IMovable, IGetIcon {
    PlayerColor playerColor;
    Position position;

    @Override
    public final boolean isValidMove(Position newPosition) {
        if(position.isEqual(newPosition)){
            return false;
        }

        var piece = ChessGame.GetInstance().GetBoard().getPiece(newPosition);

        if(piece != null && piece.playerColor == playerColor){
            return false;
        }

        if(!Board.isPositionValid(position))
        {
            return false;
        }

        return isValidMoveUniq(position);
    }

    protected abstract boolean isValidMoveUniq(Position newPosition);

    public Piece(PlayerColor playerColor, Position position) {
        this.playerColor = playerColor;
        this.position = position;
    }
}

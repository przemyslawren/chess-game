public abstract class Piece implements IMovable, IGetIcon {
    PlayerColor playerColor;
    Position position;

    @Override
    public final boolean isValidMove(Position newPosition) {
        if(position.isEqual(newPosition)){
            return false;
        }

        if(!Board.isPositionValid(newPosition))
        {
            return false;
        }

        var piece = ChessGame.GetInstance().GetBoard().getPiece(newPosition);

        if(piece != null && piece.playerColor == playerColor){
            return false;
        }

        return isValidMoveUniq(newPosition);
    }

    protected abstract boolean isValidMoveUniq(Position newPosition);

    @Override
    public void move(Position newPosition) {
        var board = ChessGame.GetInstance().GetBoard();
        board.movePiece(this, newPosition);
    }

    public Piece(PlayerColor playerColor, Position position) {
        this.playerColor = playerColor;
        this.position = position;
    }
}

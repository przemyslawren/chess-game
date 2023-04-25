
public class Pawn extends Piece {
    private boolean isMoved = false;
    private final char icon;
    public Pawn(PlayerColor color, Position position) {
        super(color, position);
        icon = (playerColor == PlayerColor.White) ? '♙' : '♟';
    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "Pawn ";
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        var direction = playerColor == PlayerColor.White ? 1 : -1;

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
        else{
            if(offsetY * direction > 1){
                return false;
            }

            return !ChessGame.GetInstance().GetBoard().isFreePosition(newPosition);
        }

        return true;
    }

    @Override
    public void move(Position newPosition) {
        var board = ChessGame.GetInstance().GetBoard();

        var lastBoardMove = board.getLastBoardMove();
        if(lastBoardMove != null && lastBoardMove.takenPiece != null) {
            var piece = board.getPiece(lastBoardMove.takenPiece.position);

            if(piece instanceof Pawn){
                if(Math.abs(piece.position.y - lastBoardMove.movedPieceLastPosition.y) == 2){
                    if(piece.position.x == newPosition.x && Math.abs(newPosition.y - piece.position.y) == 1){
                        board.clearPiece(piece.position);
                    }
                }
            }
        }

        board.movePiece(this, newPosition);

        this.isMoved = true;
    }

    @Override
    public char getIcon() {
        return icon;
    }
}


public class Pawn extends Piece {
    private boolean isMoved = false;
    private final char icon;
    public Pawn(PlayerColor color, Position position) {
        super(color, position);
        icon = (playerColor == PlayerColor.White) ? '♟' : '♙';
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
            if(offsetY * direction != 1){
                return false;
            }

            return !ChessGame.GetInstance().GetBoard().isFreePosition(newPosition) || canDoEnPassant(newPosition);
        }

        return true;
    }

    private boolean canDoEnPassant(Position newPosition) {
        var board = ChessGame.GetInstance().GetBoard();

        var lastBoardMove = board.getLastBoardMove();
        if(lastBoardMove != null && lastBoardMove.takenPiece == null && lastBoardMove.takenPiecePosition != null) {
            var pieceToCheck = board.getPiece(lastBoardMove.takenPiecePosition);

            if(pieceToCheck != null && pieceToCheck instanceof Pawn){
                if(Math.abs(pieceToCheck.position.y - lastBoardMove.movedPieceLastPosition.y) == 2){
                    if(pieceToCheck.position.x == newPosition.x && Math.abs(pieceToCheck.position.x - position.x) == 1 && Math.abs(newPosition.y - pieceToCheck.position.y) == 1){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void move(Position newPosition) {
        var board = ChessGame.GetInstance().GetBoard();
        if(canDoEnPassant(newPosition)){
            board.clearPiece(board.getLastBoardMove().takenPiecePosition);
        }

        board.movePiece(this, newPosition);

        this.isMoved = true;
    }

    @Override
    public char getIcon() {
        return icon;
    }
}

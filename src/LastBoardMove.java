public class LastBoardMove {
    Piece takenPiece;
    Position takenPiecePosition;
    Position movedPieceLastPosition;

    public LastBoardMove(Board board, Position takenPiecePosition, Position movedPieceLastPosition) {
        takenPiece = board.getPiece(takenPiecePosition);
        this.takenPiecePosition = takenPiecePosition;
        this.movedPieceLastPosition = movedPieceLastPosition;
    }
}
public class LastBoardMove {
    Piece takenPiece;
    Position movedPieceLastPosition;

    public LastBoardMove(Piece takenPiece, Position movedPieceLastPosition) {
        this.takenPiece = takenPiece;
        this.movedPieceLastPosition = movedPieceLastPosition;
    }
}
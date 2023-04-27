import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private Piece[][] fields;
    private LastBoardMove lastBoardMove;

    public Board() {
        this.fields = new Piece[8][8];
    }

    public Piece[][] getFields(){
        return this.fields;
    }

    public LastBoardMove getLastBoardMove() {
        return lastBoardMove;
    }

    public void placePiece(Piece piece) {
        setPiece(piece.position, piece);
    }

    public void movePiece(Piece piece, Position newPosition) {
        lastBoardMove = new LastBoardMove(this, newPosition, piece.position);
        setPiece(piece.position, null);
        piece.position = newPosition;
        setPiece(newPosition, piece);
    }

    public void clearPiece(Position position){
        setPiece(position, null);
    }

    public Piece getPiece(Position position) {
        return fields[position.x-1][position.y-1];
    }

    private void setPiece(Position position, Piece piece){
        fields[position.x - 1][position.y - 1] = piece;
    }

    public boolean isCheck(PlayerColor playerColor) {
        try{
            var opponentPieces = GetPiecesByColor(Helpers.GetOppositeColor(playerColor));
            var kingPiece = GetKingPiece(playerColor);

            return opponentPieces.stream().anyMatch(x -> x.isValidMove(kingPiece.position));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean canKingMove(King kingPiece, List<Piece> attackingPieces){
        return kingPiece.getKingPossibleMovesByCurrentPosition().stream().anyMatch(x -> !attackingPieces.stream().anyMatch(y -> y.isValidMove(x)));
    }

    public boolean isCheckmate(PlayerColor playerColor) {
        try{
            var opponentPieces = GetPiecesByColor(Helpers.GetOppositeColor(playerColor));
            var kingPiece = GetKingPiece(playerColor);

            List<Piece> attackingKingOpponentPieces = opponentPieces.stream().filter(x -> x.isValidMove(kingPiece.position)).collect(Collectors.toList());

            if(attackingKingOpponentPieces.size() == 0){
                return false;
            }
            else if(attackingKingOpponentPieces.size() == 1){
                if(kingPiece.isValidMove(attackingKingOpponentPieces.get(0).position)){
                    return false;
                }
            }

            boolean isCheckedByKnight = attackingKingOpponentPieces.stream().anyMatch(x -> x instanceof Knight);

            if(isCheckedByKnight) {
                return !canKingMove(kingPiece, attackingKingOpponentPieces);
            }

            HashMap<Integer, Piece> closestAttackingKingPiecesByVector = new HashMap<>();

            for (Piece piece : attackingKingOpponentPieces) {
                int vectorX = kingPiece.position.x - piece.position.x;
                int vectorY = kingPiece.position.y - piece.position.y;

                int vector = vectorX * 10 + vectorY;
                if(!closestAttackingKingPiecesByVector.containsKey(vector)){
                    closestAttackingKingPiecesByVector.put(vector, piece);
                }
                else{
                    var closestPiece = closestAttackingKingPiecesByVector.get(vector);

                    if(kingPiece.position.isCloserToPosition(piece.position, closestPiece.position)){
                        closestAttackingKingPiecesByVector.put(vector, piece);
                    }
                }
            }

            ArrayList<Piece> closestAttackingKingPieces = new ArrayList<>(closestAttackingKingPiecesByVector.values());

            if(closestAttackingKingPieces.size() > 1){
                return true;
            }
            else if(closestAttackingKingPieces.size() == 1){
                List<Piece> defendingPieces = GetPiecesByColor(playerColor).stream().filter(x -> !(x instanceof King)).collect(Collectors.toList());
                return !canKingMove(kingPiece, attackingKingOpponentPieces) && !canBlockOrAttackPiece(closestAttackingKingPieces.get(0), kingPiece, defendingPieces);
            }

            return false;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean canBlockOrAttackPiece(Piece piece, King kingPiece, List<Piece> defendingPieces) {
        int vectorX = piece.position.x - kingPiece.position.x;
        int vectorY = piece.position.y - kingPiece.position.y;

        if(vectorX == 0){
            for (int i = 1; i <= Math.abs(vectorY); i++) {
                var position = new Position(kingPiece.position.x, kingPiece.position.y + i * vectorY);
                if(defendingPieces.stream().anyMatch(x -> x.isValidMove(position))){
                    Piece piec =  defendingPieces.stream().filter(x -> x.isValidMove(position)).findFirst().get();
                    System.out.println(piec.getClass().getName() + "DEFENDING ON "+ position.x + " " + position.y);
                    return true;
                }
            }
        }
        else if(vectorY == 0){
            for (int i = 1; i <= Math.abs(vectorX); i++) {
                var position = new Position(kingPiece.position.x + i * vectorX, kingPiece.position.y);
                if(defendingPieces.stream().anyMatch(x -> x.isValidMove(position))){
                    Piece piec =  defendingPieces.stream().filter(x -> x.isValidMove(position)).findFirst().get();
                    System.out.println(piec.getClass().getName() + "DEFENDING ON "+ position.x + " " + position.y);
                    return true;
                }
            }
        }
        else{
            for (int i = 1; i <= Math.abs(vectorX); i++) {
                var position = new Position(kingPiece.position.x + i * vectorX, kingPiece.position.y + i * vectorY);
                if(defendingPieces.stream().anyMatch(x -> x.isValidMove(position))){
                    Piece piec =  defendingPieces.stream().filter(x -> x.isValidMove(position)).findFirst().get();
                    System.out.println(piec.getClass().getName() + "DEFENDING ON "+ position.x + " " + position.y);
                    return true;
                }
            }
        }

        return false;
    }

    public void initPieces(){
        PlaceWhitePieces();
        PlaceBlackPieces();
    }

    private void PlaceWhitePieces(){
        var playerColor = PlayerColor.White;

        placePiece(new Rook(playerColor, new Position(1, 1)));
        placePiece(new Knight(playerColor, new Position(2, 1)));
        placePiece(new Bishop(playerColor, new Position(3, 1)));
        placePiece(new Queen(playerColor, new Position(4, 1)));
        placePiece(new King(playerColor, new Position(5, 1)));
        placePiece(new Bishop(playerColor, new Position(6, 1)));
        placePiece(new Knight(playerColor, new Position(7, 1)));
        placePiece(new Rook(playerColor, new Position(8, 1)));

        for (int i = 1; i <= 8; i++) {
            placePiece(new Pawn(playerColor, new Position(i, 2)));
        }
    }

    private void PlaceBlackPieces(){
        var playerColor = PlayerColor.Black;

        placePiece(new Rook(playerColor, new Position(1, 8)));
        placePiece(new Knight(playerColor, new Position(2, 8)));
        placePiece(new Bishop(playerColor, new Position(3, 8)));
        placePiece(new Queen(playerColor, new Position(4, 8)));
        placePiece(new King(playerColor, new Position(5, 8)));
        placePiece(new Bishop(playerColor, new Position(6, 8)));
        placePiece(new Knight(playerColor, new Position(7, 8)));
        placePiece(new Rook(playerColor, new Position(8, 8)));

        for (int i = 1; i <= 8; i++) {
            placePiece(new Pawn(playerColor, new Position(i, 7)));
        }
    }

    public ArrayList<Piece> GetPiecesByColor(PlayerColor playerColor){
        var piecesByColor = new ArrayList<Piece>();

        for(int i=0; i < this.fields.length; i++){
            for(int j=0; j < this.fields[i].length; j++){
                if(this.fields[i][j] != null && this.fields[i][j].playerColor == playerColor){
                    piecesByColor.add(this.fields[i][j]);
                }
            }
        }

        return piecesByColor;
    }

    private King GetKingPiece(PlayerColor playerColor) throws Exception {
        for(int i=0; i < this.fields.length; i++){
            for(int j=0; j < this.fields[i].length; j++){
                if(this.fields[i][j] != null && this.fields[i][j].playerColor == playerColor && this.fields[i][j] instanceof King){
                    return (King) this.fields[i][j];
                }
            }
        }

        throw new Exception("Something went wrong! King is not on a board!");
    }

    public boolean isFreePosition(Position position){
        return getPiece(position) == null;
    }

    public static boolean isPositionValid(Position position) {
        return position.x >= 1 && position.x < 9 && position.y >= 1 && position.y < 9;
    }

    public void rollbackMove() {
        if(lastBoardMove == null){
            System.out.println("Something went wrong. There is no move to rollback.");
            return;
        }

        var piece = getPiece(lastBoardMove.takenPiecePosition);
        piece.position = lastBoardMove.movedPieceLastPosition;
        setPiece(lastBoardMove.takenPiecePosition, lastBoardMove.takenPiece);
        setPiece(lastBoardMove.movedPieceLastPosition, piece);
        lastBoardMove = null;
    }
}

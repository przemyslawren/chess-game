import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private Piece[][] fields;

    public Board() {
        this.fields = new Piece[8][8];
    }

    public void placePiece(Piece piece) {
        fields[piece.position.x][piece.position.y] = piece;
    }

    public void movePiece(Piece piece, Position newPosition) {
        // Logika przemieszczająca bierkę
    }

    public Piece getPiece(Position position) {
        return fields[position.x][position.y];
    }

    public boolean isCheck(PlayerColor playerColor) {
        try{
            var opponentPieces = GetPiecesByColor(PlayerColorHelpers.GetOppositeColor(playerColor));
            var kingPiece = GetKingPiece(playerColor);

            return opponentPieces.stream().anyMatch(x -> x.isValidMove(kingPiece.position));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCheckmate(PlayerColor playerColor) {
        // opponenciCelujacyWKrola -> array broniacych pozycji
        // sprawdzic czy obronca moze obronic krola

        if(isCheck(playerColor)){
            // ruchKrola
            // zaslonienie krola
            // zbicie opponenta
        }

        return false;
    }

    private void PlacePieces(){
        PlaceWhitePieces();
        PlaceBlackPieces();
    }

    private void PlaceWhitePieces(){
        var playerColor = PlayerColor.White;
        placePiece(new Rook(playerColor, new Position(0, 0)));
        //reszta figur
    }

    private void PlaceBlackPieces(){
        //analogicznie czarne
    }

    private ArrayList<Piece> GetPiecesByColor(PlayerColor playerColor){
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

    private Piece GetKingPiece(PlayerColor playerColor) throws Exception {
        for(int i=0; i < this.fields.length; i++){
            for(int j=0; j < this.fields[i].length; j++){
                if(this.fields[i][j] != null && this.fields[i][j].playerColor == playerColor && this.fields[i][j] instanceof King){
                    return this.fields[i][j];
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
}

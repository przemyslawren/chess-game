public class ChessGame {
    private static ChessGame instance;

    public static ChessGame GetInstance() {
        if(instance == null) {
            instance = new ChessGame();
        }

        return instance;
    }

    private Board board;
    private boolean isDraw;
    private PlayerColor currentTurn = PlayerColor.WHITE;

    public Board GetBoard() {
        return this.board;
    }

    public ChessGame() {
        board = new Board();
        isDraw = false;
    }

    public void handleTurn(){
        if(board.isCheckmate(currentTurn)){
            System.out.println("Checkmate! Player " + PlayerColorHelpers.GetOppositeColor(currentTurn) + " wins!");
        }
        else if(board.isCheck(currentTurn)) {

        }
        else{

        }
    }

    public void requestMove(Piece piece, Position newPosition) {
        // Logika sprawdzająca ruch i wykonująca go
    }

    public void offerDraw() {
        // Logika ogłaszająca remis
    }

    public void printBoard() {
        // Logika wyświetlająca tekstową reprezentację planszy
    }
}

import java.util.Scanner;

public class ChessGame {
    private static ChessGame instance = null;

    public static ChessGame GetInstance() {
        if(instance == null) {
            instance = new ChessGame();
        }

        return instance;
    }

    private Board board = new Board();
    private boolean isDraw;
    private MoveHandler moveHandler;
    private PlayerColor currentTurn = PlayerColor.White;

    public Board GetBoard() {
        return this.board;
    }

    public void startGame() {
        //sprawdzanie save'a
        //System.out.println("Loading save...");
        //inne ladowanie boarda
        System.out.println("CONSOLE CHESS");
        System.out.println("Pass your save name, otherwise press ENTER to start!");
        System.out.println("Hint! To save a game, pass: "+ InputAction.SAVE.toString().toLowerCase());
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        SaveManager saveManager = new SaveManager();
        if (!fileName.isEmpty()) {
            saveManager.loadBoardFromFile(fileName);
        } else {
            startNewGame();
        }
        moveHandler = new MoveHandler();
        isDraw = false;
        handleTurn();
    }
    public static void startNewGame() {
        System.out.println("Starting a new game.");
        ChessGame.GetInstance().GetBoard().resetBoard();
    }
    public void handleTurn(){
        Drawer.DrawBoard(board.getFields());

        if(board.isCheckmate(currentTurn)){
            System.out.println("Checkmate! Player " + Helpers.GetOppositeColor(currentTurn) + " wins!");
        }
        else if(board.isCheck(currentTurn)) {
            System.out.println("Current turn: " + currentTurn);
            System.out.println("Check!");
        }
        else{
            System.out.println("Current turn: " + currentTurn);

            moveHandler.handleInput();
            var moveInfo = moveHandler.getMoveInfo();
            var pieceToMove = board.getPiece(moveInfo[0]);

            if(pieceToMove == null || currentTurn != pieceToMove.playerColor){
                System.out.println("There's no your piece on position: [" + moveInfo[0].x+ ", " + moveInfo[0].y+"]");
                handleTurn();
            }

            if(!pieceToMove.isValidMove(moveInfo[1])){
                System.out.println("Piece " + pieceToMove + "cannot move to: [" + moveInfo[1].x+ ", " + moveInfo[1].y+"]");
                handleTurn();
            }

            //move
            //switch currentTurn
        }
    }

    public void requestMove(Piece piece, Position newPosition) {
        // Logika sprawdzająca ruch i wykonująca go
    }

    public void offerDraw() {
        // Logika ogłaszająca remis
    }
}

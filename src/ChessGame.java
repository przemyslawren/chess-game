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
    private MoveHandler moveHandler;
    private PlayerColor currentTurn = PlayerColor.White;

    public PlayerColor getCurrentTurn() {
        return currentTurn;
    }

    public Board GetBoard() {
        return this.board;
    }

    public void startGame() {
        System.out.println("CONSOLE CHESS");
        System.out.println("Pass your save name, otherwise press ENTER to start!");
        System.out.println("Hint! To save a game, pass: "+ InputAction.SAVE.toString().toLowerCase() + " and to offer draw pass draw");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        SaveManager saveManager = new SaveManager();
        if (!fileName.isEmpty()) {
            board = saveManager.loadBoardFromFile(fileName);
        } else {
            initBoard();
        }

        moveHandler = new MoveHandler();
        handleTurn();
    }

    public void initBoard() {
        System.out.println("Starting a new game.");
        board = new Board();
        board.initPieces();
    }

    public void setDraw() {
        System.out.println("Draw!");
        startGame();
    }

    public void handleTurn(){
        Drawer.DrawBoard(board.getFields());

        if(board.isCheckmate(currentTurn)){
            System.out.println("Checkmate! Player " + Helpers.GetOppositeColor(currentTurn) + " wins!");
            startGame();
        }
        else{
            if(board.isCheck(currentTurn)){
                System.out.println("Check!");
            }

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

            pieceToMove.move(moveInfo[1]);

            if(board.isCheck(currentTurn)){
                System.out.println("You cannot move to this position, because its check! Try again.");
                board.rollbackMove();
                handleTurn();
            }

            currentTurn = Helpers.GetOppositeColor(currentTurn);
            handleTurn();
        }
    }

    public void offerDraw() {
        // Logika ogłaszająca remis
    }
}

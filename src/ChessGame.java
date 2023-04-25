import java.io.*;
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
        if (!fileName.isEmpty()) {
            loadBoardFromFile(fileName);
        } else {
            System.out.println("Starting a new game.");
            board = new Board();
        }
        moveHandler = new MoveHandler(this);
        isDraw = false;
        handleTurn();
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

    public void saveBoardToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (int i = 0; i < board.getFields().length; i++) {
                for (int j = 0; j < board.getFields()[i].length; j++) {
                    Piece piece = board.getFields()[i][j];
                    if (piece != null) {
                        int pieceType = getPieceType(piece);
                        int x = piece.position.x;
                        int y = piece.position.y;
                        int color = piece.playerColor == PlayerColor.White ? 0 : 1;

                        int encodedPiece = (pieceType & 0b111) | ((x & 0b1111) << 3) | ((y & 0b1111) << 7) | (color << 11);
                        fos.write((encodedPiece >> 8) & 0xFF);
                        fos.write(encodedPiece & 0xFF);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving board to file: " + e.getMessage());
        }
    }
    public void loadBoardFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            board = new Board(); // Reset board

            int byte1, byte2;
            while ((byte1 = fis.read()) != -1 && (byte2 = fis.read()) != -1) {
                int encodedPiece = (byte1 << 8) | byte2;

                int pieceType = encodedPiece & 0b111;
                int x = (encodedPiece >> 3) & 0b1111;
                int y = (encodedPiece >> 7) & 0b1111;
                PlayerColor color = ((encodedPiece >> 11) & 1) == 0 ? PlayerColor.White : PlayerColor.Black;

                Piece piece = createPieceFromType(pieceType, color, new Position(x, y));
                if (piece != null) {
                    board.placePiece(piece);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading board from file: " + e.getMessage());
            System.out.println("Starting new game!");
        }
    }
    private int getPieceType(Piece piece) {
        if (piece instanceof King) {
            return 1;
        } else if (piece instanceof Queen) {
            return 2;
        } else if (piece instanceof Rook) {
            return 3;
        } else if (piece instanceof Bishop) {
            return 4;
        } else if (piece instanceof Knight) {
            return 5;
        } else if (piece instanceof Pawn) {
            return 0;
        }
        return -1;
    }

    private Piece createPieceFromType(int pieceType, PlayerColor color, Position position) {
        switch (pieceType) {
            case 1:
                return new King(color, position);
            case 2:
                return new Queen(color, position);
            case 3:
                return new Rook(color, position);
            case 4:
                return new Bishop(color, position);
            case 5:
                return new Knight(color, position);
            case 0:
                return new Pawn(color, position);
            default:
                return null;
        }
    }

    public void requestMove(Piece piece, Position newPosition) {
        // Logika sprawdzająca ruch i wykonująca go
    }

    public void offerDraw() {
        // Logika ogłaszająca remis
    }
}

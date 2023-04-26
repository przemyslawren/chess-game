import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveManager {
    private Board board;

    public SaveManager() {
        this.board = new Board();
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
            System.out.println("Board saved to file: " + fileName);
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
            ChessGame.startNewGame();
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
}

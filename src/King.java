import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class King extends Piece {
    private final char icon;
    public King(PlayerColor playerColor, Position position) {
        super(playerColor, position);
        icon = (playerColor == PlayerColor.White) ? '♚' : '♔';
    }

    @Override
    protected boolean isValidMoveUniq(Position newPosition) {
        //roszada
        var offsetX = newPosition.x - position.x;
        var offsetY = newPosition.y - position.y;

        var isValidMove = Math.abs(offsetX) <= 1 && Math.abs(offsetY) <= 1;

        var opponentPieces = ChessGame.GetInstance().GetBoard().GetPiecesByColor(Helpers.GetOppositeColor(playerColor));

        List<Piece> oppositePiecesLookingAtNewPosition = opponentPieces.stream().filter(x -> x.isValidMove(newPosition)).collect(Collectors.toList());

        return isValidMove && (ChessGame.GetInstance().GetBoard().isFreePosition(newPosition) || oppositePiecesLookingAtNewPosition.size() == 0);
    }

    @Override
    public void move(Position newPosition) {
        //roszada
    }

    @Override
    public String toString() {
        return playerColor.name() + " " + "King ";
    }

    @Override
    public char getIcon() {
        return icon;
    }

    public ArrayList<Position> getKingPossibleMovesByCurrentPosition() {
        Position[] kingMovesToCheck = {
                new Position(0,1),
                new Position(1, 1),
                new Position(1, 0),
                new Position(1, -1),
                new Position(0, -1),
                new Position(-1, -1),
                new Position(-1, 0),
                new Position(-1, 1),
        };

        return Arrays.stream(kingMovesToCheck).map(x -> new Position(position.x + x.x, position.y + x.y))
                .filter(Board::isPositionValid).collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
}
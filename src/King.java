import java.util.ArrayList;
import java.util.Arrays;

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

        //czy king moze zbic i nie spowoduje szacha
        
        return isValidMove && (ChessGame.GetInstance().GetBoard().isFreePosition(newPosition));
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
                .filter(Board::isPositionValid).filter(x -> isValidMove(x)).collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
}
public class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(Position position){
        return position.x == x && position.y == y;
    }

    public boolean isCloserToPosition(Position position, Position position1) {
        return Math.abs(position.x - x) + Math.abs(position.y - y) < Math.abs(position1.x - x) + Math.abs(position1.y - y);
    }
}

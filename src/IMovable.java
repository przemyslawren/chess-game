

public interface IMovable {
    boolean isValidMove(Position newPosition);
    void move(Position newPosition);
}
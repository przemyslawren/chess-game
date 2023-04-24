public class Helpers {
    public static PlayerColor GetOppositeColor(PlayerColor playerColor){
        return playerColor == PlayerColor.White ? PlayerColor.Black : PlayerColor.White;
    }

    public static boolean isCrossValidMove(Position position, Position newPosition){
        var offsetX = newPosition.x - position.x;
        var offsetY = newPosition.y - position.y;

        var dirX = offsetX > 0 ? 1 : -1;
        var dirY = offsetY > 0 ? 1 : -1;

        if(Math.abs(offsetX) != Math.abs(offsetY)){
            return false;
        }

        for(int x=1; x <= Math.abs(offsetX); x++){
            for(int y=1; y <= Math.abs(offsetY); y++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(position.x + (x * dirX), position.y + (y * dirY)))){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isHorizontalVerticalValidMove(Position currentPosition, Position newPosition) {
        var offsetX = newPosition.x - currentPosition.x;
        var offsetY = newPosition.y - currentPosition.y;

        var dirX = offsetX > 0 ? 1 : -1;
        var dirY = offsetY > 0 ? 1 : -1;

        if(offsetX != 0){
            if(offsetY != 0) {
                return false;
            }

            for(int x=1; x < Math.abs(offsetX); x++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(currentPosition.x + (x * dirX), currentPosition.y))){
                    return false;
                }
            }
        }
        else {
            for(int y=1; y < Math.abs(offsetY); y++){
                if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(currentPosition.x, currentPosition.y + (y * dirY)))){
                    return false;
                }
            }
        }

        return true;
    }
}
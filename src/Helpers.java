public class Helpers {
    public static InputAction GetInputAction(String line){
        if(line.toLowerCase().equals(InputAction.SAVE.toString().toLowerCase())){
            return InputAction.SAVE;
        }
        else if(line.toLowerCase().equals(InputAction.DRAW.toString().toLowerCase())){
            return InputAction.DRAW;
        }

        return InputAction.MOVE_INPUT;
    }

    public static PlayerColor GetOppositeColor(PlayerColor playerColor){
        return playerColor == PlayerColor.White ? PlayerColor.Black : PlayerColor.White;
    }

    public static boolean isCrossValidMoveOrAttack(Position position, Position newPosition){
        var offsetX = getOffsetX(position, newPosition);
        var offsetY = getOffsetY(position, newPosition);

        var dirX = offsetX > 0 ? 1 : -1;
        var dirY = offsetY > 0 ? 1 : -1;

        if(Math.abs(offsetX) != Math.abs(offsetY)){
            return false;
        }

        for(int i=1; i < Math.abs(offsetX); i++){
            if(!ChessGame.GetInstance().GetBoard().isFreePosition(new Position(position.x + (i * dirX), position.y + (i * dirY)))){
                return false;
            }
        }

        return true;
    }

    public static boolean isHorizontalVerticalValidMoveOrAttack(Position currentPosition, Position newPosition) {
        var offsetX = getOffsetX(currentPosition, newPosition);
        var offsetY = getOffsetY(currentPosition, newPosition);

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

    public static int getOffsetX(Position position, Position newPosition){
        return newPosition.x - position.x;
    }

    public static int getOffsetY(Position position, Position newPosition){
        return newPosition.y - position.y;
    }
}
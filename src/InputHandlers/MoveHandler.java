public class MoveHandler extends InputHandler {
    private Position[] moveInfo;

    @Override
    public String getHandlerActionName() {
        return "Type piece position and destination point separated by white space (ex. '11 22')'";
    }

    @Override
    public InputAction getInputAction() {
        return InputAction.MOVE_INPUT;
    }

    @Override
    protected void handleInputUnique(InputAction desiredAction) {
        moveInfo = handleGetMoveInfo(lastArgs);
        if(moveInfo == null){
            handleInput();
        }
    }

    public Position[] getMoveInfo() {
        return moveInfo;
    }

    public Position[] handleGetMoveInfo(String input) {
        var userInput = input.split("\\s+");
        if(userInput.length != 2){
            System.out.println("Passed wrong arguments, try again!");
            return null;
        }
        else{
            var x0 = Character.getNumericValue(userInput[0].charAt(0));
            var y0 = Character.getNumericValue(userInput[0].charAt(1));

            var x1 = Character.getNumericValue(userInput[1].charAt(0));
            var y1 = Character.getNumericValue(userInput[1].charAt(1));

            var origin = new Position(x0, y0);
            var destination = new Position(x1, y1);

            if(!Board.isPositionValid(origin) || !Board.isPositionValid(destination)){
                System.out.println("Passed positions out of board, try again!");
                return null;
            }

            return new Position[]{
              origin,
              destination
            };
        }
    }
}

import java.util.Scanner;

public class MoveHandler {
    public Position[] getMoveInfo() {
        System.out.println("Type piece position and destination point separated by white space (ex. '11 22')'");
        Scanner scanner = new Scanner(System.in);
        String[] userInput = scanner.nextLine().split("\\s+");

        if(userInput.length != 2){
            System.out.println("Passed wrong arguments, try again!");
            return getMoveInfo();
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
                return getMoveInfo();
            }

            return new Position[]{
              origin,
              destination
            };
        }
    }
}

import java.util.Scanner;
public abstract class InputHandler {
    protected String lastArgs;
    protected ChessGame chessGame;

    public InputHandler(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void handleInput(){
        System.out.println(getHandlerActionName());

        Scanner scanner = new Scanner(System.in);

        this.lastArgs = scanner.nextLine();

        var inputAction = Helpers.GetInputAction(lastArgs);
        switch(inputAction) {
            case SAVE: {
                System.out.println("Enter file name to save:");
                String fileName = scanner.nextLine();
                ChessGame.GetInstance().saveBoardToFile(fileName);
                System.out.println("Board saved to file: " + fileName);
                break;
            }
        }

        if(inputAction != getInputAction()){
            handleInput();
        }

        handleInputUnique(getInputAction());
    }
    protected abstract void handleInputUnique(InputAction desiredAction);
    public abstract String getHandlerActionName();

    public abstract InputAction getInputAction();
}
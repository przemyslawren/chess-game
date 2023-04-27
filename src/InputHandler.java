import java.util.Scanner;
public abstract class InputHandler {
    protected String lastArgs;

    public void handleInput(){
        System.out.println(getHandlerActionName());

        Scanner scanner = new Scanner(System.in);

        this.lastArgs = scanner.nextLine();

        var inputAction = Helpers.GetInputAction(lastArgs);
        switch(inputAction) {
            case SAVE: {
                System.out.println("Enter a file name to save:");
                String filename = scanner.nextLine();
                SaveManager saveManager = new SaveManager();
                saveManager.saveBoardToFile(ChessGame.GetInstance().GetBoard(), filename);
                break;
            }
            case DRAW: {
                System.out.println("Draw offer sent by: " + ChessGame.GetInstance().getCurrentTurn() + ". Type 'accept' to accept or anything else to decline.");
                String filename = scanner.nextLine();
                if(filename.toLowerCase().equals("accept")){
                    ChessGame.GetInstance().setDraw();
                    return;
                }
                System.out.println("Draw offer declined by: " + Helpers.GetOppositeColor(ChessGame.GetInstance().getCurrentTurn()));
                return;
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
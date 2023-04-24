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
                System.out.println("SAVING!!!");
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
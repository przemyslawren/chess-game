import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        Thread inputThread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line != null && line.equals("\u001B\u005B\u0033\u0035\u007E")) { // Ctrl + S key code
                        // Ctrl + S pressed
                        System.out.println("Ctrl + S pressed");
                        // add code here to handle the Ctrl + S event
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        inputThread.start();


        var chessGame = ChessGame.GetInstance();
        chessGame.startGame();
    }
}

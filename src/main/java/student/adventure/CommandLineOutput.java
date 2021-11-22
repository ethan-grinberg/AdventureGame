package student.adventure;

import java.util.Scanner;

/** The command line interface for playing the game */
public class CommandLineOutput {

  /** Starts the game loop that prompts user input and prints user output. */
  public static void startGameLoop(GameEngine gameEngine) {
    Scanner console = new Scanner(System.in);
    System.out.println(gameEngine.getCurrentRoom());

    while (!(gameEngine.getGameIsOver())) {
      System.out.print("> ");

      UserInput userInput = new UserInput(console.nextLine());

      String gameOutput = userInput.processCommand(gameEngine);

      // All the commands that don't need to print any information return an empty string
      if (gameOutput.equals("")) {
        continue;
      }

      System.out.println(gameOutput);
    }
  }
}

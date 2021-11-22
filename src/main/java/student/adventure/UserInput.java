package student.adventure;

import student.server.Command;

/** User Interface class for processing input */
public class UserInput {
  private String command;

  private String parameter;

  /** A list of all the possible commands the API can pass in */
  public static final String[] apiCommandOptions = new String[] {"go", "take", "drop", "quit"};

  /**
   * The restart command will always be an option in the game
   * Note: this is only a valid command in the API version
   */
  public static final String CONSTANT_COMMAND_RESTART = "restart";

  /** @return command as a string */
  public String getCommand() {
    return command;
  }

  /** @return flag as a string */
  public String getParameter() {
    return parameter;
  }

  /**
   * Constructs a user input object which has a command and a flag
   * @param inputStr user input as a string
   */
  public UserInput(String inputStr) {
    if (inputStr == null || inputStr.length() == 0) {
      command = "";
      parameter = "";
      return;
    }
    inputStr = inputStr.toLowerCase();
    String[] parsedString = inputStr.trim().split("\\s+", 2);

    command = parsedString[0];
    if (parsedString.length == 1) {
      parameter = "";
    } else {
      parameter = parsedString[1];
    }
  }

  /**
   * Alternate constructor to receive input from the API
   * @param command the Command object
   */
  public UserInput(Command command) {
    this.command = command.getCommandName().toLowerCase();
    this.parameter = command.getCommandValue().toLowerCase();
  }

  /**
   * @param gameEngine The instance of the game engine that you want to use
   * @return A string with the game's output
   */
  public String processCommand(GameEngine gameEngine) {
    switch (command) {
      case "quit":
      case "exit":
       return gameEngine.quit();
      case "examine":
        return gameEngine.examine();
      case "go":
        return gameEngine.go(parameter);
      case "take":
        return gameEngine.take(parameter);
      case "drop":
        return gameEngine.drop(parameter);
      case "history":
        return gameEngine.getHistory();
      default:
        String fullMessage = command + " " + parameter;
        return "I don't understand " + "\"" + fullMessage + "\"!";
    }
  }
}
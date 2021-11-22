package student.adventure;

import student.adventure.models.Direction;
import student.adventure.models.Item;
import student.adventure.models.Player;
import student.adventure.models.Room;
import student.server.AdventureState;
import student.server.GameStatus;

import java.util.*;

/** Updates a game instance based on commands */

public class GameEngine {
  private Game currentGame;

  private Room currentRoom;

  private boolean gameIsOver;

  private Player player;

  //Map from room name to room object
  private Map<String, Room> roomMap;

  private int currentId;

  //Indicator that the game engine is running from website API
  private boolean isAPI;

  private GameStatus currentGameStatus;

  /**
   * Makes an instance of the game engine from the API interface
   * @param game the instance of the game object
   */
  public GameEngine(Game game, int id) {
    currentGame = game;
    roomMap = new HashMap<>();

    //Set up room map.
    for (Room room : currentGame.getRooms()) {
      roomMap.put(room.getName(), room);
    }

    currentRoom = roomMap.get(currentGame.getStartingRoom());
    //Set up new player info
    player = new Player(currentRoom);
    currentId = id;
    //Indicates that a game is running from the website UI
    isAPI = true;
    //Set initial game status
    setCurrentGameStatus(currentRoom.getDescription());
  }

  /**
   * Alternate constructor for the command line interface version of the game
   * @param game instance of the Game object
   */
  public GameEngine(Game game) {
    this(game, 0);
  }

  /**
   * exits out of the game and updates the Game Status accordingly
   * @return an empty string
   */
  public String quit() {
    gameIsOver = true;
    setCurrentGameStatus("Sorry to see you quit! Maybe listen to a motivational speech!");
    return "";
  }

  /** @return information about a room as a string */
  public String examine() {
    return currentRoom.toString();
  }

  /**
   * Changes the current room based on the direction inputted and checks for winner
   * Updates the Game Status object accordingly
   * @param inputDirectionName the direction user wants to go in as a string
   * @return The new room info or the winning message as a string. Or error message.
   */
  public String go(String inputDirectionName) {
    Direction inputDirection = null;

    for (Direction direction : currentRoom.getDirections()) {
      String formattedDirectionName = direction.getDirectionName().toLowerCase();

      if (formattedDirectionName.equals(inputDirectionName)) {
        inputDirection = direction;
        break;
      }
    }

    if (inputDirection == null) {
      return "I can't go " + "\"" + inputDirectionName + "\"!";
    }

    currentRoom = roomMap.get(inputDirection.getRoom());

    player.addToRoomHistory(currentRoom.getName());

    if (currentRoom.getName().equals(currentGame.getEndingRoom())) {
      return setWinner();
    }

    setCurrentGameStatus(currentRoom.getDescription());
    return currentRoom.toString();
  }

  /**
   * Stops the game, and sets the game status to winning
   * @return the winning message if there's a winner
   */
  private String setWinner() {
    gameIsOver = true;
    String message = "Congratulations! You have become rich! You win at life!";
    setCurrentGameStatus(message);
    return message;
  }

  /**
   * Picks up an item in a room and updates the user's inventory
   * Updates the Game Status object accordingly
   * @param inputItemName the item user wants to take as a string
   * @return an empty string or error message string
   */
  public String take(String inputItemName) {
    Item inputItem = null;
    for (Item item : currentRoom.getItems()) {
      String formattedItemName = item.getName().toLowerCase();

      if (formattedItemName.equals(inputItemName)) {
        inputItem = item;
      }
    }
    if (inputItem == null) {
      return "There is no item " + "\"" + inputItemName + "\" in the room.";
    }

    player.addToInventory(inputItem);
    currentRoom.removeItem(inputItem);

    setCurrentGameStatus(currentRoom.getDescription());
    return "";
  }

  /**
   * Drops an item, updates user's inventory, and updates the room's items.
   * Updates the Game Status object accordingly
   * @param inputItemName the item the user wants to drop as a string
   * @return an empty string or an error message string
   */
  public String drop(String inputItemName) {
    Item inputItem = null;
    for (Item item : player.getInventory()) {
      String formattedItemName = item.getName().toLowerCase();
      if (formattedItemName.equals(inputItemName)) {
        inputItem = item;
      }
    }
    if (inputItem == null) {
      return "You don't have " + "\"" + inputItemName + "\"!";
    }

    currentRoom.addItem(inputItem);
    player.removeFromInventory(inputItem);
    setCurrentGameStatus(currentRoom.getDescription());
    return "";
  }

  /** @return the room history of the player as a string */
  public String getHistory() {
    return player.getRoomHistory().toString();
  }

  /**
   * A helper method for getting the command options for a game status.
   * @return a Map from command as a string to a string list of parameters
   */
  private Map<String, List<String>> getCommandOptions() {
    //Initializes an empty list for no argument commands
    ArrayList<String> emptyList =  new ArrayList<>();
    emptyList.add("");

    Map<String, List<String>> commandOptions = new HashMap<>();

    //This command will always be an option
    commandOptions.put(UserInput.CONSTANT_COMMAND_RESTART, emptyList);

    if (gameIsOver) {
      return commandOptions;
    }

    for (String command : UserInput.apiCommandOptions) {
      switch (command) {
        case "go":
          commandOptions.put(command, currentRoom.getDirectionNames());
          break;
        case "take":
          if (currentRoom.getItems().size() != 0) {
            commandOptions.put(command, currentRoom.getItemNames());
          }
          break;
        case "quit":
          commandOptions.put(command, emptyList);
          break;
        case "drop":
          if (player.getInventory().size() != 0) {
            commandOptions.put(command, player.getInventoryNames());
          }
          break;
        default:
          break;
      }
    }
    return commandOptions;
  }

  /**
   * Sets the current Game Status that can be retrieved by Adventure Service
   * @param message the message that should be sent to the API
   */
  private void setCurrentGameStatus(String message) {
    if (isAPI) {
      currentGameStatus =
        new GameStatus(
          false,
          currentId,
          message,
          currentRoom.getImageURL(),
          currentRoom.getVideoURL(),
          new AdventureState(player.getInventory().toString(), player.getRoomHistory().toString()),
          getCommandOptions());
    }
  }

  //The following three getters and setters are packaged private only for testing.
  void setCurrentRoom(String roomName) {
    currentRoom = roomMap.get(roomName);
  }

  Room getCurrentRoom() {
    return currentRoom;
  }

  void setInventory(String[] items) {
    for (String item : items) {
      player.addToInventory(new Item(item));
    }
  }

  /** @return the current game status object for this instance */
  public GameStatus getCurrentGameStatus() {
    return currentGameStatus;
  }

  /** @return if game is over or not as a boolean */
  public boolean getGameIsOver() {
    return gameIsOver;
  }

  /** @return the current player object of the game */
  public Player getPlayer() {
    return player;
  }
}
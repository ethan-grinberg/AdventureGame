package student.server;

import student.adventure.Game;
import student.adventure.GameEngine;
import student.adventure.LoadGame;
import student.adventure.UserInput;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class GetRichAdventureService implements AdventureService {

  private int instanceNumber;

  private Map<Integer, GameEngine> gameInstances = new HashMap<>();


  @Override
  public void reset() {
    gameInstances.clear();
    instanceNumber = 0;
  }

  @Override
  public int newGame() throws AdventureException {
    Game newGetRichGame = LoadGame.loadGameFromJsonFile(
      "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\rich.json");

    GameEngine newGameEngine = new GameEngine(newGetRichGame, instanceNumber);
    gameInstances.put(instanceNumber, newGameEngine);
    return instanceNumber++;
  }

  @Override
  public GameStatus getGame(int id) {
    GameEngine currentGame = gameInstances.get(id);
    return currentGame.getCurrentGameStatus();
  }

  @Override
  public boolean destroyGame(int id) {
    GameEngine selectedEngine = gameInstances.remove(id);
    return selectedEngine != null;
  }

  @Override
  public void executeCommand(int id, Command command) {
    //Special case for restart game
    if (command.getCommandName().equals(UserInput.CONSTANT_COMMAND_RESTART)) {
     restartGameInstance(id);
     return;
    }

    GameEngine currentGame = gameInstances.get(id);
    UserInput userInput = new UserInput(command);
    userInput.processCommand(currentGame);
  }

  //Not needed for this assignment.
  @Override
  public SortedMap<String, Integer> fetchLeaderboard() {
    return null;
  }

  /**
   * helper method to reset the game engine associated with that id
   * @param id as an int
   */
  private void restartGameInstance(int id) {
    Game newGetRichGame = LoadGame.loadGameFromJsonFile(
      "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\rich.json");

    GameEngine newGameEngine = new GameEngine(newGetRichGame, id);
    gameInstances.replace(id, newGameEngine);
  }
}
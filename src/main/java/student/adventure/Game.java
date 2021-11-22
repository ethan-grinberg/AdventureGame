package student.adventure;

import student.adventure.models.Room;

import java.util.List;

/** Stores the layout of a game and start method. */
public class Game {
  private String startingRoom;
  private String endingRoom;
  private List<Room> rooms;

  //These getters are packaged private only to be used in the Game Engine class
  /** @return startingRoom as a String */
  String getStartingRoom() {
    return startingRoom;
  }

  /** @return endingRoom as a String */
  String getEndingRoom() {
    return endingRoom;
  }

  /** @return List of Room objects*/
  List<Room> getRooms() {
    return rooms;
  }

  /** Method that instantiates the Game Engine and starts the game loop for the command line interface */
  public void start() {
    GameEngine gameEngine = new GameEngine(this);
    CommandLineOutput.startGameLoop(gameEngine);
  }
}
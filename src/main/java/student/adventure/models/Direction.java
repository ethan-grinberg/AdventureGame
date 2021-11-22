package student.adventure.models;

import java.util.List;

/** Stores Direction data from the game. */
public class Direction {
  private String directionName;
  private String room;

  /** @return direction name as a String */
  public String getDirectionName() {
    return directionName;
  }

  /** @return Room String */
  public String getRoom() {
    return room;
  }

  @Override
  public String toString() {
    return directionName;
  }
}
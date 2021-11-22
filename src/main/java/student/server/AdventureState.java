package student.server;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A class to represent values in a game state.
 *
 * Note: these fields should be JSON-serializable values, like Strings, ints, floats, doubles, etc.
 * Please don't nest objects, as the frontend won't know how to display them.
 *
 * Good example:
 * private String shoppingList;
 *
 * Bad example:
 * private ShoppingList shoppingList;
 */
@JsonSerialize
public class AdventureState {
    private String roomHistory;
    private String inventory;

    public AdventureState() {
    }

  /**
   * @param inventory the current inventory in the game engine.
   * @param roomHistory the current roomHistory in the game engine.
   */
  public AdventureState(String inventory, String roomHistory) {
        this.inventory = inventory;
        this.roomHistory = roomHistory;
    }

  /** @return the current room history */
  public String getRoomHistory() {
        return roomHistory;
    }

  /** @return current inventory */
  public String getInventory() {
        return inventory;
    }
}

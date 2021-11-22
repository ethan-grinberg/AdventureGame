package student.adventure.models;

import java.util.ArrayList;
import java.util.List;

/** Class that stores information about a player */
public class Player {
  private List<Item> inventory;

  private List<String> roomHistory;

  /** Makes a new player with an empty inventory */
  public Player(Room startingRoom) {
    inventory = new ArrayList<>();
    roomHistory = new ArrayList<>();
    roomHistory.add(startingRoom.getName());
  }

  /** @param item Item object to add to inventory */
  public void addToInventory(Item item) {
    inventory.add(item);
  }

  /** @param item Item object to remove from inventory */
  public void removeFromInventory(Item item) {
    inventory.remove(item);
  }

  /** @param string Room name to add to room history */
  public void addToRoomHistory(String string) {
    roomHistory.add(string);
  }

  /** @return A list of the item names in an inventory */
  public List<String> getInventoryNames() {
    List<String> inventoryNames = new ArrayList<>();
    for (Item item : inventory) {
      inventoryNames.add(item.getName());
    }
    return inventoryNames;
  }

  /** @return the current inventory as a list of items */
  public List<Item> getInventory() {
    return inventory;
  }

  /** @return the current room history of a player */
  public List<String> getRoomHistory() {
    return roomHistory;
  }
}
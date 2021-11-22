package student.adventure.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Stores data about a Room in a game. */
public class Room {
  private String name;

  private String description;

  private List<Direction> directions;

  private List<Item> items;

  private String imageURL;

  private String videoURL;

  /** @return name of the Room as a String */
  public String getName() {
    return name;
  }

  /** @return description of the Room as a String */
  public String getDescription() {
    return description;
  }

  /** @return A list of Directions from the room */
  public List<Direction> getDirections() {
    return directions;
  }

  /** @return A list of Item objects */
  public List<Item> getItems() {
    return items;
  }

  /** @return image URL as a string */
  public String getImageURL() {
    return imageURL;
  }

  /** @return video URL as a string */
  public String getVideoURL() {
    return videoURL;
  }

  /** @return A list of direction names for the room */
  public List<String> getDirectionNames() {
    List<String> directionNames = new ArrayList<>();
    for (Direction direction : directions) {
      directionNames.add(direction.getDirectionName());
    }
    return directionNames;
  }

  /** @return a list of item names */
  public List<String> getItemNames() {
    List<String> itemNames = new ArrayList<>();
    for (Item item : items) {
      itemNames.add(item.getName());
    }
    return itemNames;
  }
  /** @param item Item object to remove from the room */
  public void removeItem(Item item) {
    items.remove(item);
  }

  /** @param item Item object to add to the room */
  public void addItem(Item item) {
    items.add(item);
  }

  @Override
  public String toString() {
    String directionNames = directions.toString();
    String itemNames = items.toString();
    if (items.size() == 0) {
      return description
        + "\n"
        + "From here, you can go: "
        + directionNames;
    }
    return description
        + "\n"
        + "From here, you can go: "
        + directionNames
        + "\n"
        + "Items visible: "
        + itemNames;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Room room = (Room) o;
    return Objects.equals(name, room.name);
  }
}

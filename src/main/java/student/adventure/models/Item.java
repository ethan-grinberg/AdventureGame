package student.adventure.models;

/** Stores data about a game Item. */
public class Item {
  private String name;

  /** @param setName Name of the item as a String */
  public Item(String setName) {
    name = setName;
  }
  /** @return name of the item as a String*/
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
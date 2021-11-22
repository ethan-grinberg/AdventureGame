package student.adventure;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for loading a game from a file and checking its schema. */
public class LoadGameTest {
  //File loading tests
  @Test
  public void testLoadJsonValidGame() {
    Game newGame =
        LoadGame.loadGameFromJsonFile(
            "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\rich.json");
    assertEquals(11, newGame.getRooms().size());
    assertEquals("NotRich", newGame.getStartingRoom());
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadJsonNullFile() {
    Game newGame = LoadGame.loadGameFromJsonFile(null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadJsonFileNotFound() {
    Game newGame = LoadGame.loadGameFromJsonFile("");
  }

  //Invalid schema tests
  //Created a different test json file for each test.
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNoRooms() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNoRooms.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNoRoomName() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNoRoomName.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNoEndingRoom() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNoEndingRoom.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNoStartingOrEndingRooms() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNoStartingEnding.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameDuplicateRooms() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testDuplicateRooms.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNullDescription() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNullDescription.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNoDirections() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNoDirections.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameLeadsNoWhere() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testLeadsNoWhere.json");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testLoadGameNullItem() {
    Game newGame =
      LoadGame.loadGameFromJsonFile(
        "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\testNullItem.json");
  }
}
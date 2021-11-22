package student.adventure;

import org.junit.Before;
import org.junit.Test;
import student.adventure.models.Item;

import java.util.List;

import static org.junit.Assert.*;

/** Game engine test suite. */

public class GameEngineTest {
  Game newGame;
  GameEngine gameEngine;

  @Before
  public void setUp() {
    newGame =
        LoadGame.loadGameFromJsonFile(
            "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\rich.json");
    gameEngine = new GameEngine(newGame);
  }

  //Invalid command tests.
  @Test
  public void testInvalidCommandTypo() {
    UserInput userInput = new UserInput("exmaine");
    String output = userInput.processCommand(gameEngine);

    assertEquals("I don't understand \"exmaine \"!", output);
  }

  @Test
  public void testInvalidCommandNoCommand() {
    UserInput userInput = new UserInput("");
    String output = userInput.processCommand(gameEngine);

    assertEquals("I don't understand \" \"!", output);
  }

  // Tests for exit and quit commands.
  @Test
  public void testValidQuitCommand() {
    UserInput userInput = new UserInput("quit");
    userInput.processCommand(gameEngine);

    assertTrue(gameEngine.getGameIsOver());
  }

  @Test
  public void testValidExitCommand() {
    UserInput userInput = new UserInput("exit");
    userInput.processCommand(gameEngine);

    assertTrue(gameEngine.getGameIsOver());
  }

  @Test
  public void testValidExitExtraInformation() {
    UserInput userInput = new UserInput("exit please");
    userInput.processCommand(gameEngine);

    assertTrue(gameEngine.getGameIsOver());
  }

  // Tests for examine command.
  @Test
  public void testExamineCommandRandomRoom() {
    gameEngine.setCurrentRoom("Home");
    UserInput userInput = new UserInput("examine");
    String output = userInput.processCommand(gameEngine);

    assertEquals(
        "You are now at home with your new computer.\n"
            + "From here, you can go: [make an app, play video games]\n"
            + "Items visible: [burrito, book]",
        output);
  }

  @Test
  public void testExamineCommandStartingRoom() {
    UserInput userInput = new UserInput("examine");
    String output = userInput.processCommand(gameEngine);

    assertEquals(
        "You are currently not rich.\n"
            + "From here, you can go: [get lottery ticket, get computer, listen to motivational speaker]\n"
            + "Items visible: [empty wallet]",
        output);
  }
  @Test
  public void testExamineCommandExtraInformation() {
    UserInput userInput = new UserInput("examine please");
    String output = userInput.processCommand(gameEngine);

    assertEquals("You are currently not rich.\n"
      + "From here, you can go: [get lottery ticket, get computer, listen to motivational speaker]\n"
      + "Items visible: [empty wallet]", output);
  }
  //Go command tests.
  @Test
  public void testValidGoCommand() {
    gameEngine.setCurrentRoom("ConvenientStore");
    UserInput userInput = new UserInput("go check ticket");
    String output = userInput.processCommand(gameEngine);

    assertEquals("TicketChecker", gameEngine.getCurrentRoom().getName());
    assertEquals(
        "You just checked your ticket and no surprise, you lost.\n"
            + "From here, you can go: [home]\n"
            + "Items visible: [ticket scratcher]",
        output);
  }
  @Test
  public void testInvalidGoCommand() {
    UserInput userInput = new UserInput("go get booed off the stage");
    String output = userInput.processCommand(gameEngine);

    assertEquals("I can't go \"get booed off the stage\"!", output);
  }
  @Test
  public void testGoCommandWinner() {
    gameEngine.setCurrentRoom("WorkingApp");
    UserInput userInput = new UserInput("go sell app");
    String output = userInput.processCommand(gameEngine);

    assertTrue(gameEngine.getGameIsOver());
    assertEquals(
      "Congratulations! You have become rich! You win at life!",
      output);
  }
  @Test
  public void testInvalidGoCommandNoDirection() {
    UserInput userInput = new UserInput("go");
    String output = userInput.processCommand(gameEngine);
    assertEquals("I can't go \"\"!", output);
  }
  //Take command tests.
  @Test
  public void testValidTakeCommand() {
    UserInput userInput = new UserInput("take empty wallet");
    String output = userInput.processCommand(gameEngine);
    List<Item> inventory = gameEngine.getPlayer().getInventory();

    assertEquals("empty wallet", inventory.get(0).getName());
    assertEquals("", output);

    UserInput newUserInput = new UserInput("examine");
    String secondOutput = newUserInput.processCommand(gameEngine);

    assertEquals(
        "You are currently not rich.\n"
            + "From here, you can go: [get lottery ticket, get computer, listen to motivational speaker]",
        secondOutput);
  }
  @Test
  public void testInvalidTakeCommandNoItem() {
    UserInput userInput = new UserInput("take burrito");
    String output = userInput.processCommand(gameEngine);
    List<Item> inventory = gameEngine.getPlayer().getInventory();

    assertEquals(0, inventory.size());
    assertEquals("There is no item \"burrito\" in the room.", output);
  }
  @Test
  public void testInvalidTakeCommandNoInputItem() {
    UserInput userInput = new UserInput("take");
    String output = userInput.processCommand(gameEngine);
    List<Item> inventory = gameEngine.getPlayer().getInventory();

    assertEquals(0, inventory.size());
    assertEquals("There is no item \"\" in the room.", output);
  }
  // Drop command tests
  @Test
  public void testValidDropCommandSameItemName() {
    gameEngine.setInventory(new String[] {"burrito", "empty wallet"});
    UserInput userInput = new UserInput("drop empty wallet");
    String output = userInput.processCommand(gameEngine);

    assertEquals(1, gameEngine.getPlayer().getInventory().size());
    assertEquals(2, gameEngine.getCurrentRoom().getItems().size());

    UserInput newUserInput = new UserInput("examine");
    String newOutput = newUserInput.processCommand(gameEngine);

    assertEquals(
        "You are currently not rich.\n"
            + "From here, you can go: [get lottery ticket, get computer, listen to motivational speaker]\n"
            + "Items visible: [empty wallet, empty wallet]",
        newOutput);
  }
  @Test
  public void testInvalidDropCommandNoItem() {
    gameEngine.setInventory(new String[]{"burrito"});
    UserInput userInput = new UserInput("drop empty wallet");
    String output = userInput.processCommand(gameEngine);

    assertEquals("You don't have \"empty wallet\"!", output);
  }
  @Test
  public void testInvalidDropCommandEmptyInventory() {
    UserInput userInput = new UserInput("drop empty wallet");
    String output = userInput.processCommand(gameEngine);
    assertEquals("You don't have \"empty wallet\"!", output);
  }
}
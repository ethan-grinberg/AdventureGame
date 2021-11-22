package student.adventure;

import org.junit.Test;
import static org.junit.Assert.*;

/** Test suite for making sure user input is parsed correctly in the right format. */
public class UserInputTest {
  @Test
  public void testUserInputSpaces() {
    UserInput userInput = new UserInput("  go        East  ");
    assertEquals("go", userInput.getCommand());
    assertEquals("east", userInput.getParameter());
  }
  @Test
  public void testUserInputSpacesNoParameter() {
    UserInput userInput = new UserInput("  examine       ");
    assertEquals("examine", userInput.getCommand());
    assertEquals("", userInput.getParameter());
  }
  @Test
  public void testUserInputUpperCase() {
    UserInput userInput = new UserInput("thROW ITem");
    assertEquals("throw", userInput.getCommand());
    assertEquals("item", userInput.getParameter());
  }
  @Test
  public void testUserInputMultipleSpaces() {
    UserInput userInput = new UserInput("take that item");
    assertEquals("take", userInput.getCommand());
    assertEquals("that item", userInput.getParameter());
  }
  @Test
  public void testUserInputEmptyString() {
    UserInput userInput = new UserInput("");
    assertEquals("", userInput.getCommand());
    assertEquals("", userInput.getParameter());
  }
  @Test
  public void testUserInputSpaceNoCommand() {
    UserInput userInput = new UserInput(" ");
    assertEquals("", userInput.getCommand());
    assertEquals("", userInput.getParameter());
  }
  @Test
  public void testUserInputSpacesNoCommand() {
    UserInput userInput = new UserInput("     ");
    assertEquals("", userInput.getCommand());
    assertEquals("", userInput.getParameter());
  }
  @Test
  public void testUserInputNoParameter() {
    UserInput userInput = new UserInput("examine");
    assertEquals("", userInput.getParameter());
  }
}

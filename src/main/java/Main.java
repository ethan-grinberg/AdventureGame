import student.adventure.*;

public class Main {
  public static void main(String[] args) {
    Game newGame =
        LoadGame.loadGameFromJsonFile(
            "C:\\Users\\ethan\\Documents\\School\\CS126\\AdventureGame\\src\\main\\resources\\rich.json");
    newGame.start();
  }
}

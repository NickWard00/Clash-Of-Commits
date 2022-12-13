package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.view.screens.EndGameScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

/**
 * @author James Qu
 */
class EndGameScreenTest extends DukeApplicationTest {
  private EndGameScreen winScreen;
  private Button playAgain;
  private Button startGameButton;

  @Override
  public void start(Stage stage) {
    winScreen = new EndGameScreen(stage, ResourceBundle.getBundle("ResourceBundles/LabelsBundle"), 0, "The Beginning",true);
    stage.setScene(winScreen.makeScene());
    stage.show();
    playAgain = lookup("#playAgainButton").query();
  }
  @Test
  void testWinPlayAgainButton() {
    clickOn(playAgain);
    startGameButton = lookup("#startGame").query();
    String expectedId = "Start Game";
    assertEquals(expectedId, startGameButton.getText());
  }

}

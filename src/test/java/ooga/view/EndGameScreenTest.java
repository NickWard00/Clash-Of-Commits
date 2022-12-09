package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.view.screens.EndGameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class EndGameScreenTest extends DukeApplicationTest {
  private EndGameScreen screen;
  private Scene scene;

  @BeforeEach
  void setup() {
    Stage stage = new Stage();
    screen = new EndGameScreen(stage, true);
  }

  @Test
  void testCreateScreen() {
    scene = screen.makeScene();
    String expectedId = "WinningScreen";
    String id = scene.getRoot().getId();
    assertEquals(expectedId, id);
  }

}

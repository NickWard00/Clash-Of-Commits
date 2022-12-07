package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import ooga.view.screens.EndGameScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class EndGameScreenTest extends DukeApplicationTest {
  private EndGameScreen screen;
  private Scene scene;

  @BeforeEach
  void setup() {
    screen = new EndGameScreen();
  }

  @Test
  void testCreateScreen() {
    scene = screen.makeScene();
    String expectedId = "WinningScreen";
    String id = scene.getRoot().getId();
    assertEquals(expectedId, id);
  }

}

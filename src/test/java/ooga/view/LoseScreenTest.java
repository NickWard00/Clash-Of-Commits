package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import ooga.view.screens.LoseScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class LoseScreenTest extends DukeApplicationTest {
  private LoseScreen screen;
  private Scene scene;

  @BeforeEach
  void setup() {
    screen = new LoseScreen();
  }

  @Test
  void testCreateScreen() {
    scene = screen.createScene();
    String expectedId = "LosingScreen";
    String id = scene.getRoot().getId();
    assertEquals(expectedId, id);
  }
}

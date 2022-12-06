package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import ooga.view.screens.WinScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class WinScreenTest extends DukeApplicationTest {
  private WinScreen screen;
  private Scene scene;

  @BeforeEach
  void setup() {
    screen = new WinScreen();
  }

  @Test
  void testCreateScreen() {
    scene = screen.makeScene();
    String expectedId = "WinningScreen";
    String id = scene.getRoot().getId();
    assertEquals(expectedId, id);
  }

}

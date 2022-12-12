package ooga.controller.gameState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.EntityView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class AdventureGameStateTest extends DukeApplicationTest {
  private static final int TEST_SCORE = 5;
  private static final int TEST_WIN_SCORE = 200;
  private static final String TEST_HERO = "Hero";
  private static final String TEST_IMAGE = "/sprites/hero/NORTH.gif";
  private AdventureGameState gameState;
  private Controller controller;
  private ResourceBundle label;
  private Map<String, EntityView> testEntities;
  private EntityView entity;

  @Override
  public void start(Stage stage) {
    testEntities = new HashMap<>();
    entity = new EntityView(TEST_IMAGE, TEST_HERO);
    label = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
    controller = new Controller(stage, "MainMap","The Beginning", label);
    testEntities.put(TEST_HERO, entity);
    gameState = new AdventureGameState(testEntities, controller);
  }

  @Test
  void testAdventureScoreUpdated() {
    gameState.updateScore(TEST_SCORE);
    assertEquals(gameState.getScore(), TEST_SCORE);
  }

  @Test
  void testWinConditionWhenWon() {
    gameState.updateScore(TEST_WIN_SCORE);
    boolean win = gameState.determineWin();
    assertTrue(win);
  }

  @Test
  void testWinConditionWhenNoWin() {
    gameState.updateScore(TEST_SCORE);
    boolean win = gameState.determineWin();
    assertFalse(win);
  }

}

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

class SurviveGameStateTest extends DukeApplicationTest {
  private static final int TEST_SCORE = 5;
  private static final int TEST_WIN_X = 2000;
  private static final int TEST_LOSE_X = 20;
  private static final String TEST_IMAGE = "/sprites/hero/EAST.gif";
  private static final String TEST_HERO = "Hero";
  private SurviveGameState gameState;
  private Controller controller;
  private ResourceBundle label;
  private Map<String, EntityView> testEntities;
  private EntityView hero;

  @Override
  public void start(Stage stage) {
    hero = new EntityView(TEST_IMAGE, TEST_HERO);
    label = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
    controller = new Controller(stage, "BulletHellMap","Survive", label);
    testEntities = new HashMap<>(Map.of(TEST_HERO, hero));
    gameState = new SurviveGameState(testEntities, controller);
  }

  @Test
  void testScoreUpdated() {
    gameState.updateScore(TEST_SCORE);
    assertEquals(gameState.getScore(), TEST_SCORE);
  }

  @Test
  void testWinConditionWhenWon() {
    hero.setX(TEST_WIN_X);
    boolean win = gameState.determineWin();
    assertTrue(win);

  }

  @Test
  void testWinConditionWhenNoWin() {
    hero.setX(TEST_LOSE_X);
    boolean win = gameState.determineWin();
    assertFalse(win);
  }
}

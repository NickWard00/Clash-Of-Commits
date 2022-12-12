package ooga.controller.gameState;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.controller.Controller;
import ooga.view.EntityView;

/**
 * This subclass helps determine what the win and lose conditions are for our survive game.
 *
 * @author James Qu
 */

public class SurviveGameState extends MapGameState {
  private static final ResourceBundle constants = ResourceBundle.getBundle(
      "ResourceBundles.ViewConstants");
  private int score;
  private EntityView mainHero;

  public SurviveGameState(Map<String, EntityView> viewEntities, Controller controller) {
    super(viewEntities, controller);
    mainHero = viewEntities.get("Hero");
  }

  /**
   * Method to determine if the survive game is won
   * @return boolean representing whether the adventure game is won or not
   */
  @Override
  public boolean determineWin(int score) {
    this.score = score;
    if (mainHero.getX() >= Double.parseDouble(constants.getString("surviveGameWinPosition"))) {
      return true;
    }
    return false;
  }

  /**
   * Method to get the score in the survive game
   * @return score in the game
   */
  public int getScore() {
    return this.score;
  }
}

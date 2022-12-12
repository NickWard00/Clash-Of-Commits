package ooga.controller.gameState;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.controller.Controller;
import ooga.model.entities.Entity;
import ooga.view.EntityView;

/**
 * This abstract superclass helps determine what the win and lose conditions are for our games and
 * helps in setting the next scene in the view.
 *
 * @author James Qu
 */

public abstract class MapGameState {
  private static final ResourceBundle viewConstants = ResourceBundle.getBundle(
      "ResourceBundles.ViewConstants");
  private Map<String, EntityView> viewEntities;
  private Map<String, Entity> modelEntities;
  private String hero;
  private Controller controller;

  public MapGameState(Map<String, EntityView> viewEntities, Controller controller) {
    this.viewEntities = viewEntities;
    this.controller = controller;
  }

  /**
   * Abstract method to determine if the game is won
   * @return boolean representing whether the certain game is won or not
   */
  public abstract boolean determineWin(int score);

  /**
   * Used to determine if the game is lost.
   * Since losing condition is the same across all game states, the superclass implements
   * the losing condition.
   *
   * @return boolean representing whether the certain game is lost or not
   */
  public boolean determineLost() {
    this.modelEntities = controller.getModelEntities();
    this.hero = controller.getMainHeroName();
    if (modelEntities.get(hero).getHp() == Integer.parseInt(viewConstants.getString("loseHealth"))) {
      return true;
    }
    return false;
  }
}

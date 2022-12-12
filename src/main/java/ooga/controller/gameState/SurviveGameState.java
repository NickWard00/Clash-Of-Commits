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
  private String heroName;

  public SurviveGameState(Map<String, EntityView> viewEntities, Controller controller) {
    super(viewEntities, controller);
    heroName = controller.getMainHeroName();
  }

  /**
   *
   * Method to determine if the survive game is won
   * @return boolean representing whether the survive game is won or not
   */
  @Override
  public boolean determineWin() {
    if (viewEntities.get(heroName).getX() >= Double.parseDouble(constants.getString("surviveGameWinPosition"))) {
      return true;
    }
    return false;
  }

}

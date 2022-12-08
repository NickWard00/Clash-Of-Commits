package ooga.controller.gameState;

import java.util.Map;
import ooga.controller.Controller;
import ooga.controller.gameState.MapGameState;
import ooga.view.EntityView;

/**
 * This subclass helps determine what the win and lose conditions are for our adventure type game.
 *
 * @author James Qu
 */

public class AdventureGameState extends MapGameState {
  private int score;

  public AdventureGameState(Map<String, EntityView> viewEntities, Controller controller) {
    super(viewEntities, controller);
  }

  @Override
  public boolean determineWin(int score) {
    this.score = score;
    if (score >= 100) {
      return true;
    }
    return false;
  }

}

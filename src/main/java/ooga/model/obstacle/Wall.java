package ooga.model.obstacle;

import ooga.model.Entity;
import ooga.model.obstacle.Obstacle;
import ooga.model.state.MovementState;

/**
 * This superclass represents all obstacles that will block movement or projectiles in general
 *
 * @author James Qu
 */
public abstract class Wall extends Obstacle {

  public Wall(Double x, Double y, boolean canBeDestroyed, boolean onScreen) {
    super(x, y, true, canBeDestroyed, onScreen);
  }

  public void block(Entity entity) {
    if (this.onScreen) {
      entity.changeMovement(MovementState.STATIONARY);
    }
  }
}

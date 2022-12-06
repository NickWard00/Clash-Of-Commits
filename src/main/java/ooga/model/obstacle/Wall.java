package ooga.model.obstacle;

import ooga.model.entities.Entity;
import ooga.model.state.MovementState;

/**
 * This superclass represents all obstacles that will block movement or projectiles in general
 *
 * @author James Qu
 */
public abstract class Wall extends Obstacle {
  /**
   * Constructor for Wall
   * @param x x coordinate of the wall
   * @param y y coordinate of the wall
   * @param canBeDestroyed boolean representing whether the wall can be destroyed
   * @param onScreen boolean representing whether the wall is on screen
   */
  public Wall(Double x, Double y, boolean canBeDestroyed, boolean onScreen) {
    super(x, y, true, canBeDestroyed, onScreen);
  }

  /**
   * Changes the movement state to stationary if the block is on screen and hits a wall
   * @param entity
   */
  public void block(Entity entity) {
    if (this.onScreen) {
      entity.changeMovement(MovementState.STATIONARY);
    }
  }
}

package ooga.model.obstacle;

import ooga.model.obstacle.Obstacle;

/**
 * This superclass represents all obstacles that will block movement or projectiles in general
 *
 * @author James Qu
 */
public abstract class Wall extends Obstacle {

  public Wall(Double x, Double y, boolean canBeDestroyed) {
    super(x, y, true, canBeDestroyed);
  }
}

package ooga.model.obstacle;

import ooga.model.obstacle.Obstacle;

/**
 * This class represents all obstacles that will block movement or projectiles
 *
 * @author James Qu
 */
public class Wall extends Obstacle {

  public Wall(Double x, Double y) {
    super(x, y, true);
  }

  //TODO: Implement what happens when something hits this obstacle
  public void block() {

  }
}

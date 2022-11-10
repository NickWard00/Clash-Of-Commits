package ooga.model.obstacle;

import ooga.model.obstacle.Obstacle;

/**
 * This class represents all obstacles that are able to be walked through or do not block projectiles
 *
 * @author James Qu
 */
public class Bush extends Obstacle {

  public Bush(Double x, Double y) {
    super(x, y, false);
  }


}

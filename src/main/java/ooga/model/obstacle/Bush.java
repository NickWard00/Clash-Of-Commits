package ooga.model.obstacle;

import ooga.model.obstacle.Obstacle;

/**
 * This class represents all obstacles that will exist the entire game and cannot be destroyed
 *
 * @author James Qu
 */
public class Bush extends Obstacle {

  public Bush(Double x, Double y) {
    super(x, y, false, false);
  }


}

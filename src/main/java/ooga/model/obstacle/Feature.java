package ooga.model.obstacle;

/**
 * This class represents all obstacles that will exist the entire game and cannot be destroyed
 * These are basically background features that will be added into the game
 *
 * @author James Qu
 */
public class Feature extends Obstacle {

  public Feature(Double x, Double y) {
    super(x, y, false, false, true);
  }


}

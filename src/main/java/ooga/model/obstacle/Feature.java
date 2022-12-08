package ooga.model.obstacle;

/**
 * This class represents all obstacles that will exist the entire game and cannot be destroyed
 * These are basically background features that will be added into the game
 *
 * @author James Qu
 */
public class Feature extends Obstacle {
  /**
   * Constructor for Feature, which cannot be destroyed
   * and is set to on screen, but can be moved through
   * @param x x coordinate of the feature
   * @param y y coordinate of the feature
   */
  public Feature(Double x, Double y) {
    super(x, y, false, false, true);
  }
}

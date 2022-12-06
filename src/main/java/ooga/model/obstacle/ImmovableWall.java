package ooga.model.obstacle;

/**
 * This class represents all wall obstacles that remain there for the entire game duration and
 * cannot be moved or destroyed.
 *
 * @author James Qu
 */
public class ImmovableWall extends Wall {
  /**
   * Constructor for ImmovableWall, which cannot be destroyed and is set to on screen
   * @param x x coordinate of the wall
   * @param y y coordinate of the wall
   */
  public ImmovableWall(Double x, Double y) {
    super(x, y, false, true);
  }
}

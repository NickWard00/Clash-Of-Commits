package ooga.model.obstacle;

/**
 * This class represents all wall obstacles that remain there for the entire game duration and
 * cannot be moved or destroyed.
 *
 * @author James Qu
 */
public class ImmovableWall extends Wall {

  public ImmovableWall(Double x, Double y) {
    super(x, y, false, true);
  }
}

package ooga.model.obstacle;

/**
 * The superclass to represent all obstacles within the game
 *
 * @author James Qu
 */
public class Obstacle {
  private Double xPosition;
  private Double yPosition;
  private boolean blocker;

  public Obstacle(Double x, Double y, boolean blocker) {
    this.xPosition = x;
    this.yPosition = y;
    this.blocker = blocker;
  }
}

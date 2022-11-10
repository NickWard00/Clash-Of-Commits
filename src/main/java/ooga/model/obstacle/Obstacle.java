package ooga.model.obstacle;

/**
 * The superclass to represent all obstacles within the game
 *
 * @author James Qu
 */
public abstract class Obstacle {
  private Double xPosition;
  private Double yPosition;
  private boolean blocker;
  private boolean canBeDestroyed;

  public Obstacle(Double x, Double y, boolean blocker, boolean canBeDestroyed) {
    this.xPosition = x;
    this.yPosition = y;
    this.blocker = blocker;
    this.canBeDestroyed = canBeDestroyed;
  }

//  public Obstacle makeObstacle() {
//
//  }
}

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

  //TODO: Implement a variable to see if the obstacle can be shown on screen?
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

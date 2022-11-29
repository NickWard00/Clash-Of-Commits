package ooga.model.obstacle;

/**
 * The superclass to represent all obstacles within the game
 *
 * @author James Qu
 */
public abstract class Obstacle {
  protected Double xPosition;
  protected Double yPosition;
  protected boolean blocker;
  protected boolean canBeDestroyed;
  protected boolean onScreen;
  public Obstacle(Double x, Double y, boolean blocker, boolean canBeDestroyed, boolean onScreen) {
    this.xPosition = x;
    this.yPosition = y;
    this.blocker = blocker;
    this.canBeDestroyed = canBeDestroyed;
    this.onScreen = onScreen;
  }

  public double getPositionX() {
    return this.xPosition;
  }

  public double getPositionY() {
    return this.yPosition;
  }

  public boolean getBlocker() {
    return this.blocker;
  }

  public boolean getDestroyable() {
    return this.canBeDestroyed;
  }
}

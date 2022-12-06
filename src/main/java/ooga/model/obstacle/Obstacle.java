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

  /**
   * Constructor for Obstacle
   * @param x x coordinate of the obstacle
   * @param y y coordinate of the obstacle
   * @param blocker boolean representing whether the obstacle blocks movement
   * @param canBeDestroyed boolean representing whether the obstacle can be destroyed
   * @param onScreen boolean representing whether the obstacle is on screen
   */
  public Obstacle(Double x, Double y, boolean blocker, boolean canBeDestroyed, boolean onScreen) {
    this.xPosition = x;
    this.yPosition = y;
    this.blocker = blocker;
    this.canBeDestroyed = canBeDestroyed;
    this.onScreen = onScreen;
  }

  /**
   * Gets the x coordinate of the obstacle
   * @return x coordinate of the obstacle
   */
  public double getPositionX() {
    return this.xPosition;
  }

  /**
   * Gets the y coordinate of the obstacle
   * @return y coordinate of the obstacle
   */
  public double getPositionY() {
    return this.yPosition;
  }

  /**
   * Gets the boolean representing whether the obstacle blocks movement
   * @return boolean representing whether the obstacle blocks movement
   */
  public boolean getBlocker() {
    return this.blocker;
  }

  /**
   * Gets the boolean representing whether the obstacle can be destroyed
   * @return boolean representing whether the obstacle can be destroyed
   */
  public boolean getDestroyable() {
    return this.canBeDestroyed;
  }

  /**
   * Gets the boolean representing whether the obstacle is on screen
   * @return boolean representing whether the obstacle is on screen
   */
  public boolean getOnScreen() {
    return this.onScreen;
  }
}

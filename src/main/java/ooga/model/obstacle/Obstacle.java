package ooga.model.obstacle;

import ooga.controller.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

  public static Obstacle makeObstacle(Class<? extends Obstacle> obstacleClass, double xPosition, double yPosition) {
    try {
      Obstacle newObstacle = obstacleClass.getConstructor(Double.class, Double.class).newInstance(xPosition, yPosition);
      Controller.getModelObstacles().put(Arrays.asList(xPosition, yPosition), newObstacle);
      return newObstacle;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void makeRandomObstacle(List<Class<? extends Obstacle>> possibleObstacles, double xPosition, double yPosition) {
    Random random = new Random();
    int randomObstacle = random.nextInt(possibleObstacles.size());
    makeObstacle(possibleObstacles.get(randomObstacle), xPosition, yPosition);
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

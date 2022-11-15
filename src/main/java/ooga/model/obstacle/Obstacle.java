package ooga.model.obstacle;

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

  public static Obstacle makeObstacle(Class<? extends Obstacle> obstacleClass, Map<String, String> attributes, Map<Integer, String> states) {
    try {
      Obstacle newObstacle = obstacleClass.getDeclaredConstructor(Map.class, Map.class).newInstance(attributes, states);
      return newObstacle;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void makeRandomObstacle(List<Class<? extends Obstacle>> possibleObstacles, Map<String, String> attributes, Map<Integer, String> states) {
    Random random = new Random();
    int randomObstacle = random.nextInt(possibleObstacles.size());
    makeObstacle(possibleObstacles.get(randomObstacle), attributes, states);
  }

  public double getPositionX() {
    return this.xPosition;
  }

  public double getPositionY() {
    return this.yPosition;
  }
}

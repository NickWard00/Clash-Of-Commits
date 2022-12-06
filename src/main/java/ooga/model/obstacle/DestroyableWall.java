package ooga.model.obstacle;

import java.util.ResourceBundle;

/**
 * This class represents all wall obstacles that have hitpoints and can be destroyed.
 *
 * @author James Qu
 */
public class DestroyableWall extends Wall {
  public static final ResourceBundle HP_VALUES = ResourceBundle.getBundle(
      "ResourceBundles.DestroyableWall");
  private int hp;
  public static final int DEFAULT_HP = Integer.parseInt(HP_VALUES.getString("defaultHP"));
  public static final int REMOVE_HP = Integer.parseInt(HP_VALUES.getString("removeHP"));

  /**
   * Constructor for DestroyableWall, which can be destroyed and is set to on screen and set to default hp value
   * @param x x coordinate of the wall
   * @param y y coordinate of the wall
   */
  public DestroyableWall(Double x, Double y) {
    super(x, y, true, true);
    this.hp = DEFAULT_HP;
  }

  /**
   * Constructor for DestroyableWall, but set to a certain hp value
   * @param x x coordinate of the wall
   * @param y y coordinate of the wall
   * @param hp hp value of the wall
   */
  public DestroyableWall(Double x, Double y, int hp) {
    super(x, y, true, true);
    this.hp = hp;
    checkRemove();
  }

  /**
   * Returns the current hp value of the wall
   */
  public int determineHP() {
    return this.hp;
  }

  /**
   * Returns if the wall is on screen
   * @return boolean representing whether the wall is on screen
   */
  public boolean determineOnScreen() {
    return this.onScreen;
  }

  /**
   * Decreases the hp value of the wall by a certain amount
   * @param hpChange amount of damage to be dealt to the wall
   */
  public void updateHP(int hpChange) {
    this.hp += hpChange;
    checkRemove();
  }

  /**
   * Checks if the wall should be removed from the game
   */
  private void checkRemove() {
    if (this.hp <= REMOVE_HP) {
      this.onScreen = false;
    }
  }
}

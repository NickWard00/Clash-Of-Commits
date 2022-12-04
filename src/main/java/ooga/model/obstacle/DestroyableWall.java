package ooga.model.obstacle;

import java.util.ResourceBundle;

/**
 * This class represents all wall obstacles that have hitpoints and can be destroyed.
 *
 * @author James Qu
 */
public class DestroyableWall extends Wall {
  private static final ResourceBundle HP_VALUES = ResourceBundle.getBundle(
      "ResourceBundles.DestroyableWall");
  private int hp;
  private static final int DEFAULT_HP = Integer.parseInt(HP_VALUES.getString("defaultHP"));
  private static final int REMOVE_HP = Integer.parseInt(HP_VALUES.getString("removeHP"));

  public DestroyableWall(Double x, Double y) {
    super(x, y, true, true);
    this.hp = DEFAULT_HP;
  }

  public DestroyableWall(Double x, Double y, int hp) {
    super(x, y, true, true);
    this.hp = hp;
    checkRemove();
  }

  public int determineHP() {
    return this.hp;
  }

  public boolean determineOnScreen() {
    return this.onScreen;
  }

  public void updateHP(int hpChange) {
    this.hp += hpChange;
    checkRemove();
  }

  private void checkRemove() {
    if (this.hp <= REMOVE_HP) {
      this.onScreen = false;
    }
  }
}

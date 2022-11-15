package ooga.model.obstacle;

/**
 * This class represents all wall obstacles that have hitpoints and can be destroyed.
 *
 * @author James Qu
 */
public class DestroyableWall extends Wall {
  private int hp;

  //TODO: Use config file to load this value
  public static final int DEFAULT_HP = 100;

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


  //TODO: Implement what happens when something hits this obstacle
  public void block() {

  }

  public void updateHP(int hpChange) {
    this.hp += hpChange;
    checkRemove();
  }

  //TODO: Use config file to load this hard-coded value
  private void checkRemove() {
    if (this.hp <= 0) {
      this.onScreen = false;
    }
  }
}

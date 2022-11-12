package ooga.model.obstacle;

/**
 * This class represents all wall obstacles that have hitpoints and can be destroyed.
 *
 * @author James Qu
 */
public class DestroyableWall extends Wall {
  private int hp;

  //TODO: Use config file to load this value
  private static final int DEFAULT_HP = 100;

  public DestroyableWall(Double x, Double y) {
    super(x, y, true);
    this.hp = DEFAULT_HP;
  }

  public DestroyableWall(Double x, Double y, int hp) {
    super(x, y, true);
    this.hp = hp;

//    //TODO: Use config file to load this value
//    if (hp == 0) {
//      remove();
//    }
  }


  //TODO: Implement what happens when something hits this obstacle
  public void block() {

  }

//  //TODO: Implement this method
//  public void remove() {
//
//  }
}

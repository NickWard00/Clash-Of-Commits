package ooga.model.obstacle;

public class DestroyableWall extends Wall {
  private int hp;

  public DestroyableWall(Double x, Double y) {
    super(x, y, true);
  }

  public DestroyableWall(Double x, Double y, int hp) {
    super(x, y, true);
    this.hp = hp;
  }


  //TODO: Implement what happens when something hits this obstacle
  public void block() {

  }
}

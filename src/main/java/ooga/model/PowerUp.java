package ooga.model;

import ooga.model.hero.MainHero;

public class PowerUp {
  private double xPos;
  private double yPos;

  public PowerUp(double x, double y) {
    this.xPos = x;
    this.yPos = y;
  }

  public void upgradeHP(Entity entity, int hp) {
    if (entity.getClass() == MainHero.class) {
      entity.changeHp(hp);
    }
  }
}

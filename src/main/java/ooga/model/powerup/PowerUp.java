package ooga.model.powerup;

import ooga.model.Entity;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.Obstacle;

public class PowerUp extends Obstacle {

  public PowerUp(Double x, Double y) {
    super(x, y, false, true, true);
  }

  public void upgradeHP(Entity entity, int hp) {
    if (entity.getClass() == MainHero.class) {
      entity.changeHp(hp);
    }
  }
  public void remove() {
    this.onScreen = false;
  }
}

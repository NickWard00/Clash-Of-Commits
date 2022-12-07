package ooga.model.obstacle.powerup;

import ooga.model.entities.Entity;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.Obstacle;

public class PowerUp extends Obstacle {
  /**
   * Constructor for PowerUp
   * @param x x coordinate of the power up
   * @param y y coordinate of the power up
   */
  public PowerUp(Double x, Double y) {
    super(x, y, false, true, true);
  }

  /**
   * Applies the power up to the main hero (HP increase)
   * @param entity entity that the power up wants to upgrade
   * @param hp the amount of hp that the power up gives
   */
  public void upgradeHP(Entity entity, int hp) {
    if (entity.getClass() == MainHero.class) {
      entity.changeHp(hp);
    }
  }

  /**
   * Returns if the power up is on screen
   */
  public void remove() {
    this.onScreen = false;
  }
}

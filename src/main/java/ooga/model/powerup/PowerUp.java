package ooga.model.powerup;

import java.util.Map;
import ooga.model.Entity;
import ooga.model.hero.MainHero;

public class PowerUp extends Entity {

  public PowerUp(Map<String, String> attributes) {
    super(attributes);
  }
  //TODO: Figure out how to remove the PowerUp when walked on
  public void upgradeHP(Entity entity, int hp) {
    if (entity.getClass() == MainHero.class) {
      entity.changeHp(hp);
    }
  }
}

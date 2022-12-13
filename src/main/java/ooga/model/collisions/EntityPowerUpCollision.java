package ooga.model.collisions;

import ooga.model.entities.Entity;
import ooga.model.hero.Hero;
import ooga.model.powerup.PowerUp;

import java.util.Map;

/**
 * @author Nicki Lee
 */
public class EntityPowerUpCollision extends Collision {
    public EntityPowerUpCollision(Map<String, Map<?, ?>> viewModelMap) {
        super(viewModelMap);
    }

    public void collide(Entity entity, PowerUp powerup) {
        if (entity.getClass().getSuperclass() == Hero.class) {
            powerup.activatePowerUp(entity);
        }
    }

}

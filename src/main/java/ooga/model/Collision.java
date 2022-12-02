package ooga.model;

import ooga.model.attack.Attack;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.*;
import ooga.model.powerup.PowerUp;

public class Collision {

    public Collision(Attack attack, Entity entity) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            attack.deactivateAttack();
        }
    }

    public Collision(Attack attack, Obstacle obstacle) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getClass() == DestroyableWall.class) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        attack.deactivateAttack();
    }

    public Collision(Entity entity, Obstacle obstacle) {
        if (entity.getClass() == MainHero.class && (obstacle.getClass() == ImmovableWall.class || obstacle.getClass() == DestroyableWall.class)) {
            ((Wall) obstacle).block(entity);
//            LogManager.getLogger(Collision.class).info("Entity Obstacle collision occurred")
        } else if (obstacle.getClass() != Feature.class) {
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    public Collision(Entity entity1, Entity entity2) {
        if (entity1.getClass() == MainHero.class && entity2.getClass() == PowerUp.class) {
            ((PowerUp) entity2).upgradeHP(entity1, 1);
            System.out.println("collision");
        }
        else if (entity1.getClass() == PowerUp.class && entity2.getClass() == MainHero.class) {
            ((PowerUp) entity1).upgradeHP(entity2, 1);
            System.out.println("collision");
        }

    }


}

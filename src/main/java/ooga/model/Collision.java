package ooga.model;

import ooga.model.attack.Attack;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.*;
import ooga.model.powerup.PowerUp;
import ooga.view.EntityView;

import java.util.List;
import java.util.Map;

public class Collision {

    public Collision(Attack attack, Entity entity, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity() != entity) {
            System.out.println("Attack and enemy collision!");
            entity.changeHp(attack.getDamage());
            attack.deactivateAttack();
        }
    }

    public Collision(Attack attack, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getClass() == DestroyableWall.class) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        attack.deactivateAttack();
    }

    public Collision(Entity entity, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (entity.getClass() == MainHero.class && (obstacle.getClass() == ImmovableWall.class || obstacle.getClass() == DestroyableWall.class)) {
            ((Wall) obstacle).block(entity);
            List<Double> knockBackCoordinate = entity.knockBack(2);
            ((EntityView) viewModelMap.get("viewEntities").get("Hero1")).setCoordinate(knockBackCoordinate);
//            LogManager.getLogger(Collision.class).info("Entity Obstacle collision occurred")
        } else if (obstacle.getClass() != Feature.class) {
            String myName = "";
            for (Object name : viewModelMap.get("modelEntities").keySet()) {
                if (viewModelMap.get("modelEntities").get(name) == entity) {
                    myName = (String) name;
                }
            }
            EntityView myEntityView = (EntityView) viewModelMap.get("viewEntities").get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(2);
            myEntityView.setCoordinate(knockBackCoordinate);
            myEntityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    public Collision(Entity entity1, Entity entity2, Map<String, Map<?,?>> viewModelMap) {
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

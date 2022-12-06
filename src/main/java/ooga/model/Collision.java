package ooga.model;

import java.util.ResourceBundle;
import ooga.model.attack.Attack;
import ooga.model.entities.Entity;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import ooga.model.obstacle.Wall;
import ooga.model.obstacle.powerup.PowerUp;
import ooga.view.EntityView;

import java.util.List;
import java.util.Map;

public class Collision {
    private static final ResourceBundle COLLISION_VALUES = ResourceBundle.getBundle(
        "ResourceBundles.Collision");
    private static final int POWERUP_HP_ADDER = Integer.parseInt(COLLISION_VALUES.getString("powerUpHpAdder"));

    /**
     * Collsion between attack and entity
     * @param attack
     * @param entity
     * @param viewModelMap
     */
    public Collision(Attack attack, Entity entity, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            String myName = entity.getMyAttributes().get("Name");
            EntityView myEntityView = (EntityView) viewModelMap.get("viewEntities").get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(-attack.getDamage() * 5, attack.getDirection());
            myEntityView.setX(knockBackCoordinate.get(0));
            myEntityView.setY(knockBackCoordinate.get(1));
            attack.deactivateAttack();
        }
    }

    /**
     * Collision between attack and obstacle
     * @param attack
     * @param obstacle
     * @param viewModelMap
     */
    public Collision(Attack attack, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getClass() == DestroyableWall.class) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        attack.deactivateAttack();
    }

    //TODO: Refactor to get rid of constant Strings and add Logging (and also get rid of duplicated lines of code)

    /**
     * Collision between entity and obstacle
     * @param entity
     * @param obstacle
     * @param viewModelMap
     */
    public Collision(Entity entity, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (entity.getClass() == MainHero.class && obstacle.getBlocker()) {
            ((Wall) obstacle).block(entity);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            EntityView heroEntityView = ((EntityView) viewModelMap.get("viewEntities").get(entity.getMyAttributes().get("Name")));
            heroEntityView.setX(knockBackCoordinate.get(0));
            heroEntityView.setY(knockBackCoordinate.get(1));
        } else if (entity.getClass() == MainHero.class && obstacle.getClass() == PowerUp.class) {
            ((PowerUp) obstacle).upgradeHP(entity, POWERUP_HP_ADDER);
            ((PowerUp) obstacle).remove();
        } else if (obstacle.getBlocker()) {
            String myName = entity.getMyAttributes().get("Name");
            EntityView myEntityView = (EntityView) viewModelMap.get("viewEntities").get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            myEntityView.setX(knockBackCoordinate.get(0));
            myEntityView.setY(knockBackCoordinate.get(1));
            myEntityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }
}

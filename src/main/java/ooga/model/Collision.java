package ooga.model;

import java.util.ResourceBundle;
import ooga.model.attack.Attack;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.*;
import ooga.model.obstacle.powerup.PowerUp;
import ooga.view.EntityView;

import java.util.List;
import java.util.Map;

public class Collision {
    public static final ResourceBundle COLLISION_VALUES = ResourceBundle.getBundle(
        "ResourceBundles.Collision");
    public static final int POWERUP_HP_ADDER = Integer.parseInt(COLLISION_VALUES.getString("powerUpHpAdder"));

    public Collision(Attack attack, Entity entity, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            String myName = getEntityName(entity, (Map<String, Entity>) viewModelMap.get("modelEntities"));
            EntityView myEntityView = (EntityView) viewModelMap.get("viewEntities").get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(-attack.getDamage() * 5, attack.getDirection());
            myEntityView.setCoordinate(knockBackCoordinate);
            attack.deactivateAttack();
        }
    }

    public Collision(Attack attack, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getDestroyable()) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        attack.deactivateAttack();
    }

    //TODO: Refactor to get rid of constant Strings and add Logging (and also get rid of duplicated lines of code)
    public Collision(Entity entity, Obstacle obstacle, Map<String, Map<?,?>> viewModelMap) {
        if (entity.getClass() == MainHero.class && obstacle.getBlocker()) {
            ((Wall) obstacle).block(entity);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            ((EntityView) viewModelMap.get("viewEntities").get("Hero1")).setCoordinate(knockBackCoordinate);
//            LogManager.getLogger(Collision.class).info("Entity Obstacle collision occurred")
        } else if (entity.getClass() == MainHero.class && obstacle.getClass() == PowerUp.class) {
            ((PowerUp) obstacle).upgradeHP(entity, POWERUP_HP_ADDER);
            ((PowerUp) obstacle).remove();
        } else if (obstacle.getBlocker()) {
            String myName = getEntityName(entity, (Map<String, Entity>) viewModelMap.get("modelEntities"));
            EntityView myEntityView = (EntityView) viewModelMap.get("viewEntities").get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            myEntityView.setCoordinate(knockBackCoordinate);
            myEntityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    private String getEntityName(Entity entity, Map<String, Entity> modelEntities) {
        String myName = "";
        for (Object name : modelEntities.keySet()) {
            if (modelEntities.get(name) == entity) {
                myName = (String) name;
            }
        }
        return myName;
    }

}

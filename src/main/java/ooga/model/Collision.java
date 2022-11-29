package ooga.model;

import ooga.controller.Controller;
import ooga.model.attack.Attack;
import ooga.model.hero.Hero;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.*;
import ooga.model.state.MovementState;
import ooga.view.EntityView;

import java.util.List;

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
            List<Double> knockBackCoordinate = entity.knockBack();
            Controller.getViewEntities().get("Hero1").setCoordinate(knockBackCoordinate);
        } else if (obstacle.getClass() != Feature.class) {
            String myName = "";
            for (String name : Controller.getModelEntities().keySet()) {
                if (Controller.getModelEntities().get(name) == entity) {
                    myName = name;
                }
            }
            EntityView myEntityView = Controller.getViewEntities().get(myName);
            List<Double> knockBackCoordinate = entity.knockBack();
            myEntityView.setCoordinate(knockBackCoordinate);
            myEntityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    public Collision(Entity entity, PowerUp powerUp) {
        powerUp.upgradeHP(entity, 1);
    }

}

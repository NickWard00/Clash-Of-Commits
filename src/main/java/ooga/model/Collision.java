package ooga.model;

import ooga.model.attack.Attack;
import ooga.model.hero.Hero;
import ooga.model.obstacle.Obstacle;
import ooga.model.state.MovementState;

public class Collision {

    // TODO: currently, if parameters are passed in in the reverse order (ex. entity then attack as opposed to attack then entity) it doesn't recognize a constructor


    public Collision(Attack attack, Entity entity) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            deactivateAttack(attack);
        }
    }

    public Collision(Attack attack, Obstacle obstacle) {
        deactivateAttack(attack);
        // check if wall is destroyable, if it is and the attack came from the hero, and destroy the wall
    }

    public Collision(Entity entity, Obstacle obstacle) {
        if (entity.getClass().getSuperclass() == Hero.class) {
            // TODO: this may introduce a bug where if the hero is directly next to a wall, they wont be able to move perpendicular to the wall
            entity.changeMovement(MovementState.STATIONARY);
        } else {
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    private void deactivateAttack(Attack attack) {
        // remove the attack from list of attacks or something like that
    }

}

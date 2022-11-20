package ooga.model;

import ooga.model.attack.Attack;
import ooga.model.hero.Hero;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import ooga.model.obstacle.Wall;
import ooga.model.state.MovementState;

public class Collision {

    public Collision(Attack attack, Entity entity) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            deactivateAttack(attack);
        }
    }

    public Collision(Attack attack, Obstacle obstacle) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getClass() == DestroyableWall.class) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        deactivateAttack(attack);
    }

    public Collision(Entity entity, Obstacle obstacle) {
        if (entity.getClass() == MainHero.class && obstacle.getClass() == Wall.class) {
            // TODO: this may introduce a bug where if the hero is directly next to a wall, they wont be able to move perpendicular to the wall
//            entity.changeMovement(MovementState.STATIONARY);
            ((Wall) obstacle).block(entity);
        } else if (obstacle.getClass() == Wall.class) {
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

    private void deactivateAttack(Attack attack) {
        // remove the attack from list of attacks or something like that
    }

}

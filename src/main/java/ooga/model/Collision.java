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
        if (entity.getClass() == MainHero.class && obstacle.getClass() == Wall.class) {
            // TODO: this may introduce a bug where if the hero is directly next to a wall, they wont be able to move perpendicular to the wall
//            entity.changeMovement(MovementState.STATIONARY);
            ((Wall) obstacle).block(entity);
            int knockback = 5; // this is a magic value oops
            entity.setX(myX + knockback * entity.getMyDirection().oppositeDirection().getVelocity().get(0));
            entity.setY(myY + knockback * entity.getMyDirection().oppositeDirection().getVelocity().get(1));
            System.out.println("COLLISION!");
        } else if (obstacle.getClass() == Wall.class) {
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

}

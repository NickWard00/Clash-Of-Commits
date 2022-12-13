package ooga.model.collisions;

import ooga.model.attack.Attack;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import java.util.Map;

/**
 * @author Nicki Lee
 */
public class AttackObstacleCollision extends Collision {

    public AttackObstacleCollision(Map<String, Map<?, ?>> viewModelMap) {
        super(viewModelMap);
    }

    /**
     * Collision between attack and obstacle
     * @param attack
     * @param obstacle
     */
    public void collide(Attack attack, Obstacle obstacle) {
        if (attack.getMyEntity().getClass() == MainHero.class && obstacle.getDestroyable()) {
            ((DestroyableWall) obstacle).updateHP(attack.getDamage());
        }
        attack.deactivateAttack();
    }

}

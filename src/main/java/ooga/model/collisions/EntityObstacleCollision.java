package ooga.model.collisions;

import ooga.model.entities.Entity;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.Obstacle;
import ooga.model.obstacle.Wall;
import ooga.view.EntityView;

import java.util.List;
import java.util.Map;

public class EntityObstacleCollision extends Collision {

    private Map<String, EntityView> viewEntities;

    public EntityObstacleCollision(Map<String, Map<?, ?>> viewModelMap) {
        super(viewModelMap);
        viewEntities = (Map<String, EntityView>) viewModelMap.get("viewEntities");
    }

    /**
     * Collision between entity and obstacle
     * @param entity
     * @param obstacle
     */
    public void collide(Entity entity, Obstacle obstacle) {
        if (entity.getClass() == MainHero.class && obstacle.getBlocker()) {
            ((Wall) obstacle).block(entity);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            EntityView heroEntityView = viewEntities.get(entity.getMyAttributes().get("Name"));
            heroEntityView.setX(knockBackCoordinate.get(0));
            heroEntityView.setY(knockBackCoordinate.get(1));
        } else if (obstacle.getBlocker()) {
            String myName = entity.getMyAttributes().get("Name");
            EntityView myEntityView = viewEntities.get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(2, entity.getMyDirection().oppositeDirection());
            myEntityView.setX(knockBackCoordinate.get(0));
            myEntityView.setY(knockBackCoordinate.get(1));
            myEntityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            entity.changeDirection(entity.getMyDirection().oppositeDirection());
        }
    }

}

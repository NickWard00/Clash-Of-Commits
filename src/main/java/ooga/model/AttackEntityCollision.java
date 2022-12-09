package ooga.model;

import ooga.model.attack.Attack;
import ooga.model.entities.Entity;
import ooga.view.EntityView;

import java.util.List;
import java.util.Map;

public class AttackEntityCollision extends Collision {

    private Map<String, EntityView> viewEntities;

    public AttackEntityCollision(Map<String, Map<?, ?>> viewModelMap) {
        super(viewModelMap);
        viewEntities = (Map<String, EntityView>) viewModelMap.get("viewEntities");
    }

    /**
     * Collsion between attack and entity
     * @param attack the attack
     * @param entity the entity
     */
    public void collide(Attack attack, Entity entity) {
        if (attack.getMyEntity() != entity) {
            entity.changeHp(attack.getDamage());
            String myName = entity.getMyAttributes().get("Name");
            EntityView myEntityView = viewEntities.get(myName);
            List<Double> knockBackCoordinate = entity.knockBack(-attack.getDamage() * 5, attack.getDirection());
            myEntityView.setX(knockBackCoordinate.get(0));
            myEntityView.setY(knockBackCoordinate.get(1));
            attack.deactivateAttack();
        }
    }
}

package ooga.model.attack;

import ooga.model.hitBox.AttackHitBox;
import ooga.model.state.DirectionState;
import ooga.model.Entity;

import java.util.Map;

public class ShortRange extends Attack {

    /**
     * Constructor for the ShortRange attack
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
     */
    public ShortRange(Entity entity, Map<String, Double> attributes) {
        super(entity, attributes);
    }

    public void activateAttack() {
        System.out.println("SHORT RANGE ATTACK");
    }

}

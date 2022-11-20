package ooga.model.attack;

import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.hitBox.AttackHitBox;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LongRange extends Attack {
    private static double coolDown;

    /**
     * Constructor for the LongRange attack
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
    */
    public LongRange(Entity entity, Map<String, Double> attributes) {
        super(entity, attributes);
        coolDown = attributes.getOrDefault("CoolDown", 1.0);
    }

}

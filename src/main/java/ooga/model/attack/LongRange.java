package ooga.model.attack;

import ooga.model.entities.Entity;
import java.util.Map;

/**
 * @author Nicki Lee
 */
public class LongRange extends Attack {

    /**
     * Constructor for the LongRange attack
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
    */
    public LongRange(Entity entity, Map<String, Double> attributes) {
        super(entity, attributes);
    }
}

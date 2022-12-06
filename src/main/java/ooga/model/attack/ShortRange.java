package ooga.model.attack;

import ooga.model.entities.Entity;

import java.util.Map;

public class ShortRange extends Attack {

    private double coolDown;

    /**
     * Constructor for the ShortRange attack
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
     */
    public ShortRange(Entity entity, Map<String, Double> attributes) {
        super(entity, attributes);
        coolDown = attributes.getOrDefault("CoolDown", 1.0);
    }

    public double getCoolDown() { return coolDown; }
}

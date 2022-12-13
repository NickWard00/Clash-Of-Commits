package ooga.model.hero;

import ooga.model.entities.Entity;

import java.util.Map;

/**
 * @author Nicki Lee, Nick Ward
 */
public abstract class Hero extends Entity {
    /**
     * Constructor for the Hero superclass
     * @param attributes
     */
    public Hero(Map<String, String> attributes) {
        super(attributes);
    }
}

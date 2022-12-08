package ooga.model.enemy;

import java.util.Map;
import ooga.model.entities.Entity;

public abstract class Enemy extends Entity {
    /**
     * Constructor for the Enemy superclass that also extends the Entity class
     * @param attributes a string to string map of the enemy's attributes (speed, hp, etc.)
     * */
    public Enemy(Map<String, String> attributes) {
        super(attributes);
    }
}

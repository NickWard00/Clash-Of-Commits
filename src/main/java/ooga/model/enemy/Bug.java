package ooga.model.enemy;

import java.util.Map;

/**
 * @author Nicki Lee
 */
public class Bug extends Enemy {
    /**
     * Constructor for the Bug subclass which extends Enemy
     * @param attributes a string to string map of the bug's attributes (speed, hp, etc.)
     */
    public Bug(Map<String, String> attributes) {
        super(attributes);
    }
}

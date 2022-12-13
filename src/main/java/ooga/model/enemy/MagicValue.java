package ooga.model.enemy;

import java.util.Map;

/**
 * @author Nicki Lee
 */
public class MagicValue extends Enemy {
    /**
     * Constructor for the MagicValue subclass which extends Enemy
     * @param attributes a string to string map of the MagicValue's attributes (speed, hp, etc.)
     * */
    public MagicValue(Map<String, String> attributes) {
        super(attributes);
    }
}

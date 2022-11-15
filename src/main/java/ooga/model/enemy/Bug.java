package ooga.model.enemy;

import java.util.List;
import java.util.Map;

public class Bug extends Enemy {

    /**
     * Constructor for the Bug subclass which extends Enemy
     * @param attributes a string to string map of the bug's attributes (speed, hp, etc.)
     * */
    public Bug(Map<String, String> attributes) {
        super(attributes);
    }
}

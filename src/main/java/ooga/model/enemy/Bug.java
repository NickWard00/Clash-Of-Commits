package ooga.model.enemy;

import java.util.Map;

public class Bug extends Enemy {

    private static int bug_max_hp;
    private static int bug_speed;
    private static int bug_size;
    private static String bug_attackType;

    /**
     * Constructor for the Bug subclass
     * */
    public Bug(Map<String, String> attributes, Map<Integer, String> states) {
        super(attributes, states);
    }
}

package ooga.model.hero;

import java.util.List;
import java.util.Map;

public class MainHero extends Hero {
    private static final int DEFAULT_ATTACK_STRENGTH = 1;
    private static final String DEFAULT_MOVEMENT_TYPE = "random"; // we could have movement types like stationary, chase, random, etc?
    private static final int DEFAULT_HP = 2;
    private static int attackStrength = DEFAULT_ATTACK_STRENGTH;
    private static String movementType = DEFAULT_MOVEMENT_TYPE;

    public MainHero(Map<String, String> attributes) {
        super(attributes);
    }
}

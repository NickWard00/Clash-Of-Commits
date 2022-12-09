package ooga.model;

import java.util.ResourceBundle;
import java.util.Map;

public class Collision {
    private static final ResourceBundle COLLISION_VALUES = ResourceBundle.getBundle(
        "ResourceBundles.Collision");
    private static final int POWERUP_HP_ADDER = Integer.parseInt(COLLISION_VALUES.getString("powerUpHpAdder"));

    private Map<String, Map<?,?>> viewModelMap;

    public Collision(Map<String, Map<?,?>> viewModelMap) {
        this.viewModelMap = viewModelMap;
    }


}

package ooga.model.enemy;

import java.util.Map;

public class MagicValue extends Enemy {

    private static int magicValue_max_hp;
    private static int magicValue_speed;
    private static int magicValue_size;
    private static String magicValue_attackType;

    /**
     * Constructor for the MagicValue subclass
     * */
    public MagicValue(Map<String, String> attributes, Map<Integer, String> states) {
        super(attributes, states);
    }

    /**
     * Constructor for setting the MagicValue subclass' static values
     * Should only be called by the file parser that reads subclass values from the subclass sim files
     * NOT for creating new instances of MagicValue
     * */
    public MagicValue(int hp, int speed, int size, String attack) {
        magicValue_max_hp = hp;
        magicValue_speed = speed;
        magicValue_size = size;
        magicValue_attackType = attack;
    }

}

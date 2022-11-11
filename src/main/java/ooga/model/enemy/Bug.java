package ooga.model.enemy;

public class Bug extends Enemy {

    private static int bug_max_hp;
    private static int bug_speed;
    private static int bug_size;
    private static String bug_attackType;

    /**
     * Constructor for the Bug subclass
     * */
    public Bug(Double xPos, Double yPos) {
        super(xPos, yPos, bug_max_hp, bug_speed, bug_size, bug_attackType);
    }

    /**
     * Constructor for setting the Bug subclass' static values
     * Should only be called by the file parser that reads subclass values from the subclass sim files
     * NOT for creating new instances of Bug
     * */
    public Bug(int hp, int speed, int size, String attack) {
        bug_max_hp = hp;
        bug_speed = speed;
        bug_size = size;
        bug_attackType = attack;
    }
}

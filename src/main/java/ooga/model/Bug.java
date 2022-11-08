package ooga.model;

public class Bug extends Enemy {
    private static final int DEFAULT_ATTACK_STRENGTH = 1;
    private static final String DEFAULT_MOVEMENT_TYPE = "random"; // we could have movement types like stationary, chase, random, etc?
    private static final int DEFAULT_HP = 2;
    private static int attackStrength = DEFAULT_ATTACK_STRENGTH;
    private static String movementType = DEFAULT_MOVEMENT_TYPE;

    public Bug(Double xPos, Double yPos) {
        super(xPos, yPos, DEFAULT_HP);
    }

    @Override
    public void attack() {
        System.out.println("Bug attack");
    }

    @Override
    public void move() {}
}

package ooga.model.attack;

import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.hitBox.AttackHitBox;

import java.util.Arrays;
import java.util.List;

public class LongRange extends Attack {

    // TODO: work on reading default values from a file
    public static final int DEFAULT_RANGE = 20;
    private int range = DEFAULT_RANGE;
    public static final double DEFAULT_SPEED = 20;
    private double speed = DEFAULT_SPEED;
    private AttackHitBox myHitBox;
    private DirectionState myDirection;
    private Double xPos;
    private Double yPos;

    /**
     * Constructor for the LongRange attack
     * @param entity the entity that initiated this attack
     * */
    public LongRange(Entity entity) {
        super(entity);
        try {
            // TODO: establish whether entity coordinates are centered or upper left aligned and adjust this accordingly
            // calculates the x and y positions based on the positions its entity plus an offset based on direction of attack and size of the entity
            myDirection = DirectionState.valueOf(entity.getMyAttributes().get("Direction"));
            this.xPos = entity.coordinates().get(0) + (myDirection.getVelocity().get(0) * Integer.parseInt(entity.getMyAttributes().get("Size")));
            this.yPos = entity.coordinates().get(1) + (myDirection.getVelocity().get(1) * Integer.parseInt(entity.getMyAttributes().get("Size")));
            this.myHitBox = new AttackHitBox(this, xPos, yPos, range, range);
            this.myDirection = DirectionState.valueOf(entity.getStateStrings().get(0));
        }
        catch (NumberFormatException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void activateAttack() {
        System.out.println("LONG RANGE ATTACK");
    }

    /**
     * @param elapsedTime indicates how much time has passed since the last step
     * updates the X and Y positions of the attack and calls the equivalent move method for this attack's hitbox
     * */
    public List<Double> move(double elapsedTime) {
        xPos += myDirection.getVelocity().get(0) * speed * elapsedTime;
        yPos += myDirection.getVelocity().get(1) * speed * elapsedTime;
        myHitBox.move(xPos, yPos);
        return Arrays.asList(xPos, yPos);
    }

}

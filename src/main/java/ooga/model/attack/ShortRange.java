package ooga.model.attack;

import ooga.model.hitBox.AttackHitBox;
import ooga.model.state.DirectionState;
import ooga.model.Entity;

import java.util.Map;

public class ShortRange extends Attack {

    // TODO: work on reading default values from a file
    public static final int DEFAULT_RANGE = 20;
    private int range = DEFAULT_RANGE;
    private AttackHitBox myHitBox;
    private DirectionState myDirection;
    private Double xPos;
    private Double yPos;

    /**
     * Constructor for the ShortRange attack
     * @param entity the entity that initiated this attack
     * */
    public ShortRange(Entity entity) {
        super(entity);
        try {
            // TODO: establish whether entity coordinates are centered or upper left aligned and adjust this accordingly
            // calculates the x and y positions based on the positions its entity plus an offset based on direction of attack and size of the entity
            myDirection = DirectionState.valueOf(entity.getMyAttributes().get("Direction"));
            this.xPos = entity.coordinates().get(0) + (myDirection.getVelocity().get(0) * Integer.parseInt(entity.getMyAttributes().get("Size")));
            this.yPos = entity.coordinates().get(1) + (myDirection.getVelocity().get(1) * Integer.parseInt(entity.getMyAttributes().get("Size")));
            this.myHitBox = new AttackHitBox(this, xPos, yPos, range, range);
        }
        catch (NumberFormatException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public void activateAttack() {
        System.out.println("SHORT RANGE ATTACK");
    }

}

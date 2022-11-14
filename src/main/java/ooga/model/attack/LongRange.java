package ooga.model.attack;

import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.HitBox.AttackHitBox;

public class LongRange extends Attack {

    public static final int DEFAULT_RANGE = 20;
    private int range = DEFAULT_RANGE;
    private AttackHitBox myHitBox;
    private DirectionState myDirection;
    private Double xPos;
    private Double yPos;

    public LongRange(Entity entity) {
        super(entity);
        this.xPos = entity.coordinates().get(0);
        this.yPos = entity.coordinates().get(1);
        this.myHitBox = new AttackHitBox(this, xPos, yPos, range, range);
        this.myDirection = DirectionState.valueOf(entity.getStateStrings().get(0));
    }

    public void activateAttack(Entity entity) {
        System.out.println("ATTACK 1");
    }

    public void move() {}

}

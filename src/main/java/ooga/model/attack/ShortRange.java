package ooga.model.attack;

import ooga.model.HitBox.AttackHitBox;
import ooga.model.state.DirectionState;
import ooga.model.Entity;

public class ShortRange extends Attack {

    public static final int DEFAULT_RANGE = 20;
    private int range = DEFAULT_RANGE;
    private AttackHitBox myHitBox;
    private DirectionState myDirection;
    private Double xPos;
    private Double yPos;

    public ShortRange(Entity entity) {
        super(entity);
        this.xPos = entity.coordinates().get(0);
        this.yPos = entity.coordinates().get(1);
        this.myHitBox = new AttackHitBox(this, xPos, yPos, range, range);
    }

    public void activateAttack(Entity entity) {
        System.out.println("ATTACK 2");
    }

}

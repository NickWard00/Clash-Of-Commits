package ooga.model.hitBox;

import ooga.model.attack.Attack;

public class AttackHitBox extends HitBox {
    /**
     * @param startX
     * @param startY
     * @param width
     * @param height
     */
    public AttackHitBox(Attack attack, double startX, double startY, int width, int height) {
        super(attack.getMyEntity(), startX, startY, width, height);
    }
}

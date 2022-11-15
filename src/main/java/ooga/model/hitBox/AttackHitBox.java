package ooga.model.hitBox;

import ooga.model.attack.Attack;

public class AttackHitBox extends HitBox {
    /**
     * Constructor for the AttackHitBox class that extends the HitBox superclass
     * @param attack the entity that this hitbox is associated with
     * @param startX X coordinate of the upper left corner of the hitbox
     * @param startY Y coordinate of the upper left corner of the hitbox
     * @param width width of the hitbox
     * @param height height of the hitbox
     * */
    public AttackHitBox(Attack attack, double startX, double startY, int width, int height) {
        super(attack.getMyEntity(), startX, startY, width, height);
    }
}

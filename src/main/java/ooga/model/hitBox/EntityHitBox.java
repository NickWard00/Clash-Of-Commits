package ooga.model.hitBox;

import ooga.model.entities.Entity;

public class EntityHitBox extends HitBox {

    /**
     * Constructor for the EntityHitBox class that extends HitBox
     * @param entity the entity that this hitbox is associated with
     * @param startX X coordinate of the upper left corner of the hitbox
     * @param startY Y coordinate of the upper left corner of the hitbox
     * @param width width of the hitbox
     * @param height height of the hitbox
     * */
    public EntityHitBox(Entity entity, double startX, double startY, int width, int height) {
        super(entity, startX, startY, width, height);
    }
}

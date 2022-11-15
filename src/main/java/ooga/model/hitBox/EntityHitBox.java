package ooga.model.hitBox;

import ooga.model.Entity;

public class EntityHitBox extends HitBox {

    /**
     * @param startX
     * @param startY
     * @param width
     * @param height
     */
    public EntityHitBox(Entity entity, double startX, double startY, int width, int height) {
        super(entity, startX, startY, width, height);
    }
}

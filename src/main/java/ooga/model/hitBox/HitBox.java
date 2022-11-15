package ooga.model.hitBox;

import ooga.model.Entity;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class HitBox extends Rectangle2D.Double {

    private Entity myEntity;
    private double xPos;
    private double yPos;

    /**
     * Constructor for the HitBox superclass
     * @param entity the entity that this hitbox is associated with
     * @param startX X coordinate of the upper left corner of the hitbox
     * @param startY Y coordinate of the upper left corner of the hitbox
     * @param width width of the hitbox
     * @param height height of the hitbox
     * */
    public HitBox(Entity entity, double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        this.myEntity = entity;
        this.xPos = startX;
        this.yPos = startY;
    }

    public void move(double newX, double newY) {
        xPos = newX;
        yPos = newY;
    }

    public List<java.lang.Double> coordinates() {
        List<java.lang.Double> res = Arrays.asList(xPos, yPos);
        return res;
    }

}

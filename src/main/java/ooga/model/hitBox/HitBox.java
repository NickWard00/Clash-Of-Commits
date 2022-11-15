package ooga.model.hitBox;

import ooga.model.Entity;

import java.awt.geom.Rectangle2D;

public abstract class HitBox extends Rectangle2D.Double {

    private Entity myEntity;
    private double xPos;
    private double yPos;

    /**
    public HitBox(Entity entity, int width, int height, int direction) {
        super((int) Math.rint(entity.coordinates().get(0)), (int) Math.rint(entity.coordinates().get(1)), width, height);
        this.myEntity = entity;
    } */


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

}

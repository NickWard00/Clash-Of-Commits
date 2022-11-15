package ooga.model;

import ooga.model.hitBox.EntityHitBox;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Entity {
    private double xPos;
    private double yPos;
    private int max_hp;
    private double speed;
    private int size;
    private DirectionState myDirection;
    private MovementState myMovement;
    private Map<String, String> myAttributes;
    private String attackType;
    private int hp;
    private EntityHitBox myHitBox;

    public Entity(Map<String, String> attributes) {
        try {
            this.xPos = Double.parseDouble(attributes.get("XPosition"));
            this.yPos = Double.parseDouble(attributes.get("YPosition"));
            this.max_hp = Integer.parseInt(attributes.get("HP"));
            this.hp = max_hp;
            this.speed = Double.parseDouble(attributes.get("Speed"));
            this.size = Integer.parseInt(attributes.get("Size"));
            this.attackType = attributes.get("Attack");
            this.myDirection = DirectionState.valueOf(attributes.getOrDefault("Direction", "SOUTH"));
            this.myMovement = MovementState.valueOf(attributes.getOrDefault("Movement", "STATIONARY"));
            this.myAttributes = attributes;
            this.myHitBox = new EntityHitBox(this, xPos, yPos, size, size);
        }
        catch (NullPointerException | ClassCastException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }


    protected void changeHp(int diff) {
        hp += diff;
    }

    public String getAttackType() {
        return attackType;
    }

    public List<Double> coordinates() { return Arrays.asList(xPos, yPos); }

    public void changeDirection(DirectionState newDirection) {
        myDirection = newDirection;
    }
    public void changeMovement(MovementState newMovement) {
        myMovement = newMovement;
    }

    public List<String> getStateStrings() {
        return Arrays.asList(myDirection.getDirection(), myMovement.getMovement());
    }

}

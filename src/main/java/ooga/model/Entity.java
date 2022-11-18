package ooga.model;

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

    public Entity(Map<String, String> attributes) {
        try {
            this.myAttributes = attributes;
            this.xPos = Double.parseDouble(attributes.get("XPosition"));
            this.yPos = Double.parseDouble(attributes.get("YPosition"));
            this.max_hp = Integer.parseInt(attributes.get("HP"));
            this.hp = max_hp;
            this.speed = Double.parseDouble(attributes.get("Speed"));
            this.size = Integer.parseInt(attributes.get("Size"));
            this.attackType = attributes.get("Attack");
            this.myDirection = DirectionState.valueOf(attributes.getOrDefault("Direction", "SOUTH"));
            this.myMovement = MovementState.valueOf(attributes.getOrDefault("Movement", "STATIONARY"));
        }
        catch (NullPointerException | ClassCastException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to update this entity's x and y positions based on the elapsed time since the previous step
     * @param elapsedTime Time passed since the previous step
     * */
    public List<Double> move(double elapsedTime) {
        xPos += myDirection.getVelocity().get(0) * myMovement.getSpeedConverter() * speed * elapsedTime;
        yPos += myDirection.getVelocity().get(1) * myMovement.getSpeedConverter() * speed * elapsedTime;
        return Arrays.asList(xPos, yPos);
    }

    public Map<String, String> getMyAttributes() {
        return myAttributes;
    } // TODO: next week, make a record for entity

    protected void changeHp(int diff) {
        hp += diff;
    }
    public String getAttackType() {
        return attackType;
    }

    public List<Double> coordinates() {
        return Arrays.asList(xPos, yPos);
    }

    public void changeDirection(DirectionState newDirection) {
        myDirection = newDirection;
    }
    public void changeMovement(MovementState newMovement) {
        myMovement = newMovement;
    }

    public List<String> getStateStrings() {
        return Arrays.asList(myDirection.getDirection(), myMovement.getMovement());
    }

    protected int getHp() {
        return hp;
    } // only using for testing purposes before I implement records next week

    public DirectionState getMyDirection() { return myDirection; }


}

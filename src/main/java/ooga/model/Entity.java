package ooga.model;

import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class Entity {
    private double xPos;
    private double yPos;
    private int max_hp;
    private int hp;

    private double speed;
    private int size;
    private DirectionState myDirection;
    private MovementState myMovement;
    private Map<String, String> myAttributes;
    private String attackType;
    private double attackCoolDown;
    private double timeUntilAttack;

    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");


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
            Class c = Class.forName(attackBundle.getString(attackType));
            Method m = c.getDeclaredMethod("getCoolDown");
            this.attackCoolDown = (double) m.invoke(this);
            this.timeUntilAttack = attackCoolDown;
        }
        catch (NullPointerException | ClassCastException | IllegalArgumentException | ClassNotFoundException |
               NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Method to update this entity's x and y positions based on the elapsed time since the previous step
     * @param elapsedTime Time passed since the previous step
     * */
    public List<Double> move(double elapsedTime) {
        this.xPos += myDirection.getVelocity().get(0) * myMovement.getSpeedConverter() * speed * elapsedTime;
        this.yPos += myDirection.getVelocity().get(1) * myMovement.getSpeedConverter() * speed * elapsedTime;
        timeUntilAttack -= elapsedTime;
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
    }

    public DirectionState getMyDirection() { return myDirection; }

    public double getTimeUntilAttack() { return timeUntilAttack; }

    public void resetTimeUntilAttack() {
        timeUntilAttack = attackCoolDown;
    }

    public List<Double> knockBack() {
        xPos += 2 * myDirection.oppositeDirection().getVelocity().get(0);
        yPos += 2 * myDirection.oppositeDirection().getVelocity().get(1);
        return Arrays.asList(xPos, yPos);
    }

}

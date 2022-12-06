package ooga.model.entities;

import ooga.controller.AttackParser;
import ooga.model.attack.Attack;
import ooga.model.enemy.Enemy;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

    /**
     * Constructor for Entity
     * @param attributes - map of attributes
     */
    public Entity(Map<String, String> attributes) {
        this.myAttributes = attributes;
        this.myDirection = DirectionState.valueOf(attributes.getOrDefault("Direction", "SOUTH"));
        this.myMovement = MovementState.valueOf(attributes.getOrDefault("Movement", "STATIONARY"));
        this.xPos = Double.parseDouble(attributes.get("XPosition"));
        this.yPos = Double.parseDouble(attributes.get("YPosition"));
        this.max_hp = Integer.parseInt(attributes.get("HP"));
        this.hp = max_hp;
        this.speed = Double.parseDouble(attributes.get("Speed"));
        this.size = Integer.parseInt(attributes.get("Size"));
        this.attackType = attributes.get("Attack");
        this.attackCoolDown = Double.parseDouble(attributes.get("CoolDown"));
        this.timeUntilAttack = attackCoolDown;
    }

    /**
     * Method to update this entity's x and y positions based on the elapsed time since the previous step
     * @param elapsedTime Time passed since the previous step
     * */
    public List<Double> move(double elapsedTime) {
        this.xPos += myDirection.getVelocity().get(0) * myMovement.getSpeedConverter() * speed * elapsedTime;
        this.yPos += myDirection.getVelocity().get(1) * myMovement.getSpeedConverter() * speed * elapsedTime;
        timeUntilAttack -= elapsedTime;
        myAttributes.put("XPosition", String.valueOf(xPos));
        myAttributes.put("YPosition", String.valueOf(yPos));
        return Arrays.asList(xPos, yPos);
    }

    /**
     * Method which takes an entity and returns a new instance of that entity's set attack type
     * */
    public Attack attack() {
        try {
            AttackParser myAttackParser = new AttackParser(this);
            Map<String, Double> attributes = myAttackParser.getAttributeMap();
            Object o = Class.forName(attackBundle.getString(this.getAttackType())).getConstructor(Entity.class, Map.class).newInstance(this, attributes);
            return (Attack) o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void checkAttack(List<Double> heroCoordinates) {
        if (this.getClass().getSuperclass() == Enemy.class && timeUntilAttack <= 0 && withinAttackRange(heroCoordinates)) {
            attack().activateAttack();
        }
    }

    /**
     * Method which returns whether this entity is within attacking range of the hero
     * @return boolean of whether this entity should attack
     * */
    public boolean withinAttackRange(List<Double> heroCoordinates) {
        double distance = StrictMath.hypot(Math.abs(heroCoordinates.get(0) - xPos), Math.abs(heroCoordinates.get(1) - yPos));
        return (distance <= Double.parseDouble(myAttributes.get("AttackRange")));
    }

    /**
     * Getter for the attributes of an entity
     * @return myAttributes
     */
    public Map<String, String> getMyAttributes() {
        return myAttributes;
    }

    /**
     * Change HP method
     * @param diff
     */
    public void changeHp(int diff) {
        hp += diff;
    }

    /**
     * Returns the attack type of this entity
     * @return
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * Returns coordinates of the entity
     * @return coordinates
     */
    public List<Double> coordinates() {
        return Arrays.asList(xPos, yPos);
    }

    /**
     * Changes the direction of the entity
     * @param newDirection
     */
    public void changeDirection(DirectionState newDirection) {
        myDirection = newDirection;
    }

    /**
     * Changes the movement of the entity
     * @param newMovement
     */
    public void changeMovement(MovementState newMovement) {
        myMovement = newMovement;
    }

    /**
     * get the current direction and movement states of the entity
     * @return
     */
    public List<String> getStateStrings() {
        return Arrays.asList(myDirection.getDirectionString(), myMovement.getMovementString());
    }

    /**
     * Returns the current HP of the entity
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns the direction of an entity
     * @return myDirection
     */
    public DirectionState getMyDirection() {
        return myDirection;
    }

    /**
     * Returns the movement of an entity
     * @return myMovement
     */
    public MovementState getMyMovement() {
        return myMovement;
    }

    public int getSize() { return size; }

    /**
     * Returns the time until an entity can attack again
     * @return timeUntilAttack
     */
    public double getTimeUntilAttack() {
        return timeUntilAttack;
    }

    /**
     * Resets the time until an entity can attack again
     */
    public void resetTimeUntilAttack() {
        timeUntilAttack = attackCoolDown;
    }

    /**
     * Method that knocks the entity back
     * @return the distance the entity is knocked back
     */
    public List<Double> knockBack(int force, DirectionState direction) {
        xPos += force * direction.getVelocity().get(0);
        yPos += force * direction.getVelocity().get(1);
        return Arrays.asList(xPos, yPos);
    }

}

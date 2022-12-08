package ooga.model.attack;

import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.entities.Entity;

import java.util.*;
import java.util.List;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");
    private static Controller myController;
    private int damage;
    private double speed;
    private double size;
    private double maxDuration;
    private Entity myEntity;
    private Integer activeAttackID;
    private Double xPos;
    private Double yPos;
    private DirectionState myDirection;
    private double timeSinceActivation;
    private Map<String, Double> myAttributes;


    /**
     * Constructor for the Attack superclass
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
     * */
    public Attack(Entity entity, Map<String, Double> attributes) {
        this.damage = attributes.getOrDefault("Damage", 0.0).intValue();
        this.speed = attributes.getOrDefault("Speed", 0.0);
        this.size = attributes.getOrDefault("Size", 0.0);
        this.maxDuration = attributes.getOrDefault("MaxDuration", 0.0);
        this.myAttributes = attributes;
        this.myEntity = entity;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.myDirection = DirectionState.SOUTH;
    }

    /**
     * Method to initiate this attack and reset its entity's attack timer
     * */
    public void activateAttack() {
        if (myEntity.getTimeUntilAttack() <= 0) {
            myEntity.resetTimeUntilAttack();
            activeAttackID = createRandomID();
            myController.getModelAttacks().put(activeAttackID, this);
            this.myDirection = DirectionState.valueOf(myEntity.getStateStrings().get(0));
            this.timeSinceActivation = 0.0;
            setInitialCoordinates();
        }
    }

    /**
     * Method to set the initial coordinates of this attack, centered on its parent entity
     * */
    private void setInitialCoordinates() {
        int halfSize = Integer.parseInt(myEntity.getMyAttributes().get("Size"))/2;
        double centerX = myEntity.coordinates().get(0) + halfSize;
        double centerY = myEntity.coordinates().get(1) + halfSize;
        this.xPos = centerX + myDirection.getVelocity().get(0) * halfSize;
        this.yPos = centerY + myDirection.getVelocity().get(1) * halfSize;
    }

    /**
     * Method to generate a random attackID which is unique to the other active attacks' IDs
     * */
    private Integer createRandomID() {
        Random r = new Random();
        Integer randomID = r.nextInt(100);
        while (myController.getModelAttacks().containsKey(randomID)) {
            randomID = r.nextInt(100);
        }
        return randomID;
    }

    /**
     * Deactivates this attack, calls the removeAttack method in the controller
     * */
    public void deactivateAttack() {
        myController.removeAttack(activeAttackID);
    }

    /**
     * Method that gets the current direction of the attack
     * @return the current direction of the attack
     */
    public DirectionState getDirection() {
        return myDirection;
    }

    /**
     * deactivates attack if it has been active for longer than its maxDuration
     * otherwise, updates the X and Y positions of the attack
     * @param elapsedTime indicates how much time has passed since the last step
     */
    public List<Double> move(double elapsedTime) {
        timeSinceActivation += elapsedTime;
        if (timeSinceActivation >= maxDuration) {
            deactivateAttack();
        } else {
            xPos += myDirection.getVelocity().get(0) * speed * elapsedTime;
            yPos += myDirection.getVelocity().get(1) * speed * elapsedTime;
        }
        return Arrays.asList(xPos, yPos);
    }

    /**
     * Gets the entity associated with the attack
     * @return the entity associated with the attack
     */
    public Entity getMyEntity() {
        return myEntity;
    }

    /**
     * Gets the damage of the attack
     * @return the damage of the attack
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the attributes associated with the attack
     * @return attribute map of the attack
     */
    public Map<String, Double> getMyAttributes() {
        return myAttributes;
    }

    /**
     * Returns the coordinates of the attack (x, y)
     * @return a list of the coordinates of an attack
     */
    public List<Double> coordinates() {
        return Arrays.asList(xPos, yPos);
    }

    /**
     * Should only be called once during setup of the game to set all attacks' controller
     */
    public static void setMyController(Controller controller) {
        myController = controller;
    }
}

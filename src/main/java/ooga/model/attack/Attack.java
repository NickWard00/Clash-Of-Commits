package ooga.model.attack;

import ooga.controller.AttackParser;
import ooga.controller.Controller;
import ooga.controller.GeneralParser;
import ooga.model.enemy.Enemy;
import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.state.MovementState;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");
    private int damage;
    private double speed;
    private double coolDown;
    private double maxDuration;
    private Entity myEntity;
    private Integer activeAttackID;
    private Double xPos;
    private Double yPos;
    private DirectionState myDirection;
    private double timeSinceActivation;


    /**
     * Constructor for the Attack superclass
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
     * */
    public Attack(Entity entity, Map<String, Double> attributes) {
        this.damage = attributes.getOrDefault("Damage", 0.0).intValue();
        this.speed = attributes.getOrDefault("Speed", 0.0);
        this.coolDown = attributes.getOrDefault("CoolDown", 1.0);
        this.maxDuration = attributes.getOrDefault("MaxDuration", 0.0);
        this.myEntity = entity;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.myDirection = DirectionState.SOUTH;
    }

    /**
     * Method which takes an entity and invokes that entity's set attack
     * @param entity the entity to initiate an attack
     * */
    public static Attack attack(Entity entity) {
        try {
            AttackParser myAttackParser = new AttackParser(entity);
            Map<String, Double> attributes = myAttackParser.getAttributeMap();
            Object o = Class.forName(attackBundle.getString(entity.getAttackType())).getConstructor(Entity.class, Map.class).newInstance(entity, attributes);
            return (Attack) o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void activateAttack() {
        if (myEntity.getTimeUntilAttack() <= 0) {
            activeAttackID = createRandomID();
            Controller.getModelAttacks().put(activeAttackID, this);
            this.myDirection = DirectionState.valueOf(myEntity.getStateStrings().get(0));
            // TODO: establish whether entity coordinates are centered or upper left aligned and adjust this accordingly
            this.xPos = myEntity.coordinates().get(0) + (myDirection.getVelocity().get(0) * Integer.parseInt(myEntity.getMyAttributes().get("Size")));
            this.yPos = myEntity.coordinates().get(1) + (myDirection.getVelocity().get(1) * Integer.parseInt(myEntity.getMyAttributes().get("Size")));
            this.timeSinceActivation = 0.0;
            myEntity.resetTimeUntilAttack();
        }
    }

    private Integer createRandomID() {
        Random r = new Random();
        Integer randomID = r.nextInt(100);
        while (Controller.getModelAttacks().containsKey(randomID)) {
            randomID = r.nextInt(100);
        }
        return randomID;
    }

    public void deactivateAttack() {
        Controller.getModelAttacks().remove(activeAttackID);
        Controller.getViewAttacks().remove(activeAttackID);
    }

    public DirectionState getDirection() {
        return myDirection;
    }

    /**
     * @param elapsedTime indicates how much time has passed since the last step
     * deactivates attack if it has been active for longer than its maxDuration
     * otherwise, updates the X and Y positions of the attack
     * */
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

    public Entity getMyEntity() {
        return myEntity;
    }

    public int getDamage() {
        return damage;
    }
}

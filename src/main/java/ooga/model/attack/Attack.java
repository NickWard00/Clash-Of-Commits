package ooga.model.attack;

import ooga.controller.AttackParser;
import ooga.controller.GeneralParser;
import ooga.model.enemy.Enemy;
import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.state.MovementState;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");
    private Entity myEntity;
    private int damage;
    private double speed;
    private double coolDown;
    private Double xPos;
    private Double yPos;
    private DirectionState myDirection;



    /**
     * Constructor for the Attack superclass
     * @param entity the entity that initiated this attack
     * @param attributes map of the attributes (speed, damage, cool down, etc.) for this entity's designated attack
     * */
    public Attack(Entity entity, Map<String, Double> attributes) {
        this.myEntity = entity;
        this.damage = attributes.getOrDefault("Damage", 0.0).intValue();
        this.speed = attributes.getOrDefault("Speed", 0.0);
        this.coolDown = attributes.getOrDefault("CoolDown", 1.0);
        this.myDirection = DirectionState.valueOf(entity.getStateStrings().get(0));
        // TODO: establish whether entity coordinates are centered or upper left aligned and adjust this accordingly
        this.xPos = entity.coordinates().get(0) + (myDirection.getVelocity().get(0) * Integer.parseInt(entity.getMyAttributes().get("Size")));
        this.yPos = entity.coordinates().get(1) + (myDirection.getVelocity().get(1) * Integer.parseInt(entity.getMyAttributes().get("Size")));
    }

    public abstract void activateAttack();

    /**
     * Method which takes an entity and invokes that entity's set attack
     * @param entity the entity to initiate an attack
     * */
    public static Attack attack(Entity entity) {
        try {
            AttackParser myAttackParser = new AttackParser(entity);
            Map<String, Double> attributes = myAttackParser.getAttributeMap();
            Object o = Class.forName(attackBundle.getString(entity.getAttackType())).getConstructor(Entity.class, Map.class).newInstance(entity, attributes);
            Attack a = (Attack) o;
            return a;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param elapsedTime indicates how much time has passed since the last step
     * updates the X and Y positions of the attack
     * */
    public List<Double> move(double elapsedTime) {
        xPos += myDirection.getVelocity().get(0) * speed * elapsedTime;
        yPos += myDirection.getVelocity().get(1) * speed * elapsedTime;
        return Arrays.asList(xPos, yPos);
    }


    public Entity getMyEntity() { return myEntity; }

    public int getDamage() { return damage; }
}

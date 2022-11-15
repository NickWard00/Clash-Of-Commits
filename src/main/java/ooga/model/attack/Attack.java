package ooga.model.attack;

import ooga.model.state.DirectionState;
import ooga.model.Entity;
import ooga.model.state.MovementState;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");
    private Entity myEntity;

    /**
     * Constructor for the Attack superclass
     * @param entity the entity that initiated this attack
     * */
    public Attack(Entity entity) {
        this.myEntity = entity;
    }

    public abstract void activateAttack();

    /**
     * Method which takes an entity and invokes that entity's set attack
     * @param entity the entity to initiate an attack
     * */
    public static void attack(Entity entity) {
        try {
            Object o = Class.forName(attackBundle.getString(entity.getAttackType())).getConstructor(Entity.class).newInstance(entity);
            Method attackMethod = o.getClass().getMethod("activateAttack", Entity.class);
            attackMethod.invoke(o, entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Entity getMyEntity() { return myEntity; }
}

package ooga.model.attack;

import ooga.model.state.DirectionState;
import ooga.model.Entity;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("ResourceBundles.Attack");
    private Entity myEntity;

    public Attack(Entity entity) {
        this.myEntity = entity;
    }

    public abstract void activateAttack(Entity entity);

    public void attack() {
        try {
            Object o = Class.forName(attackBundle.getString(myEntity.getAttackType())).getConstructor(Entity.class).newInstance(myEntity);
            Method attackMethod = o.getClass().getMethod("activateAttack", Entity.class);
            attackMethod.invoke(o, myEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Entity getMyEntity() { return myEntity; }
}

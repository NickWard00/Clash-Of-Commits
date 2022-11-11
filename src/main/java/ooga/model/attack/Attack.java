package ooga.model.attack;

import ooga.model.Entity;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

public abstract class Attack {
    public static final ResourceBundle attackBundle = ResourceBundle.getBundle("properties.Attack");

    public Attack() {}

    public abstract void attackAction();

    public static void attack(Entity entity) {
        try {
            Object o = Class.forName(attackBundle.getString(entity.getAttackType())).getConstructor().newInstance();
            Method attackMethod = o.getClass().getMethod("attackAction");
            attackMethod.invoke(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

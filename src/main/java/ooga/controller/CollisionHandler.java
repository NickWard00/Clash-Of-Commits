package ooga.controller;


import ooga.model.Collision;
import ooga.model.attack.Attack;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CollisionHandler {


    public CollisionHandler() {}


    public static void handleCollision(Object object1, Object object2) {
        // TODO: find a better/cleaner way to implement this without using if's
        try {
            Class class1 = object1.getClass().getSuperclass();
            Class class2 = object2.getClass().getSuperclass();
            if (class1 != Attack.class) { class1 = class1.getSuperclass(); }
            if (class2 != Attack.class) { class2 = class2.getSuperclass(); }
            Set<Class> classes = new HashSet<>(Arrays.asList(class1, class2));
            Collision collision = Collision.class.getConstructor(class1, class2).newInstance(object1, object2);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

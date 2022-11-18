package ooga.controller;


import ooga.model.Collision;
import ooga.model.Entity;
import ooga.model.attack.Attack;
import ooga.model.obstacle.Obstacle;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CollisionHandler {


    public CollisionHandler() {}


    public static void handleCollision(Object object1, Object object2) {
        Map<Class, Integer> indexMap = Map.of(Attack.class, 0, Entity.class, 1, Obstacle.class, 2);
        try {
            Class class1 = getCorrectClassForCollision(object1);
            Class class2 = getCorrectClassForCollision(object2);
            Map<Object, Class> myClassCategory = Map.of(object1, class1, object2, class2);
            List<Object> myObjects = Arrays.asList(object1, object2);
            myObjects.sort(Comparator.comparing((Object o) -> indexMap.get(myClassCategory.get(o))));
            Collision.class.getConstructor(
                    myClassCategory.get(myObjects.get(0)), myClassCategory.get(myObjects.get(1))).newInstance(myObjects.get(0), myObjects.get(1));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Class getCorrectClassForCollision(Object object) {
        Class myClass = object.getClass().getSuperclass();
        if (myClass != Attack.class) {
            myClass = myClass.getSuperclass();
        }
        return myClass;
    }

}

package ooga.controller;

import ooga.model.Collision;
import ooga.model.entities.Entity;
import ooga.model.attack.Attack;
import ooga.model.obstacle.Obstacle;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CollisionHandler {
    private Map<String, Map<?,?>> viewModelMap;
    private Collision myCollision;

    /**
     * Constructor for the CollisionHandler class
     * @param viewModelMap
     */
    public CollisionHandler(Map<String, Map<?,?>> viewModelMap) {
        this.viewModelMap = viewModelMap;
        myCollision = new Collision(viewModelMap);
    }

    /**
     * Method which uses reflection to handle collisions of all types
     */
    public void collision(Object object1, Object object2) {
        Map<Class, Integer> indexMap = Map.of(Attack.class, 0, Entity.class, 1, Obstacle.class, 2);
        try {
            Class class1 = getCorrectClassForCollision(object1);
            Class class2 = getCorrectClassForCollision(object2);
            Map<Object, Class> myClassCategory = Map.of(object1, class1, object2, class2);
            List<Object> myObjects = Arrays.asList(object1, object2);
            myObjects.sort(Comparator.comparing((Object o) -> indexMap.get(myClassCategory.get(o))));
            String className = String.format("ooga.model.%s%sCollision", myClassCategory.get(myObjects.get(0)).getSimpleName(), myClassCategory.get(myObjects.get(1)).getSimpleName());
            Class thisClass = Class.forName(className);
            Object myCollisionClass = thisClass.getConstructor(Map.class).newInstance(viewModelMap);
            thisClass.getMethod("collide", myClassCategory.get(myObjects.get(0)), myClassCategory.get(myObjects.get(1)))
                    .invoke(myCollisionClass, myObjects.get(0), myObjects.get(1));
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("noMethodFound", e);
        }
    }

    /**
     * Translates the view objects passed in into the correct type of collision with model objects
     * @param viewObj1
     * @param viewObj2
     * @param modelMap1
     * @param modelMap2
     */
    public void translateCollision(Object viewObj1, Object viewObj2, Map<?,?> modelMap1, Map<?,?> modelMap2) {
        try {
            Object key1 = viewObj1.getClass().getDeclaredMethod("getKey").invoke(viewObj1);
            Object key2 = viewObj2.getClass().getDeclaredMethod("getKey").invoke(viewObj2);
            collision(modelMap1.get(key1), modelMap2.get(key2));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalStateException("noMethodFound", e);
        }
    }

    /**
     * Method to get the correct class for collision
     * @param object
     * @return
     */
    private Class getCorrectClassForCollision(Object object) {
        Class myClass = object.getClass().getSuperclass();
        if (myClass != Attack.class && myClass != Obstacle.class) {
            myClass = myClass.getSuperclass();
        }
        return myClass;
    }
}

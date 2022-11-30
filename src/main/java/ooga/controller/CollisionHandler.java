package ooga.controller;


import ooga.model.Collision;
import ooga.model.Entity;
import ooga.model.attack.Attack;
import ooga.model.obstacle.Obstacle;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import ooga.view.BlockView;
import ooga.view.EntityView;

public class CollisionHandler {

    private Map<String, Entity> modelEntities;
    private Map<String, EntityView> viewEntities;
    private Obstacle collisionObstacle;
    private Entity collisionEntity;

    public CollisionHandler(Map<String, Entity> modelEntities, Map<String, EntityView> viewEntities) {
        this.modelEntities = modelEntities;
        this.viewEntities = viewEntities;
    }


    public void collision(Object object1, Object object2) {
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

    public void translateCollision(EntityView entityView, BlockView obstacleView, Map<List<Double>, Obstacle> modelObstacles) {
        collisionEntity = modelEntities.get(entityView.getEntityName());
        List<Double> obstacleCoordinates = Arrays.asList(obstacleView.getXPosition(), obstacleView.getYPosition());
        collisionObstacle = modelObstacles.get(obstacleCoordinates);
        collision(collisionEntity, collisionObstacle);
    }

    private static Class getCorrectClassForCollision(Object object) {
        Class myClass = object.getClass().getSuperclass();
        if (myClass != Attack.class) {
            myClass = myClass.getSuperclass();
        }
        return myClass;
    }
}

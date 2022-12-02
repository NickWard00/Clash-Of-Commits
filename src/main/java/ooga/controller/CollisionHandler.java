package ooga.controller;


import ooga.model.Collision;
import ooga.model.Entity;
import ooga.model.attack.Attack;
import ooga.model.enemy.Enemy;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Feature;
import ooga.model.obstacle.ImmovableWall;
import ooga.model.obstacle.Obstacle;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import ooga.view.AttackView;
import ooga.view.BlockView;
import ooga.view.EntityView;

public class CollisionHandler {

    private Map<String, Entity> modelEntities;
    private Map<String, EntityView> viewEntities;
    private Map<Integer, Attack> modelAttacks;
    private Map<Integer, AttackView> viewAttacks;
    private Map<List<Double>, Obstacle> modelObstacles;
    private Map<List<Double>, BlockView> viewObstacles;


    public CollisionHandler(Map<String, Map<?,?>> viewModelMap) {
        this.modelEntities = (Map<String, Entity>) viewModelMap.get("modelEntities");
        this.viewEntities = (Map<String, EntityView>) viewModelMap.get("viewEntities");
        this.modelAttacks = (Map<Integer, Attack>) viewModelMap.get("modelAttacks");
        this.viewAttacks = (Map<Integer, AttackView>) viewModelMap.get("viewAttacks");
        this.modelObstacles = (Map<List<Double>, Obstacle>) viewModelMap.get("modelObstacles");
        this.viewObstacles = (Map<List<Double>, BlockView>) viewModelMap.get("viewObstacles");
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


    public void translateCollision(Object viewObj1, Object viewObj2, Map<?,?> modelMap1, Map<?,?> modelMap2) {
        try {
            Object key1 = viewObj1.getClass().getDeclaredMethod("getKey").invoke(viewObj1);
            Object key2 = viewObj2.getClass().getDeclaredMethod("getKey").invoke(viewObj2);
            collision(modelMap1.get(key1), modelMap2.get(key2));
            if (viewObj1.getClass() == EntityView.class && viewObj2.getClass() == BlockView.class) {
                checkKnockBack((EntityView) viewObj1, (BlockView) viewObj2);
            } else if (viewObj2.getClass() == EntityView.class && viewObj1.getClass() == BlockView.class) {
                checkKnockBack((EntityView) viewObj2, (BlockView) viewObj1);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
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

    private void checkKnockBack(EntityView entityView, BlockView obstacleView) {
        Entity entity = modelEntities.get(entityView.getKey());
        Obstacle obstacle = modelObstacles.get(obstacleView.getKey());
        if (obstacle.getClass() != Feature.class) {
            handleKnockBack(entityView.getKey(), 2);
            if (entity.getClass().getSuperclass() == Enemy.class) {
                entityView.changeDirectionAndMovement(entity.getMyDirection().oppositeDirection(), entity.getMyMovement());
            }
        }
    }

    private void handleKnockBack(String entityName, int force) {
        Entity entity = modelEntities.get(entityName);
        List<Double> knockBackCoordinates = entity.knockBack(force);
        EntityView entityView = viewEntities.get(entityName);
        entityView.setCoordinate(knockBackCoordinates);
    }
}

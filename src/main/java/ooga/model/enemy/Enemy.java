package ooga.model.enemy;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ooga.model.Entity;
import ooga.model.attack.Attack;

public abstract class Enemy extends Entity {

    public Enemy(Map<String, String> attributes) {
        super(attributes);
    }

    public static Enemy makeEnemy(Class<? extends Enemy> enemyClass, Map<String, String> attributes) {
        try {
            Enemy newEnemy = enemyClass.getDeclaredConstructor(Map.class).newInstance(attributes);
            return newEnemy;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeRandomEnemy(List<Class<? extends Enemy>> possibleEnemies, Map<String, String> attributes) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleEnemies.size());
        makeEnemy(possibleEnemies.get(randomIndex), attributes);
    }

}

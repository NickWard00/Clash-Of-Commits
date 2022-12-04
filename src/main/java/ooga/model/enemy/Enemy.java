package ooga.model.enemy;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ooga.model.Entity;
import ooga.model.attack.Attack;

public abstract class Enemy extends Entity {

    /**
     * Constructor for the Enemy superclass that also extends the Entity class
     * @param attributes a string to string map of the enemy's attributes (speed, hp, etc.)
     * */
    public Enemy(Map<String, String> attributes) {
        super(attributes);
    }

    /**
     * Method to make a new enemy of a specified subclass through reflection
     * @param enemyClass the specific subclass of enemy to be created (Bug, MagicValue, etc.)
     * @param attributes a map of the attributes (size, attack type, etc.) of the enemy to be created
     * */
    public static Enemy makeEnemy(Class<? extends Enemy> enemyClass, Map<String, String> attributes) {
        try {
            Enemy newEnemy = enemyClass.getDeclaredConstructor(Map.class).newInstance(attributes);
            return newEnemy;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Enemy makeRandomEnemy(List<Class<? extends Enemy>> possibleEnemies, Map<String, String> attributes) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleEnemies.size());
        Enemy randomEnemy = makeEnemy(possibleEnemies.get(randomIndex), attributes);
        return randomEnemy;
    }



}

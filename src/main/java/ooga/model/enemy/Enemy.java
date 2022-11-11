package ooga.model.enemy;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import ooga.model.Entity;
import ooga.model.attack.Attack;

public abstract class Enemy extends Entity {

    public Enemy(Double xPos, Double yPos, int hp, int speed, int size, String attack) {
        super(xPos, yPos, hp, speed, size, attack);
    }

    protected Enemy() {}

    public static Enemy makeEnemy(Class<? extends Enemy> enemyClass, Double x, Double y) {
        try {
            Enemy newEnemy = enemyClass.getDeclaredConstructor(Double.class, Double.class).newInstance(x,y);
            return newEnemy;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void makeRandomEnemy(List<Class<? extends Enemy>> possibleEnemies, Double x, Double y) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleEnemies.size());
        makeEnemy(possibleEnemies.get(randomIndex), x, y);
    }

}

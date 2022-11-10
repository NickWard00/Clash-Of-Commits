package ooga.model.enemy;

import java.util.List;
import java.util.Random;
import ooga.model.Entity;

public abstract class Enemy extends Entity {

    public Enemy(Double xPos, Double yPos, int hp) {
        super(xPos, yPos, hp);
    }

    public static Enemy makeEnemy(Class<? extends Enemy> enemyClass, Double x, Double y) {
        try {
            Enemy newEnemy = enemyClass.getDeclaredConstructor(Double.class, Double.class).newInstance(x,y);
            // add newEnemy to list of entities
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

    public abstract void attack();
    public abstract void move();

}

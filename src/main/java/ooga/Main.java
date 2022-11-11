package ooga;


import ooga.model.Entity;
import ooga.model.attack.Attack;
import ooga.model.enemy.Bug;
import ooga.model.enemy.Enemy;
import ooga.model.enemy.MagicValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Bug b = new Bug(10, 10, 10, "Attack1");
        MagicValue m = new MagicValue(10, 10, 10, "Attack2");

        Enemy bug = Enemy.makeEnemy(Bug.class, 1.0, 1.0);
        Enemy magicValue = Enemy.makeEnemy(MagicValue.class, 5.0, 2.0);
        List<Enemy> enemies = Arrays.asList(bug, magicValue);

        Consumer<Enemy> attackMethod = (enemy) -> { Attack.attack(enemy); };
        enemies.iterator().forEachRemaining(attackMethod);
    }
}

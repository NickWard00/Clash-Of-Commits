package ooga;


import ooga.model.Bug;
import ooga.model.Enemy;
import ooga.model.MagicValue;

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
        Enemy bug = Enemy.makeEnemy(Bug.class, 1.0, 1.0);
        bug.attack();
        Enemy magicValue = Enemy.makeEnemy(MagicValue.class, 5.0, 2.0);
        magicValue.attack();
    }
}

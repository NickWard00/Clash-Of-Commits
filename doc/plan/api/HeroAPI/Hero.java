/**
 * Class that contains information about the Hero's
 * position, number of "points", health level, abilities and
 * upgrades, etc.
 */
public class Hero extends Entity{
    //constructor which initializes the Hero at a certain position,
    // likely based off information in the .csv file upload
    Hero(int initialPowers, int xPos, int yPos){}

    //abstract method to be implemented by Hero sub-classes based on their individual powers
    attack(){}

    //intializes a Bullet moving from Hero's location in the specified x and y direction
    shoot(double xDirection, double yDirection){}

    //intializes a Bullet moving from Hero's location in the specified angle
    shoot(double degrees){}

    //method to move the hero by a certain distance in x and y direction
    move(double deltaX, double deltaY) {}

    // method to change the speed (either speedup or slowdown)
    changeSpeed(double deltaSpeed){}

    //decrements health of the hero by an amount specified by the Hero type
    //checks if it's been destroyed due to health too low (in which case it calls die())
    takeDamage() {}

    //replenishes health points, removes all coins/points acquired, decrements one life
    die() {}

}
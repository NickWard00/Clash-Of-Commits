public abstract class Enemy {

    // method which uses reflection to make a new instance of a specific enemy type
    // instantiates the new enemy at coordinates xPos, yPos
    makeEnemy(Class<? extends Enemy> enemyClass, int xPos, int yPos) {}

    // method which takes a list of enemy classes, picks one at random, then calls
    // makeEnemy with that class
    makeRandomEnemy(List<Class> possibleEnemies, int xPos, int yPos) {}

    // method to get the x and y coordinates of this enemy
    getCoordinates() {}

    // method which decrements the health of this enemy then checks if it's been destroyed
    takeDamage() {}

    // abstract method which subclasses will override to implement specific movement rules
    move() {}

}
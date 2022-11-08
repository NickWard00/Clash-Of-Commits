public abstract class Enemy {
    public void makeEnemy(Class<? extends Enemy> enemyClass, int x, int y) {
        try {
            Enemy newEnemy = enemyClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(x,y);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void makeRandomEnemy(List<Class<? extends Enemy>> possibleEnemies, int x, int y) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleEnemies.size());
        makeEnemy(possibleEnemies.get(randomIndex), x, y);
    }
}

public class Bug extends Enemy {
    private int xPos;
    private int yPos;
    public Bug(int x, int y) {
        xPos = x;
        yPos = y;
    }
}


public class Example {

    // somewhere within the FileParser, reads through a file and generates enemies
    public void parseData() {
        while (file.hasNextLine()) {
            data = file.nextLine(); // some code to get the enemy class and coordinates
            Enemy.makeEnemy(Bug.class, 50, 50);
        }
    }

}
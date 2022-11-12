package ooga.model.hero;

import ooga.model.Entity;

import java.util.List;
import java.util.Random;
public abstract class Hero extends Entity {
    public Hero(Double xPos, Double yPos, int hp) {
        super(xPos, yPos, hp);
    }

    public static Hero makeHero(Class<? extends Hero> heroClass, Double x, Double y) {
        try {
            Hero newHero = heroClass.getDeclaredConstructor(Double.class, Double.class).newInstance(x,y);
            return newHero;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void makeRandomEnemy(List<Class<? extends Hero>> possibleHeroes, Double x, Double y) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleHeroes.size());
        makeHero(possibleHeroes.get(randomIndex), x, y);
    }

    public abstract void attack();
    public abstract void move();

}

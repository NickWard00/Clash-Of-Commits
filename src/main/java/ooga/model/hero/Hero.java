package ooga.model.hero;

import ooga.model.Entity;

import java.util.List;
import java.util.Map;
import java.util.Random;
public abstract class Hero extends Entity {
    public Hero(Map<String, String> attributes) {
        super(attributes);
    }

    public static Hero makeHero(Class<? extends Hero> heroClass, Map<String, String> attributes) {
        try {
            Hero newHero = heroClass.getDeclaredConstructor(Map.class, Map.class).newInstance(attributes);
            return newHero;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void makeRandomHero(List<Class<? extends Hero>> possibleHeroes, Map<String, String> attributes, Map<Integer, String> states) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleHeroes.size());
        makeHero(possibleHeroes.get(randomIndex), attributes);
    }

    public abstract void attack();
    public abstract void move();

}

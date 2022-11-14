package ooga.model.hero;

import ooga.model.Entity;

import java.util.List;
import java.util.Map;
import java.util.Random;
public abstract class Hero extends Entity {
    public Hero(Map<String, String> attributes, Map<Integer, List<String>> states) {
        super(attributes, states);
    }

    public static Hero makeHero(Class<? extends Hero> heroClass, Map<String, String> attributes, Map<Integer, String> states) {
        try {
            Hero newHero = heroClass.getDeclaredConstructor(Map.class, Map.class).newInstance(attributes, states);
            return newHero;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void makeRandomEnemy(List<Class<? extends Hero>> possibleHeroes, Map<String, String> attributes, Map<Integer, String> states) {
        Random r = new Random();
        int randomIndex = r.nextInt(possibleHeroes.size());
        makeHero(possibleHeroes.get(randomIndex), attributes, states);
    }

    public abstract void attack();
    public abstract void move();

}

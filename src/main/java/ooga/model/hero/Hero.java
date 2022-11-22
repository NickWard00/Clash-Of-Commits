package ooga.model.hero;

import ooga.model.Entity;

import java.util.List;
import java.util.Map;
import java.util.Random;
public abstract class Hero extends Entity {
    public Hero(Map<String, String> attributes) {
        super(attributes);
    }
}

package ooga.model;

import java.util.Map;

public abstract class Entity {
    private Double xPos;
    private Double yPos;
    private int max_hp;
    private double speed;
    private int size;
    private Map<Integer, String> myStates;
    private Map<String, String> myAttributes;
    private String attackType;
    private int hp;

    public Entity(Map<String, String> attributes, Map<Integer, String> states) {
        this.xPos = Double.parseDouble(attributes.get("XPosition"));
        this.yPos = Double.parseDouble(attributes.get("YPosition"));
        this.max_hp = Integer.parseInt(attributes.get("HP"));
        this.hp = max_hp;
        this.speed = Double.parseDouble(attributes.get("Speed"));
        this.size = Integer.parseInt(attributes.get("Size"));
        this.attackType = "S";
        this.myStates = states;
        this.myAttributes = attributes;
    }

    protected Entity() {}

    protected void changeHp(int diff) {
        hp += diff;
    }

    public String getAttackType() {
        return attackType;
    }

}

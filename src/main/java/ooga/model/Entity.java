package ooga.model;

public abstract class Entity {
    private Double xPos;
    private Double yPos;
    private int max_hp;
    private int speed;
    private int size;
    private String attackType;
    private int hp;

    public Entity(Double xPos, Double yPos, int hp, int speed, int size, String attack) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.max_hp = hp;
        this.hp = max_hp;
        this.speed = speed;
        this.size = size;
        this.attackType = attack;
    }

    protected Entity() {}

    protected void changeHp(int diff) {
        hp += diff;
    }

    public String getAttackType() {
        return attackType;
    }

}

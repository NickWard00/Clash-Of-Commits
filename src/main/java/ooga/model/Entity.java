package ooga.model;

public abstract class Entity {
    private Double xPos;
    private Double yPos;
    private int hp;

    public Entity(Double xPos, Double yPos, int hp) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.hp = hp;
    }

    protected void changeHp(int diff) {
        hp += diff;
    }

}

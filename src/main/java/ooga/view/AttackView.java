package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.model.state.DirectionState;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Nick Ward, Melanie Wang
 */
public class AttackView extends ImageView {
    private String attackType;
    private int attackID;
    private String imagePath;
    private Image northSprite;
    private Image southSprite;
    private Image westSprite;
    private Image eastSprite;

    /**
     * Creates a new AttackView with the given image path, attack type, x & y position, and size
     * @param imagePath
     * @param xSize
     * @param ySize
     */
    public AttackView(String imagePath, String attackType, double xPos, double yPos, int xSize, int ySize, int attackID) {
        super(new Image(imagePath));
        this.imagePath = imagePath;
        this.attackType = attackType;
        this.attackID = attackID;
        // setupSprites();
        this.setFitWidth(xSize);
        this.setFitHeight(ySize);
        this.setX(xPos - xSize/2);
        this.setY(yPos - ySize/2);
    }

    /**
     * Sets the sprite to the corresponding direction
     * @param newDirection the new direction of the sprite
     */
    public void changeDirection(DirectionState newDirection) {
        String spriteDirection = newDirection.getDirectionString();
        try {
            this.getClass().getDeclaredMethod(String.format("set%sSprite", spriteDirection)).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        }
    }

    /**
     * Sets the sprite to the north sprite
     */
    private void setNORTHSprite() {
        this.setImage(northSprite);
    }

    /**
     * Sets the sprite to the south sprite
     */
    private void setSOUTHSprite() {
        this.setImage(southSprite);
    }

    /**
     * Sets the sprite to the west sprite
     */
    private void setWESTSprite() {
        this.setImage(westSprite);
    }

    /**
     * Sets the sprite to the east sprite
     */
    private void setEASTSprite() {
        this.setImage(eastSprite);
    }

    /**
     * Sets up the sprites for the entity for each direction
     */
    private void setupSprites() {
        String imagePathNorth = String.format("%s%s_%s.png", imagePath, attackType, "NORTH");
        String imagePathSouth = String.format("%s%s_%s.png", imagePath, attackType, "SOUTH");
        String imagePathWest = String.format("%s%s_%s.png", imagePath, attackType, "WEST");
        String imagePathEast = String.format("%s%s_%s.png", imagePath, attackType, "EAST");
        northSprite = new Image(imagePathNorth);
        southSprite = new Image(imagePathSouth);
        westSprite = new Image(imagePathWest);
        eastSprite = new Image(imagePathEast);
    }

    /**
     * Returns the attackType of the AttackView
     * @return attackType
     */
    public String getAttackType() {
        return attackType;
    }

    public int getKey() { return attackID; }
}

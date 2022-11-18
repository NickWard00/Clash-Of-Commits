package ooga.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.model.state.DirectionState;

import java.lang.reflect.InvocationTargetException;

public class EntityView extends ImageView {
    private String entityName;
    private String imagePath;
    private Image northSprite;
    private Image southSprite;
    private Image westSprite;
    private Image eastSprite;

    /**
     * Creates an EntityView with the given image path and entity name
     * @param imagePath
     * @param entityName
     */
    public EntityView(String imagePath, String entityName) {
        super(new Image(imagePath));
        this.entityName = entityName;
    }

    /**
     * Creates a new EntityView with the given image path, entity name, x & y position, and size
     * @param imagePath
     * @param direction
     * @param entityName
     * @param xPosition
     * @param yPosition
     * @param xSize
     * @param ySize
     */
    public EntityView(String imagePath, String direction, String entityName, double xPosition, double yPosition, int xSize, int ySize) {
        // Example: imagePath = "sprites/bug/", direction = "NORTH", so Image will be created with "sprites/bug/NORTH.gif"
        super(new Image(String.format("%s%s.GIF", imagePath, direction.toUpperCase())));
        this.imagePath = imagePath;
        setupSprites(imagePath);
        this.entityName = entityName;
        this.setX(xPosition);
        this.setY(yPosition);
        this.setFitWidth(xSize);
        this.setFitHeight(ySize);
    }

    public void changeDirection(DirectionState newDirection) {
        String spriteDirection = newDirection.getDirection();
        try {
            this.getClass().getDeclaredMethod(String.format("set%sSprite", spriteDirection)).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        }
    }

    private void setNORTHSprite() {
        this.setImage(northSprite);
    }

    private void setSOUTHSprite() {
        this.setImage(southSprite);
    }

    private void setWESTSprite() {
        this.setImage(westSprite);
    }

    private void setEASTSprite() {
        this.setImage(eastSprite);
    }

    private void setupSprites(String spritePath) {
        String imagePathNorth = String.format("%s%s.gif", spritePath, "NORTH");
        String imagePathSouth = String.format("%s%s.gif", spritePath, "SOUTH");
        String imagePathWest = String.format("%s%s.gif", spritePath, "WEST");
        String imagePathEast = String.format("%s%s.gif", spritePath, "EAST");
        northSprite = new Image(imagePathNorth);
        southSprite = new Image(imagePathSouth);
        westSprite = new Image(imagePathWest);
        eastSprite = new Image(imagePathEast);
    }

    /**
     * Returns the entity name
     * @return
     */
    public String getEntityName() {
        return entityName;
    }
}

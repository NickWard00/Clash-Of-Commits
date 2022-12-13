package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nick Ward, Melanie Wang
 */
public class EntityView extends ImageView {
    private String entityName;
    private String imagePath;
    private Image northSprite;
    private Image southSprite;
    private Image westSprite;
    private Image eastSprite;
    private Image northStationarySprite;
    private Image eastStationarySprite;
    private Image westStationarySprite;
    private Image southStationarySprite;
    private Image northAttackSprite;
    private Image eastAttackSprite;
    private Image westAttackSprite;
    private Image southAttackSprite;

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

    /**
     * Changes the sprite of the EntityView to the given direction and movement state
     * @param direction
     * @param movement
     */
    public void changeDirectionAndMovement(DirectionState direction, MovementState movement) {
        String spriteDirection = direction.getDirectionString();
        //Casts Sprinting movements to Moving View movements, as there is no difference in the sprite
        if(movement.equals(MovementState.SPRINTING)){
            movement = MovementState.MOVING;
        }
        String spriteMovement = movement.getMovementString();
        try {
            String path = String.format("set%s_%sSprite", spriteDirection, spriteMovement);
            this.getClass().getDeclaredMethod(path).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        }
    }

    /**
     * Sets the image of the EntityView to the north moving sprite
     */
    private void setNORTH_MOVINGSprite() {
        this.setImage(northSprite);
    }

    /**
     * Sets the image of the EntityView to the west moving sprite
     */
    private void setWEST_MOVINGSprite() {
        this.setImage(westSprite);
    }

    /**
     * Sets the image of the EntityView to the east moving sprite
     */
    private void setEAST_MOVINGSprite() {
        this.setImage(eastSprite);
    }

    /**
     * Sets the image of the EntityView to the south moving sprite
     */
    private void setSOUTH_MOVINGSprite() {
        this.setImage(southSprite);
    }

    /**
     * Sets the image of the EntityView to the north stationary sprite
     */
    private void setNORTH_STATIONARYSprite(){
        this.setImage(northStationarySprite);
    }

    /**
     * Sets the image of the EntityView to the west stationary sprite
     */
    private void setWEST_STATIONARYSprite(){
        this.setImage(westStationarySprite);
    }

    /**
     * Sets the image of the EntityView to the east stationary sprite
     */
    private void setEAST_STATIONARYSprite(){
        this.setImage(eastStationarySprite);
    }

    /**
     * Sets the image of the EntityView to the south stationary sprite
     */
    private void setSOUTH_STATIONARYSprite(){
        this.setImage(southStationarySprite);
    }

    /**
     * Sets the image of the EntityView to the north attack sprite
     */
    private void setNORTH_ATTACKSprite(){
        this.setImage(northAttackSprite);
    }

    /**
     * Sets the image of the EntityView to the west attack sprite
     */
    private void setWEST_ATTACKSprite(){
        this.setImage(westAttackSprite);
    }

    /**
     * Sets the image of the EntityView to the east attack sprite
     */
    private void setEAST_ATTACKSprite(){
        this.setImage(eastAttackSprite);
    }

    /**
     * Sets the image of the EntityView to the south attack sprite
     */
    private void setSOUTH_ATTACKSprite(){
        this.setImage(southAttackSprite);
    }

    /**
     * Sets the sprite for each direction
     * @param spritePath
     */
    private void setupSprites(String spritePath) {
        String imagePathNorth = String.format("%s%s.gif", spritePath, "NORTH");
        String imagePathSouth = String.format("%s%s.gif", spritePath, "SOUTH");
        String imagePathWest = String.format("%s%s.gif", spritePath, "WEST");
        String imagePathEast = String.format("%s%s.gif", spritePath, "EAST");
        String imagePathNorthStationary = String.format("%s%s.gif", spritePath, "NORTH_STATIONARY");
        String imagePathEastStationary = String.format("%s%s.gif", spritePath, "EAST_STATIONARY");
        String imagePathSouthStationary = String.format("%s%s.gif", spritePath, "SOUTH_STATIONARY");
        String imagePathWestStationary = String.format("%s%s.gif", spritePath, "WEST_STATIONARY");
        String imagePathNorthAttack = String.format("%s%s.gif", spritePath, "NORTH_ATTACK");
        String imagePathEastAttack = String.format("%s%s.gif", spritePath, "EAST_ATTACK");
        String imagePathSouthAttack = String.format("%s%s.gif", spritePath, "SOUTH_ATTACK");
        String imagePathWestAttack = String.format("%s%s.gif", spritePath, "WEST_ATTACK");

        northSprite = new Image(imagePathNorth);
        southSprite = new Image(imagePathSouth);
        westSprite = new Image(imagePathWest);
        eastSprite = new Image(imagePathEast);
        northStationarySprite = new Image(imagePathNorthStationary);
        southStationarySprite = new Image(imagePathSouthStationary);
        westStationarySprite = new Image(imagePathWestStationary);
        eastStationarySprite = new Image(imagePathEastStationary);
        northAttackSprite = new Image(imagePathNorthAttack);
        eastAttackSprite = new Image(imagePathEastAttack);
        westAttackSprite = new Image(imagePathWestAttack);
        southAttackSprite = new Image(imagePathSouthAttack);
    }

    /**
     * Returns the entity name
     * @return entityName
     */
    public String getKey() {
        return entityName;
    }
}

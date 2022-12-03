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


    public void changeDirection(DirectionState newDirection) {
        String spriteDirection = newDirection.getDirectionString();
        try {
            this.getClass().getDeclaredMethod(String.format("set%sSprite", spriteDirection)).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        }
    }

    public void changeDirectionAndMovement(DirectionState direction, MovementState movement) {
        String spriteDirection = direction.getDirectionString();
        String spriteMovement = movement.getMovementString();
        try {
            String path = String.format("set%s_%sSprite", spriteDirection, spriteMovement);
            this.getClass().getDeclaredMethod(path).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        }
    }

    private void setNORTH_MOVINGSprite() {
        this.setImage(northSprite);
    }

    private void setSOUTH_MOVINGSprite() {
        this.setImage(southSprite);
    }

    private void setWEST_MOVINGSprite() {
        this.setImage(westSprite);
    }

    private void setEAST_MOVINGSprite() {
        this.setImage(eastSprite);
    }

    private void setNORTH_STATIONARYSprite(){
        this.setImage(northStationarySprite);
    }
    private void setEAST_STATIONARYSprite(){
        this.setImage(eastStationarySprite);
    }
    private void setSOUTH_STATIONARYSprite(){
        this.setImage(southStationarySprite);
    }

    private void setWEST_STATIONARYSprite(){
        this.setImage(westStationarySprite);
    }

    private void setNORTH_ATTACKSprite(){
        this.setImage(northAttackSprite);
    }
    private void setEAST_ATTACKSprite(){
        this.setImage(eastAttackSprite);
    }
    private void setWEST_ATTACKSprite(){
        this.setImage(westAttackSprite);
    }
    private void setSOUTH_ATTACKSprite(){
        this.setImage(southAttackSprite);
    }

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
     * @return
     */
    public String getKey() {
        return entityName;
    }

    public void setCoordinate(List<Double> newCoordinates) {
        this.setX(newCoordinates.get(0));
        this.setY(newCoordinates.get(1));
    }
}

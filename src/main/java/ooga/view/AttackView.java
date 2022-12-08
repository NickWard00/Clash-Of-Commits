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



    /**
     * Creates a new AttackView with the given image path, attack type, x & y position, and size
     * @param imagePath: the image that the attack should be set to
     * @param xSize: width of attack
     * @param ySize: height of attack
     */
    public AttackView(String imagePath, String attackType, double xPos, double yPos, int xSize, int ySize, int attackID) {
        super(new Image(imagePath));
        this.attackType = attackType;
        this.attackID = attackID;
        this.setFitWidth(xSize);
        this.setFitHeight(ySize);
        this.setX(xPos - xSize/2);
        this.setY(yPos - ySize/2);
    }

    /**
     * Returns the attackType of the AttackView
     * @return attackType
     */
    public String getAttackType() {
        return attackType;
    }

    /**
     * Returns the attack ID of the AttackView
     * @return attackID
     */
    public int getKey() { return attackID; }
}

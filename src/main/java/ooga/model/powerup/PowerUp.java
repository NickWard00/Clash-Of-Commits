package ooga.model.powerup;

import ooga.model.entities.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public abstract class PowerUp {
    private static final ResourceBundle POWER_UP_BUNDLE = ResourceBundle.getBundle("ResourceBundles.PowerUp");
    private String powerUpType;
    private List<Integer> myCoordinates;
    private String imagePath;

    public PowerUp(String type, int x, int y) {
        this.powerUpType = type;
        this.myCoordinates = Arrays.asList(x,y);
        this.imagePath = POWER_UP_BUNDLE.getString(powerUpType);
    }

    public abstract void activatePowerUp(Entity entity);

    public List<Integer> getKey() {
        return myCoordinates;
    }

    public String getImagePath() { return imagePath; }

}

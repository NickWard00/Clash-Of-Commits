package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ooga.view.screens.SceneCreator;

/**
 * @author Melanie Wang
 */

/**
 * The HealthStatus is the health bar displayed in the HUD.
 */
public class HealthStatus extends HBox {
    int health;

    /**
     * Constructor for the HealthStatus class
     */
    public HealthStatus() {
        health = Integer.parseInt(SceneCreator.constants.getString("defaultHealth"));
        new ImageView(new Image(SceneCreator.images.getString("healthImage")));
        for (int i = 0; i < health; i++) {
            this.getChildren().add(new ImageView(new Image(SceneCreator.images.getString("healthImage"))));
        }
        this.getStyleClass().add("HealthStatus");
    }

    /**
     * Updates the health status of the player
     *
     * @param health
     */
    public void updateHealth(int health) {
        if (this.getChildren().size() != health) {
            this.getChildren().clear();
            for (int i = 0; i < health; i++) {
                this.getChildren().add(new ImageView(new Image(SceneCreator.images.getString("healthImage"))));
            }
        }
    }
}
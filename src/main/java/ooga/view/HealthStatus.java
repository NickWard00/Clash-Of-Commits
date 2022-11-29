package ooga.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ooga.view.screens.SceneCreator;

public class HealthStatus extends HBox {
    int health;

    public HealthStatus(){
        health = Integer.parseInt(SceneCreator.constants.getString("defaultHealth"));
        new ImageView(new Image(SceneCreator.images.getString("healthImage")));
        for (int i = 0; i < health; i++) {
            this.getChildren().add(new ImageView(new Image(SceneCreator.images.getString("healthImage"))));
        }
        this.setSpacing(5);

    }

    public void updateHealth(int health){
        this.getChildren().clear();
        for (int i = 0; i < health; i++) {
            this.getChildren().add(new ImageView(new Image(SceneCreator.images.getString("healthImage"))));
        }
        this.setSpacing(5);
    }

}

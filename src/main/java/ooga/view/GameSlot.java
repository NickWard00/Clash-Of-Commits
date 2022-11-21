package ooga.view;

import javafx.scene.control.Label;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
public class GameSlot extends Slot {
    private Label gameType;
    public GameSlot(String g, ResourceBundle labels) {
        super(labels);
        gameType = new Label(g);
        this.getChildren().add(gameType);
        this.getStyleClass().add("GameSlot");
        //this.getStylesheets().add(StartScreen.styles.getString("openNewGameCSS"));
    }
}

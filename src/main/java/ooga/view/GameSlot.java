package ooga.view;

import javafx.scene.control.Label;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
public class GameSlot extends Slot {
    private Label gameType;

    /**
     * The game slot is a slot for each game. There will always be three slots.
     * @param gameTypeString
     * @param labels
     */
    public GameSlot(String gameTypeString, ResourceBundle labels) {
        super(labels);
        this.gameType = new Label(gameTypeString);
        this.getChildren().add(gameType);
        this.getStyleClass().add("GameSlot");
    }
}

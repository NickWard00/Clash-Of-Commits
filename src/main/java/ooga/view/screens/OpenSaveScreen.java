package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.SaveSlot;
import ooga.view.screens.SceneCreator;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
//this screen is for when the player wishes to load a previously saved game.
public class OpenSaveScreen extends SceneCreator {
    private SaveSlot slot1;
    private SaveSlot slot2;
    private SaveSlot slot3;
    private Pane background;
    private ResourceBundle labels;
    private int screenSize;

    public OpenSaveScreen(ResourceBundle l){
        labels=l;
        this.screenSize = getScreenSize();
    }

    public Scene makeScene(){
        background = new Pane();
        slot1 = new SaveSlot(labels, 1);
        slot2 = new SaveSlot(labels, 2);
        slot3 = new SaveSlot(labels,3);
        VBox slots = new VBox(slot1,slot2,slot3);
        background.getChildren().add(slots);
        Scene s = new Scene(background, screenSize, screenSize);
        s.getStylesheets().add(styles.getString("saveCSS"));
        return s;
    }
}

package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.SaveSlot;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

/**
 * Screen that allows player to load a save
 */
public class OpenSaveScreen extends SceneCreator {
    private SaveSlot slot1;
    private SaveSlot slot2;
    private SaveSlot slot3;
    private Pane background;
    private ResourceBundle labels;
    private int screenSize;
    private Stage stage;

    /**
     * Constructor for open save screen
     * @param stage the stage the scene is based on
     * @param labels the labels that allows strings to be in different languages
     */
    public OpenSaveScreen(Stage stage, ResourceBundle labels){
        this.labels = labels;
        this.stage = stage;
        this.screenSize = getScreenSize();
    }

    /**
     * creates the scene object used to display the screen
     * @return scene
     */
    @Override
    public Scene makeScene(){
        background = new Pane();
        slot1 = new SaveSlot(labels, 1);
        slot2 = new SaveSlot(labels, 2);
        slot3 = new SaveSlot(labels,3);
        VBox slots = new VBox(slot1, slot2, slot3);
        background.getChildren().add(slots);
        Scene scene = new Scene(background, screenSize, screenSize);
        scene.getStylesheets().add(styles.getString("saveCSS"));
        handleEvents();
        return scene;
    }

    /**
     * handles the clicking of the slots
     */
    public void handleEvents(){
        slot1.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_1", slot1.getGameType(), labels);
            controller.startAnimation();
        });
        slot2.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_2", slot2.getGameType(), labels);
            controller.startAnimation();
        });
        slot3.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_3", slot3.getGameType(), labels);
            controller.startAnimation();
        });
    }
}

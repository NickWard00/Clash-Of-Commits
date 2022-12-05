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
public class OpenSaveScreen extends SceneCreator {
    private SaveSlot slot1;
    private SaveSlot slot2;
    private SaveSlot slot3;
    private Pane background;
    private ResourceBundle labels;
    private int screenSize;
    private Stage stage;

    public OpenSaveScreen(Stage stage, ResourceBundle labels){
        this.labels = labels;
        this.stage = stage;
        this.screenSize = getScreenSize();
    }

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

    //TODO: what the heck goes in as a map name when opening a save? (uncomment ^handleEvents() above to test)
    public void handleEvents(){
        slot1.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_1", "", labels);
            controller.startAnimation();
        });
        slot2.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_2", "", labels);
            controller.startAnimation();
        });
        slot3.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, "Save_3", "", labels);
            controller.startAnimation();
        });
    }
}

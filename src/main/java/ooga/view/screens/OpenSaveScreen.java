package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.SaveSlot;

import java.util.ArrayList;
import java.util.List;
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
    private SaveSlot slot4;
    private StackPane background;
    private ResourceBundle labels;
    private int screenSize;
    private Stage stage;
    private List<SaveSlot> slotList = new ArrayList<>();

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
        background = new StackPane();
        slot1 = new SaveSlot(labels, 1, false);
        slot2 = new SaveSlot(labels, 2, false);
        slot3 = new SaveSlot(labels,3,false);
        slot4 = new SaveSlot(labels, 4, true);
        VBox slots = new VBox(slot1, slot2, slot3, slot4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        slots.setId("slots");
        background.setAlignment(Pos.CENTER);
        background.getChildren().add(slots);
        Scene scene = new Scene(background, screenSize, screenSize);
        scene.getStylesheets().add(styles.getString("saveCSS"));
        handleEvents();
        return scene;
    }

    /**
     * handles the clicking of the slots
     */
    private void handleEvents(){
        for(SaveSlot s: slotList){
            s.setOnMouseClicked(event->{
                Controller controller = new Controller(stage, String.format("Save_%d", s.getNumber()), slot1.getGameType(), labels);
                controller.startAnimation();
            });
        }
        slot4.setOnMouseClicked(event->{
            Controller controller = new Controller(stage, String.format("Save_%d", slot4.getNumber()), slot4.getGameType(), labels);
            controller.startAnimation();
        });
    }
}

package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.controller.Controller;
import ooga.view.SaveSlot;

import java.util.ResourceBundle;

public class CreateSavePopup extends SceneCreator {
    private Controller myController;
    private Label saveGameText;
    private SaveSlot slot1;
    private SaveSlot slot2;
    private SaveSlot slot3;
    private ResourceBundle labels;
    private int popupSize = Integer.parseInt(constants.getString("popupSize"));

    public CreateSavePopup(Controller controller, ResourceBundle label){
        this.myController = controller;
        labels = label;
        saveGameText = new Label(labels.getString("saveGameText"));
    }

    @Override
    public Scene makeScene(){
        Pane savePane = new Pane();
        slot1 = new SaveSlot(labels, 1);
        slot2 = new SaveSlot(labels, 2);
        slot3 = new SaveSlot(labels, 3);
        VBox slotHolder = new VBox(saveGameText, slot1,slot2,slot3);
        savePane.getChildren().add(slotHolder);
        handleEvents();
        Scene scene = new Scene(savePane,popupSize, popupSize);
        scene.getStylesheets().add(styles.getString("saveCSS"));
        return scene;
    }

    private void handleEvents(){
        slot1.setOnMouseClicked(event->{
            myController.saveGame(slot1.getNumber());
        });
        slot2.setOnMouseClicked(event->{
            myController.saveGame(slot2.getNumber());
        });
        slot3.setOnMouseClicked(event->{
            myController.saveGame(slot3.getNumber());
        });
    }
}

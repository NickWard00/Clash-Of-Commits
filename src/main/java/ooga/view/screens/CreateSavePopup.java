package ooga.view.screens;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.controller.SaveFileParser;
import ooga.view.SaveSlot;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Melanie Wang
 */

/**
 * This popup allows users to save their game
 */
public class CreateSavePopup extends SceneCreator {
    private Controller myController;
    private Label saveGameText;
    private SaveSlot slot1;
    private SaveSlot slot2;
    private SaveSlot slot3;
    private SaveSlot slot4;
    private ResourceBundle labels;
    private Stage stage;

    private List<SaveSlot> slotList = new ArrayList<>();
    private int popupSize = Integer.parseInt(constants.getString("popupSize"));

    /**
     * Constructor for CreateSavePopup
     * @param controller the controller used to save the game
     * @param label language specific resourebundle
     */
    public CreateSavePopup(Controller controller, ResourceBundle label, Stage s){
        this.myController = controller;
        stage=s;
        labels = label;
        saveGameText = new Label(labels.getString("saveGameText"));
    }

    /**
     * creates the scene
     * @return scene
     */
    @Override
    public Scene makeScene(){
        StackPane savePane = new StackPane();
        slot1 = new SaveSlot(labels, 1);
        slot1.setId("slot1");
        slot2 = new SaveSlot(labels, 2);
        slot2.setId("slot2");
        slot3 = new SaveSlot(labels, 3);
        slot3.setId("slot3");
        slot4 = new SaveSlot(labels,4);
        slotList.add(slot1);
        slotList.add(slot2);
        slotList.add(slot3);
        VBox slotHolder = new VBox(saveGameText, slot1,slot2,slot3, slot4);
        savePane.getChildren().add(slotHolder);
        handleEvents();
        Scene scene = new Scene(savePane,popupSize, popupSize);
        scene.getStylesheets().add(styles.getString("saveCSS"));
        return scene;
    }

     //maps each slot to its specific save file, and saves game on click.
    private void handleEvents(){
        for(SaveSlot s:slotList){
            s.setOnMouseClicked(event->{
                myController.saveGame(s.getNumber());
                confirmSave();
            });
        }
        slot4.setOnMouseClicked(event ->{
            try {
                myController.saveGametoWeb(4);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

     // Opens a small popup that lets the user know the game's been saved.
    private void confirmSave(){
        Stage confirmation = new Stage();
        confirmation.initModality(Modality.APPLICATION_MODAL);
        confirmation.initOwner(stage);
        Label saveDone = new Label(labels.getString("saveConfirmation"));
        Scene confirmationScene= new Scene(saveDone);
        confirmation.setScene(confirmationScene);
        confirmation.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished( event -> confirmation.close() );
        delay.play();
    }
}

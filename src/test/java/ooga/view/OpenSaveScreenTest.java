package ooga.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.screens.CreateSavePopup;
import ooga.view.screens.OpenNewGameScreen;
import ooga.view.screens.OpenSaveScreen;
import ooga.view.screens.SettingsPopup;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Melanie Wang
 */

public class OpenSaveScreenTest extends DukeApplicationTest {
    ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");
    private Stage s;
    private Stage newStage;


    @Override
    public void start(Stage stage) {
        OpenNewGameScreen ongs = new OpenNewGameScreen(stage, labels);
        stage.setScene(ongs.makeScene());
        stage.show();
        s=stage;
        /*
        stage.close();
        OpenNewGameScreen ongs = new OpenNewGameScreen(stage, labels);
        stage.setScene(ongs.makeScene());
        stage.show();
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Button settings = (Button) hud.getItems().get(4);
        clickOn(settings);
        SettingsPopup sps = lookup("#settingsPopup").query();
        Button saveScreen = (Button) sps.getChildren().get(2);
        clickOn(saveScreen);
        SaveSlot slot1 = lookup("#slot1").query();
        clickOn(slot1);
        SaveSlot slot2 = lookup("#slot2").query();
        clickOn(slot2);
        SaveSlot slot3 = lookup("#slot3").query();
        clickOn(slot3);
        stage.close();
         */
    }

    @Test
    void testSave1Transition(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Button settings = (Button) hud.getItems().get(4);
        clickOn(settings);
        SettingsPopup sps = lookup("#settingsPopup").query();
        Button saveScreen = (Button) sps.getChildren().get(2);
        clickOn(saveScreen);
        SaveSlot slot1 = lookup("#slot1").query();
        clickOn(slot1);
        Platform.runLater((new Runnable() {
            @Override
                    public void run() {
                newStage= new Stage();
                OpenSaveScreen oss = new OpenSaveScreen(newStage, labels);
                s.close();
                newStage.setScene(oss.makeScene());
                newStage.show();
                VBox slots = lookup("#slots").query();
                SaveSlot firstSave = (SaveSlot) slots.getChildren().get(0);
                clickOn(firstSave);
                ToolBar hud = lookup("#HUD").query();
                Label score = (Label) hud.getItems().get(1);
                assertEquals("Score:  0", score.getText());
            }
                }));


    }

    @Test
    void testSave2Transition(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Button settings = (Button) hud.getItems().get(4);
        clickOn(settings);
        SettingsPopup sps = lookup("#settingsPopup").query();
        Button saveScreen = (Button) sps.getChildren().get(2);
        clickOn(saveScreen);
        SaveSlot slot2 = lookup("#slot2").query();
        clickOn(slot2);
        Platform.runLater((new Runnable() {
            @Override
            public void run() {
                OpenSaveScreen oss = new OpenSaveScreen(newStage, labels);
                s.close();
                newStage= new Stage();
                newStage.setScene(oss.makeScene());
                newStage.show();
                VBox slots = lookup("#slots").query();
                SaveSlot secondSave = (SaveSlot) slots.getChildren().get(1);
                clickOn(secondSave);
                //ToolBar hud = lookup("#HUD").query();
                Label score = (Label) hud.getItems().get(1);
                assertEquals("Score:  0", score.getText());
            }
        }));

    }
    @Test
    void testSave3Transition(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Button settings = (Button) hud.getItems().get(4);
        clickOn(settings);
        SettingsPopup sps = lookup("#settingsPopup").query();
        Button saveScreen = (Button) sps.getChildren().get(2);
        clickOn(saveScreen);
        SaveSlot slot3 = lookup("#slot3").query();
        clickOn(slot3);
        Platform.runLater((new Runnable() {
            @Override
            public void run() {
                OpenSaveScreen oss = new OpenSaveScreen(newStage, labels);
                s.close();
                newStage= new Stage();
                newStage.setScene(oss.makeScene());
                newStage.show();
                VBox slots = lookup("#slots").query();
                SaveSlot thirdSave = (SaveSlot) slots.getChildren().get(2);
                clickOn(thirdSave);
                //ToolBar hud = lookup("#HUD").query();
                Label score = (Label) hud.getItems().get(1);
                assertEquals("Score:  0", score.getText());
            }
        }));

    }
}

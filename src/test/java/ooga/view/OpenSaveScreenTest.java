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
import java.util.concurrent.TimeUnit;

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
    }

    @Test
    void testSave1Transition() {
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        // fake button
        clickOn(first, 0, 0);
        // new scene loaded
        ToolBar hud = lookup("#HUD").query();
        Button settings = (Button) hud.getItems().get(4);
        // real button
        clickOn(settings);
        SettingsPopup sps = lookup("#settingsPopup").query();
        Button saveScreen = (Button) sps.getChildren().get(2);
        // real button
        clickOn(saveScreen);
        SaveSlot slot1 = lookup("#slot1").query();
        // fake button
        clickOn(slot1, 0, 0);
        // wait for saved message to disappear
        sleep(3, TimeUnit.SECONDS);
        // check that the slot’s label now reflects the name of the saved game
        String gameType = "The Beginning";
        assertEquals(gameType, slot1.getGameType());
    }
}

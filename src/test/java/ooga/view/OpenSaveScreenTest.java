package ooga.view;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.screens.OpenNewGameScreen;
import ooga.view.screens.OpenSaveScreen;
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


    @Override
    public void start(Stage stage) {
        OpenSaveScreen oss = new OpenSaveScreen(stage, labels);
        stage.setScene(oss.makeScene());
        stage.show();
    }

    @Test
    void testGameType1Transition(){
        VBox slots = lookup("#slots").query();
        SaveSlot first = (SaveSlot) slots.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Label score = (Label) hud.getItems().get(1);
        assertEquals("Score:  0", score.getText());
    }
}

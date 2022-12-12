package ooga.view;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.screens.ChooseGameScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Melanie Wang
 */
public class ChooseGameScreenTest extends DukeApplicationTest{
    ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");


    @Override
    public void start(Stage stage) {
        ChooseGameScreen cgs = new ChooseGameScreen(stage, labels);
        stage.setScene(cgs.makeScene());
        stage.show();
    }

    @Test
    void testNewGameButton() {
        Button newGame = lookup("#newGame").query();
        clickOn(newGame);
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        Label gameType = (Label) first.getChildren().get(0);
        assertEquals(gameType.getText(), "The Beginning");
    }

    @Test
    void testLoadSaveButton() {
        Button loadSave = lookup("#loadSave").query();
        clickOn(loadSave);
        VBox slots = lookup("#slots").query();

        assertEquals(slots.getChildren().size(), 4);
    }
}

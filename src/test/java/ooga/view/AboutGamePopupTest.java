package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.screens.AboutGamePopup;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.OpenNewGameScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Melanie Wang
 */
public class AboutGamePopupTest extends DukeApplicationTest {
    ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");
    private Button about;

    @Override
    public void start(Stage stage) {
        OpenNewGameScreen ongs = new OpenNewGameScreen(stage, labels);
        stage.setScene(ongs.makeScene());
        stage.show();

    }

    @Test
    void testAboutPopup(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(0);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        about = (Button) hud.getItems().get(2);
        clickOn(about);
        AboutGamePopup a = lookup("#aboutPopup").query();
        Label lab = (Label)a.getChildren().get(0);
        assertEquals(lab.getText(), labels.getString("placeholder"));
    }
}

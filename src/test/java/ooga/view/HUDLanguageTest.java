package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.view.screens.AboutGamePopup;
import ooga.view.screens.OpenNewGameScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Nick Ward
 */
public class HUDLanguageTest extends DukeApplicationTest {
    ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle_de");

    ResourceBundle images = ResourceBundle.getBundle("ResourceBundles.images");
    private Button about;

    private ImageView pause;


    @Override
    public void start(Stage stage) {
        OpenNewGameScreen ongs = new OpenNewGameScreen(stage, labels);
        stage.setScene(ongs.makeScene());
        stage.show();
        pause = new ImageView(new Image(images.getString("pauseImage")));
    }

    @Test
    void testAboutPopup(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(1);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        about = (Button) hud.getItems().get(2);
        clickOn(about);
        AboutGamePopup a = lookup("#aboutPopup").query();
        Label lab = (Label)a.getChildren().get(0);
        assertEquals(lab.getText(), labels.getString("placeholder"));
    }

    @Test
    void testPausePlayButton(){
        VBox slotBox = lookup("#slotbox").query();
        GameSlot first = (GameSlot) slotBox.getChildren().get(1);
        clickOn(first);
        ToolBar hud = lookup("#HUD").query();
        Button playPause = (Button) hud.getItems().get(3);
        clickOn(playPause);
        assertNotEquals(playPause.getGraphic(), pause);

    }
}

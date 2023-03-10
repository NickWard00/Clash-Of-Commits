package ooga.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.ScreenSelector;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

public class ViewTest extends DukeApplicationTest {
    private View view;
    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        setupView(stage);
    }

    void setupView(Stage stage) {
        ResourceBundle label = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
        Controller controller = new Controller(stage, "MainMap", label);
        view = new View(stage, controller, label);
    }

    @Test
    void changeEntityStateTest() {
        String entityName = "Hero1";
        DirectionState direction = DirectionState.NORTH;
        view.changeEntityState(entityName, direction);
    }
}

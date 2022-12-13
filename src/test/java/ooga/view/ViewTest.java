package ooga.view;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

/**
 * @author Nick Ward, Nicki Lee, Mayari Merchant
 */
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
        Controller controller = new Controller(stage, "MainMap","", label);
        view = new View(stage, controller, "", label);
    }

    @Test
    void changeEntityStateTest() {
        String entityName = "Hero1";
        DirectionState direction = DirectionState.NORTH;
        MovementState movement = MovementState.MOVING;
        view.changeEntityState(entityName, direction, movement);
    }
}

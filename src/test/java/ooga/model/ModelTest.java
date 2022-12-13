package ooga.model;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest extends DukeApplicationTest {
    private Model myModel;
    private Controller myController;

    @Override
    public void start(Stage stage) {
        myController = new Controller(stage, "MainMap", "", ResourceBundle.getBundle("ResourceBundles.LabelsBundle"));
        myModel = new Model(myController);
    }

    @Test
    void changeEntityStateTest() {
        myModel.changeEntityState("Hero1", DirectionState.NORTH, MovementState.MOVING);
        assertEquals(myController.getModelEntities().get("Hero1").getMyDirection(), DirectionState.NORTH);
    }

    @Test
    void modelAttackTest() {
        myModel.attack();
        assertTrue(myController.getModelEntities().get("Hero1").getTimeUntilAttack() > 0);
    }
}

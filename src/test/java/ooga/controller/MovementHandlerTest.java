package ooga.controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.model.entities.Entity;
import ooga.view.MapWrapper;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import java.util.*;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class MovementHandlerTest extends DukeApplicationTest {
    private static final Map<KeyCode, String> movementActions = Map.of(
            KeyCode.UP, "moveUp",
            KeyCode.DOWN, "moveDown",
            KeyCode.RIGHT, "moveRight",
            KeyCode.LEFT, "moveLeft",
            KeyCode.W, "moveUp",
            KeyCode.S, "moveDown",
            KeyCode.D, "moveRight",
            KeyCode.A, "moveLeft",
            KeyCode.SHIFT, "sprint"
    );
    private static final Map<KeyCode, String> attackActions = Map.of(
            KeyCode.SPACE, "attack",
            KeyCode.Z, "attack",
            KeyCode.CONTROL, "control",
            KeyCode.X, "crossAttack"
    );
    private static final Map<KeyCode, String> cheatCodeActions = Map.of(
            KeyCode.B, "block",
            KeyCode.P, "pause",
            KeyCode.Q, "quit",
            KeyCode.L, "life",
            KeyCode.O, "forceField",
            KeyCode.DIGIT2, "doubleScore"
    );
    private Controller controller;
    private MovementHandler movementHandler;
    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        setupController(stage);
    }

    void setupController(Stage stage) {
        controller = new Controller(stage, "MainMap","", ResourceBundle.getBundle("ResourceBundles.LabelsBundle"));
        movementHandler = controller.getMovementHandler();
    }

    @Test
    void handleKeyRelease() {
        for(KeyCode key : movementActions.keySet()){
            controller.checkKeyRelease(key);
            assertFalse(movementHandler.isMoving());
        }
    }

    @Test
    void handleKeyPress() {
        for(KeyCode key : movementActions.keySet()){
            controller.checkKeyPress(key);
            assertTrue(movementHandler.isMoving());
        }
    }

    @Test
    void moveDownStop() {
        if(movementHandler.isMoving()){
            movementHandler.moveDownStop();
            assertFalse(movementHandler.isMoving());
        }
    }

    @Test
    void moveLeftStop() {
        if(movementHandler.isMoving()){
            movementHandler.moveLeftStop();
            assertFalse(movementHandler.isMoving());
        }
    }

    @Test
    void moveRightStop() {
        if(movementHandler.isMoving()){
            movementHandler.moveRightStop();
            assertFalse(movementHandler.isMoving());
        }
    }

    @Test
    void moveUpStop() {
        if(movementHandler.isMoving()){
            movementHandler.moveUpStop();
            assertFalse(movementHandler.isMoving());
        }
    }

    @Test
    void moveUp() {
        if(!movementHandler.isMoving()){
            movementHandler.moveUp();
            assertTrue(movementHandler.isMoving());
        }
    }

    @Test
    void moveDown() {
        if(!movementHandler.isMoving()){
            movementHandler.moveDown();
            assertTrue(movementHandler.isMoving());
        }
    }

    @Test
    void moveLeft() {
        if(!movementHandler.isMoving()){
            movementHandler.moveLeft();
            assertTrue(movementHandler.isMoving());
        }
    }

    @Test
    void moveRight() {
        if(!movementHandler.isMoving()){
            movementHandler.moveRight();
            assertTrue(movementHandler.isMoving());
        }
    }

    @Test
    void sprint() {
        //should only be moving if "sprint" WHILE
        //already moving
        movementHandler.moveRight();
        movementHandler.sprint();
        assertTrue(movementHandler.isMoving());
        movementHandler.moveRightStop();
        movementHandler.sprint();
        assertFalse(movementHandler.isMoving());
    }

    @Test
    void sprintStop() {
        //should only stop MOVING if
        //not moving in a direction at
        //regular speed to begin with
        movementHandler.moveRight();
        movementHandler.sprint();
        movementHandler.sprintStop();
        assertTrue(movementHandler.isMoving());
        movementHandler.moveLeft();
        movementHandler.sprint();
        movementHandler.moveLeftStop();
        assertFalse(movementHandler.isMoving());
    }
}
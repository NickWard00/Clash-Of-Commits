package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MovementHandler {
    private Map<KeyCode, String> movementActions;
    private Map<KeyCode, String> attackActions;
    private Map<KeyCode, String> cheatCodeActions;

    public MovementHandler(){
        this.movementActions = Map.of(
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
        this.attackActions = Map.of(
                KeyCode.SPACE, "attack",
                KeyCode.Z, "attack",
                KeyCode.CONTROL, "control",
                KeyCode.X, "crossAttack"
        );
        this.cheatCodeActions = Map.of(
                KeyCode.B, "block",
                KeyCode.P, "pause",
                KeyCode.Q, "quit",
                KeyCode.L, "life",
                KeyCode.O, "forceField",
                KeyCode.DIGIT2, "doubleScore"
        );
    }



}

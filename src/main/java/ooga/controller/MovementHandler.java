package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Mayari Merchant, Nick Ward, Nicki Lee, Melanie Wang
 */
public class MovementHandler {
    private Controller controller;
    private String myMainHeroName;
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
    private boolean moving;

    public MovementHandler(Controller controller, String mainHeroName){
        this.controller = controller;
        myMainHeroName = mainHeroName;
    }
    /**
     * Handles the key input release from the user that is detected in the view
     * @param action
     */
    public void handleKeyRelease(String action) throws IllegalStateException {
        try {
            Method currentAction = this.getClass().getDeclaredMethod(action + "Stop");
            currentAction.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("illegalKeyPress", e);
        }
    }
    /**
     * Handles the key input press from the user that is detected in the view
     * @param action
     */
    public void handleKeyPress(String action) throws IllegalStateException {
        try {
            Method currentAction = this.getClass().getDeclaredMethod(action);
            currentAction.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("noMethodFound", e);
        }
    }

    private void attackStop(){
        controller.changeEntityState(myMainHeroName, MovementState.STATIONARY);
    }
    private void attack(){
        controller.attack();
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the south direction
     */
    public void moveDownStop(){
        moving = false;
        controller.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the west direction
     */
    public void moveLeftStop(){
        moving = false;
        controller.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the east direction
     */
    public void moveRightStop(){
        moving = false;
        controller.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.STATIONARY);
    }
    public void moveUpStop(){
        moving = false;
        controller.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the north direction
     */
    public void moveUp() {
        moving = true;
        controller.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the south direction
     */
    public void moveDown() {
        moving = true;
        controller.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the west direction
     */
    public void moveLeft(){
        moving = true;
        controller.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the east direction
     */
    public void moveRight(){
        moving = true;
        controller.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move allow the player to sprint
     */
    public void sprint(){
        controller.changeEntityState(myMainHeroName, MovementState.SPRINTING);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from sprinting
     */
    public void sprintStop(){
        MovementState nextMovement = moving ? MovementState.MOVING : MovementState.STATIONARY;
        controller.changeEntityState(myMainHeroName, nextMovement);
    }

    private void freeze(){

    }

    private void crossAttack(){

    }

    private void pause(){
        controller.playPause();
    }
    private void pauseStop(){

    }

    private void quit(){

    }

    private void block(){

    }

    private void forceField(){

    }

    private void addLife(){

    }

    private void doubleScore(){

    }

    private void doubleAttack(){

    }



}

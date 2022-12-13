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

    public static final Map<KeyCode, String> MOVEMENT_ACTIONS = Map.of(
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
    public static final Map<KeyCode, String> ATTACK_ACTIONS = Map.of(
            KeyCode.SPACE, "attack",
            KeyCode.Z, "attack",
            KeyCode.X, "crossAttack"
    );
    public static final Map<KeyCode, String> CHEAT_CODE_ACTIONS = Map.of(
            KeyCode.B, "block",
            KeyCode.P, "playPause",
            KeyCode.Q, "quit",
            KeyCode.L, "life",
            KeyCode.O, "forceField",
            KeyCode.DIGIT2, "doubleScore",
            KeyCode.NUMPAD2, "doubleScore",
            KeyCode.CONTROL, "control"
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
    private void crossAttackStop() {

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
        /*moveLeftStop();
        attack();
        moveUpStop();
        attack();
        moveRightStop();
        attack();
        moveDownStop();
        attack();*/

        controller.crossAttack();
    }

    private void playPause(){
        controller.playPause();
    }


    public void quit(){
        controller.quitToTitle();
    }

    public void block(){

    }

    public void forceField(){

    }

    public void life(){
        controller.addLife();
    }

    public void doubleScore(){
        controller.doubleScore();
    }

    public void doubleAttack(){

    }
    public boolean isMoving(){
        return moving;
    }



}

package ooga.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.EntityView;
import ooga.model.Entity;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class View {
    private Scene myScene;
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;
    private Map<String, Entity> myModelEntities;

    private Map<KeyCode, String> actions = Map.of(
            KeyCode.UP, "moveUp",
            KeyCode.DOWN, "moveDown",
            KeyCode.RIGHT, "moveRight",
            KeyCode.LEFT, "moveLeft"
    );

    public View(Stage stage, Controller controller){
        this.stage = stage;
        this.myController = controller;
        setupGame(stage);
    }
    public void step(double elapsedTime){
        myModelEntities.get("Hero1").move(elapsedTime);
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myModelEntities = myController.getModelEntities();
        MainGameScreen mainGameScreen = new MainGameScreen();
        mainGameScreen.startGamePlay(myController.getMapWrapper(), myViewEntities);
        myScene = mainGameScreen.makeScene();
        handleKeyInputs();
        stage.setScene(myScene);
    }

    private void changeScene(String sceneName){
        ScreenSelector screenSelector = new ScreenSelector(stage);
        screenSelector.selectScreen(sceneName);
    }

    private void handleKeyInputs(){
        myScene.setOnKeyPressed(event ->{
            try {
                Method currentAction = this.getClass().getDeclaredMethod(
                        actions.get(event.getCode()));
                currentAction.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        myScene.setOnKeyReleased(event ->{
            myModelEntities.get("Hero1").changeMovement(MovementState.STATIONARY);
        });
    }

    private void moveUp() {
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.NORTH);
        myViewEntities.get("Hero1").changeDirection(DirectionState.NORTH);
    }

    private void moveDown() {
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
        myViewEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
    }

    private void moveLeft(){
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.WEST);
        myViewEntities.get("Hero1").changeDirection(DirectionState.WEST);
    }
    private void moveRight(){
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.EAST);
        myViewEntities.get("Hero1").changeDirection(DirectionState.EAST);
    }

}

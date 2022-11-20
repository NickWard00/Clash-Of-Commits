package ooga.view;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.Entity;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class View {
    private Scene myScene;
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;
    private MapWrapper myMapWrapper;
    private double myWidth;
    private double myHeight;
    private double blockSize;
    private EntityView myHeroView;
    private Entity myHeroModel;
    private StackPane s;

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
        myHeroModel.move(elapsedTime);
        s.setTranslateX((myScene.getWidth() - blockSize) / 2 - myHeroView.getX());
        s.setTranslateY((myScene.getHeight() - blockSize) / 2 - myHeroView.getY());
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        pickControllableEntity(myController.getMainHeroName());
        MainGameScreen mainGameScreen = new MainGameScreen();
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
        mainGameScreen.startGamePlay(myMapWrapper, myViewEntities);
        myScene = mainGameScreen.makeScene();
        handleKeyInputs();
        stage.setScene(myScene);
        createScrollableBackground();
    }

    private void pickControllableEntity(String entityName) {
        myHeroModel = myController.getModelEntities().get(entityName);
        myHeroView = myViewEntities.get(entityName);
    }

    private void createScrollableBackground() {
        BorderPane b = (BorderPane) myScene.getRoot();
        s = (StackPane) b.getChildren().get(0);
        s.setMinHeight(myHeight);
        s.setMinWidth(myWidth);
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
            try {
                Method currentAction = this.getClass().getDeclaredMethod(
                    actions.get(event.getCode())+"Stop");
                currentAction.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void moveUpStop(){
        myHeroModel.changeDirection(DirectionState.NORTH_STATIONARY);
        myHeroView.changeDirection(DirectionState.NORTH_STATIONARY);
    }
    private void moveDownStop(){
        myHeroModel.changeDirection(DirectionState.SOUTH_STATIONARY);
        myHeroView.changeDirection(DirectionState.SOUTH_STATIONARY);
    }
    private void moveRightStop(){
        myHeroModel.changeDirection(DirectionState.EAST_STATIONARY);
        myHeroView.changeDirection(DirectionState.EAST_STATIONARY);
    }

    private void moveLeftStop(){
        myHeroModel.changeDirection(DirectionState.WEST_STATIONARY);
        myHeroView.changeDirection(DirectionState.WEST_STATIONARY);
    }

    private void moveUp() {
        myHeroModel.changeMovement(MovementState.MOVING);
        myHeroModel.changeDirection(DirectionState.NORTH);
        myHeroView.changeDirection(DirectionState.NORTH);
    }

    private void moveDown() {
        myHeroModel.changeMovement(MovementState.MOVING);
        myHeroModel.changeDirection(DirectionState.SOUTH);
        myHeroView.changeDirection(DirectionState.SOUTH);
    }

    private void moveLeft(){
        myHeroModel.changeMovement(MovementState.MOVING);
        myHeroModel.changeDirection(DirectionState.WEST);
        myHeroView.changeDirection(DirectionState.WEST);
    }
    private void moveRight(){
        myHeroModel.changeMovement(MovementState.MOVING);
        myHeroModel.changeDirection(DirectionState.EAST);
        myHeroView.changeDirection(DirectionState.EAST);
    }

}

package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.EntityView;
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
    private Map<String, Entity> myModelEntities;
    private MapWrapper myMapWrapper;
    private double myWidth;
    private double myHeight;
    private double blockSize;
    private double startX;
    private double startY;
    private Entity myHeroModel;
    private EntityView myHeroView;
    private double myHeroSpeed;
    private ScrollPane bg;
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
        s.setTranslateX((myScene.getWidth() - blockSize) / 2 - myHeroModel.coordinates().get(0));
        s.setTranslateY((myScene.getHeight() - blockSize) / 2 - myHeroModel.coordinates().get(1));
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myModelEntities = myController.getModelEntities();
        myHeroModel = myModelEntities.get("Hero1");
        myHeroView = myViewEntities.get("Hero1");
        myHeroSpeed = Double.parseDouble(myHeroModel.getMyAttributes().get("Speed"));
        MainGameScreen mainGameScreen = new MainGameScreen();
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
        startX = myHeroModel.coordinates().get(0);
        startY = myHeroModel.coordinates().get(1);
        mainGameScreen.startGamePlay(myMapWrapper, myViewEntities);
        myScene = mainGameScreen.makeScene();
        handleKeyInputs();
        stage.setScene(myScene);
        createScrollableBackground();
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
        myModelEntities.get("Hero1").changeDirection(DirectionState.NORTH_STATIONARY);
        myViewEntities.get("Hero1").changeDirection(DirectionState.NORTH_STATIONARY);
    }
    private void moveDownStop(){
        myModelEntities.get("Hero1").changeDirection(DirectionState.SOUTH_STATIONARY);
        myViewEntities.get("Hero1").changeDirection(DirectionState.SOUTH_STATIONARY);
    }
    private void moveRightStop(){
        myModelEntities.get("Hero1").changeDirection(DirectionState.EAST_STATIONARY);
        myViewEntities.get("Hero1").changeDirection(DirectionState.EAST_STATIONARY);
    }

    private void moveLeftStop(){
        myModelEntities.get("Hero1").changeDirection(DirectionState.WEST_STATIONARY);
        myViewEntities.get("Hero1").changeDirection(DirectionState.WEST_STATIONARY);
    }

    private void moveUp() {
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.NORTH);
        myViewEntities.get("Hero1").changeDirection(DirectionState.NORTH);
        //bg.setVvalue(bg.getVvalue() - );
    }

    private void moveDown() {
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
        myViewEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
        //bg.setVvalue(bg.getVvalue() + myHeroSpeed*2);
    }

    private void moveLeft(){
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.WEST);
        myViewEntities.get("Hero1").changeDirection(DirectionState.WEST);
        //bg.setHvalue(bg.getHvalue() - myHeroSpeed);
    }
    private void moveRight(){
        myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
        myModelEntities.get("Hero1").changeDirection(DirectionState.EAST);
        myViewEntities.get("Hero1").changeDirection(DirectionState.EAST);
        //bg.setHvalue(bg.getHvalue() + myHeroSpeed);
    }

}

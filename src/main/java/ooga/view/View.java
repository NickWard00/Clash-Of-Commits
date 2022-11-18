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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class View {
    private Scene myScene;
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;
    private Map<String, Entity> myModelEntities;

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
        myScene.setOnKeyPressed(e->getKeyInput(e.getCode()));
        stage.setScene(myScene);
    }

    private void changeScene(String sceneName){
        ScreenSelector screenSelector = new ScreenSelector(stage);
        screenSelector.selectScreen(sceneName);
    }

    private void getKeyInput(KeyCode key){
        if (key == KeyCode.UP){
            myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
            myModelEntities.get("Hero1").changeDirection(DirectionState.NORTH);
            myViewEntities.get("Hero1").changeDirection(DirectionState.NORTH);
        }
        else if (key == KeyCode.DOWN){
            myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
            myModelEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
            myViewEntities.get("Hero1").changeDirection(DirectionState.SOUTH);
        }
        else if (key == KeyCode.LEFT){
            myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
            myModelEntities.get("Hero1").changeDirection(DirectionState.WEST);
            myViewEntities.get("Hero1").changeDirection(DirectionState.WEST);
        }
        else if (key == KeyCode.RIGHT){
            myModelEntities.get("Hero1").changeMovement(MovementState.MOVING);
            myModelEntities.get("Hero1").changeDirection(DirectionState.EAST);
            myViewEntities.get("Hero1").changeDirection(DirectionState.EAST);
        }

    }

}

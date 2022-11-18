package ooga.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.EntityView;
import ooga.model.Entity;
import ooga.view.screens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class View {
    private Scene myScene;
    private BorderPane root;
    private Stage stage;
    private MapView mapView;
    private StartScreen startScreen;
    private Controller myController;

    public View(Stage stage, Controller controller){
        this.stage = stage;
        this.myController = controller;
        setupGame(stage);
    }
    public void step(double elapsedTime){

    }

    private void setupGame(Stage stage){
        MainGameScreen mainGameScreen = new MainGameScreen();
        mainGameScreen.startGamePlay(myController.getMapWrapper(), myController.getEntityViewList());
        myScene = mainGameScreen.makeScene();
        myScene.setOnKeyPressed(e->getKeyInput(e.getCode()));
        stage.setScene(myScene);
    }

    private void changeScene(String sceneName){
        ScreenSelector screenSelector = new ScreenSelector(stage);
        screenSelector.selectScreen(sceneName);
    }

    private void getKeyInput(KeyCode key){
        switch(key){
            case LEFT -> {
                mapView.moveRight();
            }
            case RIGHT -> {
                mapView.moveLeft();
            }
            case DOWN -> {
                mapView.moveUp();
            }
            case UP -> {
                mapView.moveDown();
            }
        }
    }

}

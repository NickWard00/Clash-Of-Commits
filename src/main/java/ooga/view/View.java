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
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;

    public View(Stage stage, Controller controller){
        this.stage = stage;
        this.myController = controller;
        setupGame(stage);
    }
    public void step(double elapsedTime){

    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
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
        switch(key){
            case LEFT -> {
            }
            case RIGHT -> {
            }
            case DOWN -> {
            }
            case UP -> {
            }
        }
    }

}

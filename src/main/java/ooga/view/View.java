package ooga.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.view.screens.*;

import java.util.Map;

/**
 * @author Melanie Wang, Nick Ward, Mayari Merchant
 */
public class View {
    private Scene myScene;
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;
    private MapWrapper myMapWrapper;
    private double myWidth;
    private double myHeight;
    private double blockSize;
    private StackPane s;
    private EntityView myHeroView;

    public View(Stage stage, Controller controller){
        this.stage = stage;
        this.myController = controller;
        setupGame(stage);
    }
    public void step(double elapsedTime){
        s.setTranslateX((myScene.getWidth() - blockSize) / 2 - myHeroView.getX());
        s.setTranslateY((myScene.getHeight() - blockSize) / 2 - myHeroView.getY());
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myHeroView = myViewEntities.get(myController.getMainHeroName());

        setupMap();

        MainGameScreen mainGameScreen = new MainGameScreen();
        mainGameScreen.startGamePlay(myMapWrapper, myViewEntities);
        myScene = mainGameScreen.makeScene();
        handleKeyInputs();
        stage.setScene(myScene);
        createScrollableBackground();
    }

    public void changeEntityState(String entityName, DirectionState direction) {
        EntityView entity = myViewEntities.get(entityName);
        entity.changeDirection(direction);
    }

    private void setupMap() {
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
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

    private void handleKeyInputs() {
        myScene.setOnKeyPressed(event -> {
            myController.handleKeyPress(event.getCode());
        });
        myScene.setOnKeyReleased(event -> {
            myController.handleKeyRelease(event.getCode());
        });
    }
}

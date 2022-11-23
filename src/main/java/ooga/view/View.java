package ooga.view;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.*;

import java.util.Map;
import java.util.ResourceBundle;

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
    private BorderPane bPane;
    private StackPane stackPane;
    private EntityView myHeroView;
    private MainGameScreen mainGameScreen;
    private boolean isActive;
    private MediaPlayer walking;
    private ResourceBundle labels;

    public View(Stage stage, Controller controller, ResourceBundle label){
        this.isActive = false;
        this.stage = stage;
        this.myController = controller;
        setupGame(stage);
        labels = label;
    }
    
    public void step(double elapsedTime){
        stackPane.setTranslateX((myScene.getWidth() - blockSize) / 2 - myHeroView.getX());
        stackPane.setTranslateY((myScene.getHeight() - blockSize) / 2 - myHeroView.getY());
        if (isActive == true) {
            mainGameScreen.detectCollisions(myController);
        }
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myHeroView = myViewEntities.get(myController.getMainHeroName());

        setupMap();

        mainGameScreen = new MainGameScreen(stage);
        mainGameScreen.startGamePlay(myMapWrapper, myViewEntities);
        myScene = mainGameScreen.makeScene();
        walking = mainGameScreen.getWalkPlayer();
        walking.setOnEndOfMedia(new Runnable() {
            public void run() {
                walking.seek(Duration.ZERO);
            }});
        handleKeyInputs();
        stage.setScene(myScene);
        createScrollableBackground();
        isActive = true;
    }

    public void changeEntityState(String entityName, DirectionState direction, MovementState movement) {
        EntityView entity = myViewEntities.get(entityName);
        entity.changeDirectionAndMovement(direction, movement);
    }

    private void setupMap() {
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
    }

    private void createScrollableBackground() {
        bPane = (BorderPane) myScene.getRoot();
        stackPane = (StackPane) bPane.getChildren().get(0);
        stackPane.setMinHeight(myHeight);
        stackPane.setMinWidth(myWidth);
    }

    private void changeScene(String sceneName){
        ScreenSelector screenSelector = new ScreenSelector(stage, labels);
        screenSelector.selectScreen(sceneName);
    }

    private void handleKeyInputs() {
        myScene.setOnKeyPressed(event -> {
            myController.handleKeyPress(event.getCode());
            walking.play();
        });
        myScene.setOnKeyReleased(event -> {
            myController.handleKeyRelease(event.getCode());
            walking.pause();
        });
    }

    public MainGameScreen getGameScreen() {
        return mainGameScreen;
    }
}


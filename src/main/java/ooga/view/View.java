package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.CollisionHandler;
import ooga.controller.Controller;
import ooga.model.Entity;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.*;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * @author Melanie Wang, Nick Ward, Mayari Merchant
 */
public class View {
    private Scene myScene;
    private Stage stage;
    private Controller myController;
    private Map<String, EntityView> myViewEntities;
    private MapWrapper myMapWrapper;
    private MapView myMapView;
    private Map<List<Double>, BlockView> myViewObstacles;
    private Map<Integer, AttackView> myViewAttacks;
    private double myWidth;
    private double myHeight;
    private double blockSize;
    private BorderPane bPane;
    private StackPane stackPane;
    private GridPane mapPane;
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
            detectCollisions();
        }
    }

    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myViewAttacks = myController.getViewAttacks();
        myHeroView = myViewEntities.get(myController.getMainHeroName());

        setupMap();

        mainGameScreen = new MainGameScreen(stage, myController);
        mainGameScreen.startGamePlay(mapPane, myViewEntities);
        myScene = mainGameScreen.makeScene();
        setupWalkingMusic();

        handleKeyInputs();

        stage.setScene(myScene);
        stage.setTitle("Adventure Mode");
        stage.getIcons().add(new Image("sprites/hero/SOUTH_STATIONARY.GIF"));

        createScrollableBackground();

        this.isActive = true;
    }

    private void setupWalkingMusic() {
        walking = mainGameScreen.getWalkPlayer();
        walking.setOnEndOfMedia(new Runnable() {
            public void run() {
                walking.seek(Duration.ZERO);
            }});
    }

    public void changeEntityState(String entityName, DirectionState direction, MovementState movement) {
        EntityView entity = myViewEntities.get(entityName);
        entity.changeDirectionAndMovement(direction, movement);
    }
    public void changeEntityState(String entityName, MovementState movement){
        EntityView entity = myViewEntities.get(entityName);
        entity.changeMovement(movement);
    }

    private void setupMap() {
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
        myMapView = new MapView(myMapWrapper);
        mapPane = myMapView.createMap();
        myViewObstacles = myMapView.getViewObstacles();
    }

    private void createScrollableBackground() {
        bPane = (BorderPane) myScene.getRoot();
        StackPane centerConsolidated = (StackPane) bPane.getChildren().get(0);
        stackPane = (StackPane) centerConsolidated.getChildren().get(0);
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

    private void detectCollisions() {
        List<EntityView> myViewEntitiesList = new ArrayList<>(myViewEntities.values());
        for (EntityView entity : myViewEntitiesList) {
            List<AttackView> myViewAttackList = new ArrayList<>(myViewAttacks.values());
            for (AttackView attack : myViewAttackList) {
                if (entity.localToScreen(entity.getBoundsInLocal()).intersects(attack.localToScreen(attack.getBoundsInLocal()))) {
                    myController.passCollision(entity, attack);
                }
            }
            List<BlockView> myViewObstacleList = new ArrayList<>(myViewObstacles.values());
            for (BlockView obstacle : myViewObstacleList) {
                if (entity.localToScreen(entity.getBoundsInLocal()).intersects(obstacle.getImageView().localToScreen(obstacle.getImageView().getBoundsInLocal()))) {
                    myController.passCollision(entity, obstacle);
                }
            }
        }
        List<AttackView> myViewAttackList = new ArrayList<>(myViewAttacks.values());
        for (AttackView attack : myViewAttackList) {
            List<BlockView> myViewObstacleList = new ArrayList<>(myViewObstacles.values());
            for (BlockView obstacle : myViewObstacleList) {
                if (attack.localToScreen(attack.getBoundsInLocal()).intersects(obstacle.getImageView().localToScreen(obstacle.getImageView().getBoundsInLocal()))) {
                    myController.passCollision(attack, obstacle);
                    break;
                }
            }
        }
    }

    public MainGameScreen getGameScreen() {
        return mainGameScreen;
    }

    public Map<List<Double>, BlockView> getViewObstacles() {
        return myViewObstacles;
    }

}


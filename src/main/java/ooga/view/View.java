package ooga.view;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.MainGameScreen;

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
    private GridPane backgroundPane;
    private GridPane mapPane;
    private EntityView myHeroView;
    private MainGameScreen mainGameScreen;
    private boolean isActive;
    private MediaPlayer walking;
    private ResourceBundle labels;
    private String myGameType;

    /**
     * Constructor for the View class
     * @param stage the stage to be displayed
     * @param controller the controller that this view is associated with
     * @param label the resource bundle for the labels
     */
    public View(Stage stage, Controller controller, String gameType, ResourceBundle label){
        this.isActive = false;
        this.stage = stage;
        this.myController = controller;
        this.myGameType = gameType;
        this.labels = label;
        setupGame(stage);
    }

    /**
     * The step function for the view that moves the stackpane and checks for collisions
     * @param elapsedTime the time elapsed
     */
    public void step(double elapsedTime){
        stackPane.setTranslateX((myScene.getWidth() - blockSize) / 2 - myHeroView.getX());
        stackPane.setTranslateY((myScene.getHeight() - blockSize) / 2 - myHeroView.getY());
        if (isActive) {
            detectCollisions();
        }
    }

    /**
     * Method to set up the view for a new game
     * @param stage
     */
    private void setupGame(Stage stage){
        myViewEntities = myController.getViewEntities();
        myViewAttacks = myController.getViewAttacks();
        myHeroView = myViewEntities.get(myController.getMainHeroName());

        setupMap();

        mainGameScreen = new MainGameScreen(stage, myController);
        mainGameScreen.startGamePlay(backgroundPane, mapPane, myViewEntities);
        myScene = mainGameScreen.makeScene();
        setupWalkingMusic();

        handleKeyInputs();

        stage.setScene(myScene);
        stage.setTitle(myGameType);
        stage.getIcons().add(new Image("sprites/hero/SOUTH_STATIONARY.GIF"));

        createScrollableBackground();

        this.isActive = true;
    }

    /**
     * Sets up the walking music for a player
     */
    private void setupWalkingMusic() {
        walking = mainGameScreen.getWalkPlayer();
        walking.setOnEndOfMedia(new Runnable() {
            public void run() {
                walking.seek(Duration.ZERO);
            }});
    }

    /**
     * Changes the entity state for a view entity
     * @param entityName the name of the entity whose state is being changed
     * @param direction the new direction state
     * @param movement the new movement state
     */
    public void changeEntityState(String entityName, DirectionState direction, MovementState movement) {
        EntityView entity = myViewEntities.get(entityName);
        entity.changeDirectionAndMovement(direction, movement);
    }

    /**
     * Sets up the map for the view
     */
    private void setupMap() {
        myMapWrapper = myController.getMapWrapper();
        myWidth = myMapWrapper.getVisualProperties().get(2);
        myHeight = myMapWrapper.getVisualProperties().get(1);
        blockSize = myMapWrapper.getVisualProperties().get(0);
        myMapView = new MapView(myMapWrapper);
        mapPane = myMapView.createMap();
        backgroundPane = myMapView.getBackground();
        myViewObstacles = myMapView.getViewObstacles();
    }

    /**
     * Creates a scrollable background for the view
     */
    private void createScrollableBackground() {
        bPane = (BorderPane) myScene.getRoot();
        StackPane centerConsolidated = (StackPane) bPane.getChildren().get(0);
        stackPane = (StackPane) centerConsolidated.getChildren().get(0);
        stackPane.setMinHeight(myHeight);
        stackPane.setMinWidth(myWidth);
    }

    /**
     * Handles the key inputs for the view and sends to the controller
     */
    private void handleKeyInputs() {
        myScene.setOnKeyPressed(event -> {
            myController.checkKeyPress(event.getCode());
            walking.play();
        });
        myScene.setOnKeyReleased(event -> {
            myController.checkKeyRelease(event.getCode());
            walking.pause();
        });
    }

    /**
     * Detects collisions for the view
     */
    private void detectCollisions() {
        List<EntityView> myViewEntitiesList = myViewEntities.values().parallelStream().toList();
        for (EntityView entity : myViewEntitiesList) {
            List<AttackView> myViewAttackList = myViewAttacks.values().parallelStream().toList();
            for (AttackView attack : myViewAttackList) {
                if (entity.getBoundsInParent().intersects(attack.getBoundsInParent())) {
                    myController.passCollision(entity, attack);
                    break;
                }
            }
            List<BlockView> myViewObstacleList = myViewObstacles.values().parallelStream().toList();
            for (BlockView obstacle : myViewObstacleList) {
                if (entity.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                    myController.passCollision(entity, obstacle);
                    break;
                }
            }
        }
        List<AttackView> myViewAttackList = myViewAttacks.values().parallelStream().toList();
        for (AttackView attack : myViewAttackList) {
            List<BlockView> myViewObstacleList = myViewObstacles.values().parallelStream().toList();
            for (BlockView obstacle : myViewObstacleList) {
                if (attack.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                    myController.passCollision(attack, obstacle);
                    break;
                }
            }
        }
    }

    /**
     * Gets the main game screen
     * @return the main game screen
     */
    public MainGameScreen getGameScreen() {
        return mainGameScreen;
    }

    /**
     * Gets the view obstacles
     * @return the view obstacles
     */
    public Map<List<Double>, BlockView> getViewObstacles() {
        return myViewObstacles;
    }

    public void updateHealth(int num){
        mainGameScreen.getHud().updateHealth(num);
    }

    public void updateScore(int score) {
        mainGameScreen.getHud().updateScore(score);
    }
}


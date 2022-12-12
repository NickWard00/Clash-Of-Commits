package ooga.view.screens;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.gameState.AdventureGameState;
import ooga.controller.Controller;
import ooga.controller.gameState.MapGameState;
import ooga.controller.gameState.SurviveGameState;
import ooga.view.*;

import java.util.List;
import java.util.Map;

/**
 * @author Melanie Wang, Nick Ward, Mayari Merchant, James Qu
 */

/**
 * This class serves as the launching point for all three gametypes.
 */
public class MainGameScreen extends SceneCreator {
    private boolean isPlaying = false;
    private int screenSize;
    private GridPane mapPane;
    private Map<String, EntityView> myViewEntities;
    private Map<List<Integer>, BlockView> myViewPowerUps;
    private Group root;
    private BorderPane gameScreenPane;
    private ScrollPane background;
    private ScrollPane mapLayer;
    private StackPane centerPaneConsolidated;
    private Pane characters;
    private HUD hud;
    private Media music;
    private Media walk;
    private Controller controller;
    private MediaPlayer musicPlayer;
    private MediaPlayer walkPlayer;
    private Stage stage;
    private Scene myScene;
    private String myGameType;
    private double overlaySize = Integer.parseInt(constants.getString("overlaySize"));
    private Pane overlay;
    private ImageView snowy = new ImageView(new Image(images.getString("snowyImage")));
    private ImageView dark = new ImageView(new Image(images.getString("darkImage")));

    private Map<String,String> styleMethods = Map.of(
            labels.getString("css1"), "setDefault",
            labels.getString("css2"),"setDark",
            labels.getString("css3"),"setSnowy"
    );
    private MapGameState mapGameState;

    /**
     * Constructor for maingamescreen
     * @param stage the stage that the screen is set on
     * @param myController the controller of the game
     */
    public MainGameScreen(Stage stage, Controller myController){
        this.screenSize = getScreenSize();
        this.stage = stage;
        controller = myController;
    }

    /**
     * Responsible for initializing gameplay, creating the map, starting sound effects
     * @param mapPane responsible for creating the view of the map
     * @param entities refers to all existing entities in the map
     */
    public void startGamePlay(GridPane mapPane, Map<String, EntityView> entities, Map<List<Integer>, BlockView> powerups, String gameType) {
        this.isPlaying = true;
        this.myViewEntities = entities;
        this.myGameType = gameType;
        this.myViewPowerUps = powerups;
        this.mapPane = mapPane;

        music = new Media(new File(media.getString("lvl1")).toURI().toString());
        walk = new Media(new File(media.getString("walking")).toURI().toString());
        walkPlayer = new MediaPlayer(walk);

        if (gameType.equals("The Beginning") || gameType.equals("The Legend of Zelda for NES")) {
            mapGameState = new AdventureGameState(myViewEntities, controller);
        }
        else if (gameType.equals("Survive")) {
            mapGameState = new SurviveGameState(myViewEntities, controller);
        } else {
            mapGameState = new AdventureGameState(myViewEntities, controller);
        }
    }

    /**
     * Method for generating the scene that the game is based off of.
     * @return Scene object
     */
    @Override
    public Scene makeScene(){
        gameScreenPane = new BorderPane();
        background = new ScrollPane();
        mapLayer = new ScrollPane();
        characters = new Pane();
        overlay = new Pane();
        makeCharacters();
        makeBackground();
        makeDefaultOverlay();
        makeCenterPane();
        gameScreenPane.setCenter(centerPaneConsolidated);
        gameScreenPane.setBackground(Background.EMPTY);
        createHUD();
        myScene = new Scene(gameScreenPane, screenSize, screenSize);
        myScene.getStylesheets().add(styles.getString("DefaultCSS"));
        musicPlayer = new MediaPlayer(music);
        musicPlayer.setAutoPlay(true);
        nextScene();

        myScene.setFill(Color.TRANSPARENT);
        return myScene;
    }

    /**
     * generates the background (grid of the map)
     */
    private void makeBackground(){
        mapLayer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapLayer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapLayer.setContent(mapPane);
        mapLayer.setBackground(Background.EMPTY);
    }

    /**
     * puts all the parts together- adds the moving parts (background, character) as the base
     * and then stacks the overlay (special CSS effects) on top
     */
    private void makeCenterPane(){
        StackPane centerPaneMoving = new StackPane();
        centerPaneMoving.getChildren().addAll(mapLayer, characters);
        centerPaneMoving.setBackground(Background.EMPTY);
        StackPane centerPaneStill = new StackPane(overlay);
        centerPaneStill.setBackground(Background.EMPTY);
        centerPaneConsolidated = new StackPane();
        centerPaneConsolidated.setBackground(Background.EMPTY);
        centerPaneConsolidated.getChildren().addAll(centerPaneMoving, centerPaneStill);
    }

    /**
     * sets the default CSS style's overlay portion
     */
    private void makeDefaultOverlay(){
        snowy.setFitWidth(overlaySize);
        snowy.setFitHeight(overlaySize);
        dark.setFitWidth(overlaySize);
        dark.setFitHeight(overlaySize);
        overlay.getChildren().clear();
    }

    /**
     * sets the dark CSS style's overlay portion
     */
    private void makeDarkOverlay(){
        overlay.getChildren().clear();
        overlay.getChildren().add(dark);
    }
    /**
     * sets the snowy CSS style's overlay portion
     */
    private void makeSnowyOverlay(){
        overlay.getChildren().clear();
        overlay.getChildren().add(snowy);
    }

    /**
     * adds all entity's views to the characters pane
     */
    private void makeCharacters(){
        root = new Group();
        for (EntityView entity : myViewEntities.values()) {
            root.getChildren().add(entity);
        }
        characters.getChildren().add(root);
    }

    public void addPowerUpsToRoot() {
        for (BlockView powerup : myViewPowerUps.values()) {
            root.getChildren().add(powerup);
        }
    }


    /**
     * initializes the hud on the top of the screen
     */
    public void createHUD(){
        hud = new HUD(stage, this, controller);
        ToolBar top = hud.makeHUD();
        top.setId("HUD");
        gameScreenPane.setTop(top);
    }

    /**
     * changes the CSS style and calls the set methods for setting non-CSS sheet related effects
     * @param style string that details which style to change to
     */
    public void changeStyle(String style) throws IllegalStateException {
        myScene.getStylesheets().clear();
        myScene.getStylesheets().add(styles.getString(String.format("%sCSS",style)));
        try {
            Method changeCSS = this.getClass().getDeclaredMethod(styleMethods.get(style));
            changeCSS.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("noMethodFound", e);
        }
    }

    /**
     * sets the default CSS style
     */
    public void setDefault(){
        makeDefaultOverlay();
    }
    /**
     * sets the snowy CSS style
     */
    public void setSnowy(){
        makeSnowyOverlay();
    }

    /**
     * sets the dark CSS style
     */
    public void setDark(){
        makeDarkOverlay();
    }

    /**
     * removes an entity from the scene
     * @param entityName the name of the entity to be removed
     */
    public void removeEntityFromScene(String entityName){
        root.getChildren().remove(myViewEntities.get(entityName));
    }

    /**
     * adds an attack to the scene
     * @param attack the attack to be added
     */
    public void addAttackToScene(AttackView attack) {
        root.getChildren().add(attack);
    }

    /**
     * removes an attack from the scene
     * @param attack the attack to be removed
     */
    public void removeAttackFromScene(AttackView attack) {
        root.getChildren().remove(attack);
    }

    public void removePowerUpFromScene(BlockView powerUp) {
        root.getChildren().remove(powerUp);
    }

    /**
     * removes an obstacle from the scene
     * @param obstacle the obstacle to be removed
     */
    public void removeObstacleFromScene(BlockView obstacle) {
        double x = obstacle.getKey().get(0);
        double y = obstacle.getKey().get(1);
        double blockSizeX = obstacle.getFitWidth();
        double blockSizeY = obstacle.getFitHeight();
        mapPane.getChildren().remove(obstacle);
        ImageView emptyGrass;
        if (obstacle.getImagePath().contains("winter")){
            emptyGrass = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("blocks/winter_grass.jpeg")));
        }
        else {
            emptyGrass = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("blocks/grass.jpeg")));
        }
        emptyGrass.setFitWidth(blockSizeX);
        emptyGrass.setFitHeight(blockSizeY);
        mapPane.add(emptyGrass, (int) x, (int) y);
        background.setContent(mapPane);
    }

    /**
     * returns the stackpane that the map is located on
     * @return stackpane
     */
    public StackPane getMapPane() {
        return this.centerPaneConsolidated;
    }

    /**
     * checks if the game is currently playing
     * @return boolean
     */
    public boolean isPlaying(){
        return isPlaying;
    }

    /**
     * stops the playing of the game
     */
    public void stopPlaying(){
        isPlaying = false;
    }

    /**
     * getter for the media player used to play sound effects
     * @return MediaPlayer
     */
    public MediaPlayer getWalkPlayer() {
        return walkPlayer;
    }

    /**
     * required in order to update the statistics displayed in the hud
     * @return HUD
     */
    public HUD getHud() {
        return hud;
    }

    /**
     * Changes to the next scene (in this case winning or losing conditions)
     */
    public void nextScene() {
        if (mapGameState.determineWin(hud.getScore())) {
            EndGameScreen winScreen = new EndGameScreen(stage, labels, hud.getScore(), myGameType, true);
            myScene = winScreen.makeScene();
            stage.setScene(myScene);
            stage.show();
        }
        else if (mapGameState.determineLost()) {
            EndGameScreen loseScreen = new EndGameScreen(stage, labels, hud.getScore(), myGameType,false);
            myScene = loseScreen.makeScene();
            stage.setScene(myScene);
            stage.show();
        }
    }
}

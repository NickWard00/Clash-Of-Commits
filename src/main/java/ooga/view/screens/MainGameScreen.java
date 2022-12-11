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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import ooga.controller.gameState.AdventureGameState;
import ooga.controller.Controller;
import ooga.controller.gameState.MapGameState;
import ooga.view.*;

import java.util.Map;

/**
 * @author Melanie Wang, Nick Ward, Mayari Merchant
 */

/**
 * This class serves as the launching point for all three gametypes.
 */
public class MainGameScreen extends SceneCreator {
    //TODO: refactor all "Screens" into subclasses of a screen superclass
    //TODO: refactor stackpane
    private boolean isPlaying = false;
    private int screenSize;
    private GridPane mapPane;
    private Map<String, EntityView> myViewEntities;
    private Group root;
    private BorderPane gameScreenPane;
    private ScrollPane background;
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
    public void startGamePlay(GridPane mapPane, Map<String, EntityView> entities) {
        isPlaying = true;
        myViewEntities = entities;
        this.mapPane = mapPane;

        music = new Media(new File(media.getString("lvl1")).toURI().toString());
        walk = new Media(new File(media.getString("walking")).toURI().toString());
        walkPlayer = new MediaPlayer(walk);

        //TODO: Figure out how to decide what game state to use
        mapGameState = new AdventureGameState(myViewEntities, controller);
    }

    /**
     * Method for generating the scene that the game is based off of.
     * @return Scene object
     */
    @Override
    public Scene makeScene(){
        gameScreenPane = new BorderPane();
        background = new ScrollPane();
        characters = new Pane();
        overlay = new Pane();
        makeCharacters();
        makeBackground();
        makeDefaultOverlay();
        makeCenterPane();
        gameScreenPane.setCenter(centerPaneConsolidated);
        createHUD();
        myScene = new Scene(gameScreenPane, screenSize, screenSize);
        myScene.getStylesheets().add(styles.getString("DefaultCSS"));
        musicPlayer = new MediaPlayer(music);
        musicPlayer.setAutoPlay(true);
        nextScene();
        return myScene;
    }

    /**
     * generates the background (grid of the map)
     */
    private void makeBackground(){
        background.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //TODO: Set a completely grass background here?
//        background.setContent()
        background.setContent(mapPane);
    }

    /**
     * puts all the parts together- adds the moving parts (background, character) as the base
     * and then stacks the overlay (special CSS effects) on top
     */
    private void makeCenterPane(){
        StackPane centerPaneMoving = new StackPane();
        centerPaneMoving.getChildren().addAll(background, characters);
        StackPane centerPaneStill = new StackPane(overlay);
        centerPaneConsolidated = new StackPane();
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

    /**
     * removes an obstacle from the scene
     * @param obstacle the obstacle to be removed
     */
    public void removeObstacleFromScene(BlockView obstacle, double blockSize) {
        double x = obstacle.getKey().get(0);
        double y = obstacle.getKey().get(1);
        mapPane.getChildren().remove(obstacle);
        ImageView emptyGrass = new ImageView();
        if (obstacle.getImagePath().contains("winter")){
            emptyGrass = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("blocks/winter_grass.jpeg")));
        }
        else {
            emptyGrass = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("blocks/grass.jpeg")));
        }
        emptyGrass.setFitWidth(blockSize);
        emptyGrass.setFitHeight(blockSize);
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

    public void nextScene() {
        if (mapGameState.determineWin(hud.getScore())) {
            EndGameScreen winScreen = new EndGameScreen(stage, true);
            myScene = winScreen.makeScene();
            stage.setScene(myScene);
            stage.show();
        }
        else if (mapGameState.determineLost()) {
            EndGameScreen loseScreen = new EndGameScreen(stage, false);
            myScene = loseScreen.makeScene();
            stage.setScene(myScene);
            stage.show();
        }
    }
}

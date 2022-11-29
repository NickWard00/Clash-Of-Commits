package ooga.view.screens;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ooga.controller.CollisionHandler;
import ooga.controller.Controller;
import ooga.model.Collision;
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
    private MapWrapper mapWrapper;
    private MapView mapView;
    private boolean isPlaying = false;
    private int screenSize;
    private Map<String, EntityView> myViewEntities;
    private Group root;
    private BorderPane gameScreenPane;
    private ScrollPane background;
    private StackPane centerPaneConsolidated;
    private Pane characters;
    private HUD hud;
    private List<BlockView> obstacleList;
    private Media music;
    private Media walk;
    private MediaPlayer musicPlayer;
    private MediaPlayer walkPlayer;
    private Stage stage;
    private Scene s;
    private double overlaySize = Integer.parseInt(constants.getString("overlaySize"));
    private Pane overlay;
    private ImageView snowy = new ImageView(new Image(images.getString("snowyImage")));
    private ImageView dark = new ImageView(new Image(images.getString("darkImage")));

    private Map<String,String> styleMethods = Map.of(
            labels.getString("css1"), "setDefault",
            labels.getString("css2"),"setDark",
            labels.getString("css3"),"setSnowy"
    );


    public MainGameScreen(Stage s){
        this.screenSize = getScreenSize();
        stage = s;
    }

    /**
     * Responsible for initializing gameplay, creating the map, starting sound effects
     * @param map responsible for creating the view of the map
     * @param entities refers to all existing entities in the map
     */
    public void startGamePlay(MapWrapper map, Map<String, EntityView> entities) {
        isPlaying = true;
        this.mapWrapper = map;
        mapView = new MapView(mapWrapper);
        myViewEntities = entities;
        music = new Media(new File(media.getString("lvl1")).toURI().toString());
        walk = new Media(new File(media.getString("walking")).toURI().toString());
        walkPlayer = new MediaPlayer(walk);
    }

    /**
     * Method for generating the scene that the game is based off of.
     * @return Scene object
     */
    @Override
    public Scene makeScene(){
        gameScreenPane = new BorderPane();
        background = new ScrollPane();
        characters= new Pane();
        overlay = new Pane();
        makeCharacters();
        makeBackground();
        makeDefaultOverlay();
        makeCenterPane();
        gameScreenPane.setCenter(centerPaneConsolidated);
        createHUD();
        s = new Scene(gameScreenPane, screenSize, screenSize);
        s.getStylesheets().add(styles.getString("DefaultCSS"));
        musicPlayer= new MediaPlayer(music);
        musicPlayer.setAutoPlay(true);
        return s;
    }

    /**
     * generates the background (grid of the map)
     */
    public void makeBackground(){
        background.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setContent(mapView.createMap());
    }

    /**
     * puts all the parts together- adds the moving parts (background, character) as the base
     * and then stacks the overlay (special CSS effects) on top
     */
    public void makeCenterPane(){
        StackPane centerPaneMoving = new StackPane();
        centerPaneMoving.getChildren().addAll(background, characters);
        StackPane centerPaneStill = new StackPane(overlay);
        centerPaneConsolidated=new StackPane();
        centerPaneConsolidated.getChildren().addAll(centerPaneMoving, centerPaneStill);
    }

    /**
     * sets the default CSS style's overlay portion
     */
    public void makeDefaultOverlay(){
        snowy.setFitWidth(overlaySize);
        snowy.setFitHeight(overlaySize);
        dark.setFitWidth(overlaySize);
        dark.setFitHeight(overlaySize);
        overlay.getChildren().clear();

    }

    /**
     * sets the dark CSS style's overlay portion
     */
    public void makeDarkOverlay(){
        overlay.getChildren().clear();
        overlay.getChildren().add(dark);
    }
    /**
     * sets the snowy CSS style's overlay portion
     */
    public void makeSnowyOverlay(){
        overlay.getChildren().clear();
        overlay.getChildren().add(snowy);
    }

    /**
     * adds all entity's views to the characters pane
     */
    public void makeCharacters(){
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
        hud = new HUD(stage, this);
        ToolBar top =hud.makeHUD();
        top.setId("HUD");
        gameScreenPane.setTop(top);
    }

    /**
     * changes the CSS style and calls the set methods for setting non-CSS sheet related effects
     * @param style string that details which style to change to
     */
    public void changeStyle(String style){
        s.getStylesheets().clear();
        s.getStylesheets().add(styles.getString(String.format("%sCSS",style)));
        try {
            Method changeCSS = this.getClass().getDeclaredMethod(
                    styleMethods.get(style));
            changeCSS.invoke(this);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefault(){
        makeDefaultOverlay();
    }
    public void setSnowy(){
        makeSnowyOverlay();
    }

    public void setDark(){
        makeDarkOverlay();
    }


    public void removeEntityFromScene(String entityName){
        root.getChildren().remove(myViewEntities.get(entityName));
    }

    public void addAttackToScene(AttackView attack) {
        root.getChildren().add(attack);
    }
    public void removeAttackFromScene(AttackView attack) {
        root.getChildren().remove(attack);
    }

    public StackPane getMapPane() {
        return this.centerPaneConsolidated;
    }

    public boolean isPlaying(){
        return isPlaying;
    }
    public void stopPlaying(){
        isPlaying = false;
    }

    public MediaPlayer getWalkPlayer() {
        return walkPlayer;
    }

    public void detectCollisions(Controller controller) {
        int counter = 0;
        //TODO: Implement collisions for entity and entity
        for (EntityView entity: myViewEntities.values()) {
            for (BlockView obstacle: Controller.getViewObstacles().values()) {
                if (entity.localToScreen(entity.getBoundsInLocal()).intersects(obstacle.getImageView().localToScreen(obstacle.getImageView().getBoundsInLocal()))) {
                    CollisionHandler handler = new CollisionHandler();
                    handler.translateCollision(entity, obstacle);
                }
            }
        }

    }
}

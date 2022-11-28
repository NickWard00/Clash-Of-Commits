package ooga.view.screens;

import java.io.File;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
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


    public MainGameScreen(Stage s){
        this.screenSize = getScreenSize();
        stage = s;
    }

    public void startGamePlay(MapWrapper map, Map<String, EntityView> entities) {
        isPlaying = true;
        this.mapWrapper = map;
        mapView = new MapView(mapWrapper);
        myViewEntities = entities;
        music = new Media(new File(media.getString("lvl1")).toURI().toString());
        walk = new Media(new File(media.getString("walking")).toURI().toString());
        walkPlayer = new MediaPlayer(walk);
    }

    //make new scene
    @Override
    public Scene makeScene(){
        gameScreenPane = new BorderPane();
        background = new ScrollPane();
        centerPaneConsolidated = new StackPane();
        background.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        characters= new Pane();
        root = new Group();
        for (EntityView entity : myViewEntities.values()) {
            root.getChildren().add(entity);
        }
        characters.getChildren().add(root);
        background.setContent(mapView.createMap());
        centerPaneConsolidated.getChildren().addAll(background, characters);
        gameScreenPane.setCenter(centerPaneConsolidated);
        createHUD();
        Scene s = new Scene(gameScreenPane, screenSize, screenSize);
        s.getStylesheets().add(styles.getString("mainGameScreenCSS"));
        musicPlayer= new MediaPlayer(music);
        musicPlayer.setAutoPlay(true);
        return s;
    }

    public void createHUD(){
        hud = new HUD(stage);
        ToolBar top =hud.makeHUD();
        top.setId("HUD");
        gameScreenPane.setTop(top);
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

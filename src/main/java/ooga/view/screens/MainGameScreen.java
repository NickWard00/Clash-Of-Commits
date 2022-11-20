package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import ooga.view.EntityView;
import ooga.view.HUD;
import ooga.view.MapView;
import ooga.view.MapWrapper;

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


    public MainGameScreen(){
        this.screenSize = getScreenSize();
    }

    public void startGamePlay(MapWrapper map, Map<String, EntityView> entities) {
        isPlaying = true;
        this.mapWrapper = map;
        mapView = new MapView(mapWrapper);
        myViewEntities = entities;
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
        hud = new HUD();
        root = new Group();
        //root.getChildren().add(mapView.createMap());
        for (EntityView entity : myViewEntities.values()) {
            root.getChildren().add(entity);
        }
        characters.getChildren().add(root);
        background.setContent(mapView.createMap());
        centerPaneConsolidated.getChildren().addAll(background, characters);
        gameScreenPane.setCenter(centerPaneConsolidated);
        gameScreenPane.setTop(hud.makeHUD());
        Scene s = new Scene(gameScreenPane, screenSize, screenSize);
        return s;
    }


    public void removeEntityFromScene(String entityName){
        root.getChildren().remove(myViewEntities.get(entityName));
    }

    public boolean isPlaying(){
        return isPlaying;
    }
    public void stopPlaying(){
        isPlaying = false;
    }

}

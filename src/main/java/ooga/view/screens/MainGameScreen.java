package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ooga.controller.EntityView;
import ooga.controller.MapParser;
import ooga.view.MapView;
import ooga.view.MapWrapper;

import java.util.List;
import java.util.Map;

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
    private ScrollPane centerPane;

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
        centerPane = new ScrollPane();
        // gameScreenPane.setPrefSize(SCREEN_SIZE, SCREEN_SIZE);
        // StackPane.setAlignment(gameScreenPane, Pos.CENTER);
        root = new Group();
        root.getChildren().add(mapView.createMap());
        for (EntityView entity : myViewEntities.values()) {
            root.getChildren().add(entity);
        }
        centerPane.setContent(root);
        gameScreenPane.setCenter(centerPane);
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

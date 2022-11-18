package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import ooga.controller.EntityView;
import ooga.controller.MapParser;
import ooga.view.MapView;
import ooga.view.MapWrapper;

import java.util.List;

public class MainGameScreen extends SceneCreator {
    //TODO: refactor all "Screens" into subclasses of a screen superclass
    //TODO: refactor stackpane
    private MapWrapper mapWrapper;
    private MapView mapView;
    private boolean isPlaying = false;
    private int screenSize;
    private List<EntityView> myEntities;

    public MainGameScreen(){
        this.screenSize = getScreenSize();
    }

    public void startGamePlay(MapWrapper map, List<EntityView> entities) {
        isPlaying = true;
        this.mapWrapper = map;
        mapView = new MapView(mapWrapper);
        myEntities = entities;
    }

    //make new scene
    @Override
    public Scene makeScene(){
        // gameScreenPane = new GridPane();
        // gameScreenPane.setPrefSize(SCREEN_SIZE, SCREEN_SIZE);
        // StackPane.setAlignment(gameScreenPane, Pos.CENTER);
        Group root = new Group();
        root.getChildren().add(mapView.createMap());
        for (EntityView entity : myEntities) {
            root.getChildren().add(entity);
        }
        Scene s = new Scene(root, screenSize, screenSize);
        return s;
    }
    public boolean isPlaying(){
        return isPlaying;
    }
    public void stopPlaying(){
        isPlaying = false;
    }

}

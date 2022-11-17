package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import ooga.controller.MapParser;
import ooga.view.MapView;
import ooga.view.MapWrapper;

public class MainGameScreen extends SceneCreator {
    //TODO: refactor all "Screens" into subclasses of a screen superclass
    //TODO: refactor stackpane
    private GridPane gameScreenPane;
    private MapWrapper mapWrapper;
    private MapParser mapParser;
    private MapView mapView;
    private boolean isPlaying = false;
    private int screenSize;

    public MainGameScreen(){
        this.screenSize = getScreenSize();
    }
    public void startGamePlay() {
        isPlaying = true;
        String mapName = "MainMap"; //for now!
        mapParser = new MapParser(mapName);
        mapWrapper = mapParser.getMapWrapper();
        mapWrapper.setStateToImageMap(mapParser.getStateToImageMap());
        mapView = new MapView(mapWrapper);
    }

    //make new scene
    @Override
    public Scene makeScene(){
        // gameScreenPane = new GridPane();
        // gameScreenPane.setPrefSize(SCREEN_SIZE, SCREEN_SIZE);
        // StackPane.setAlignment(gameScreenPane, Pos.CENTER);
        Scene s = new Scene(mapView.createMap(), screenSize, screenSize);
        return s;
    }
    public boolean isPlaying(){
        return isPlaying;
    }
    public void stopPlaying(){
        isPlaying = false;
    }

}

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ooga.controller.MapParser;

import java.util.ResourceBundle;

public class MainGameScreen {
    //TODO: refactor all "Screens" into subclasses of a screen superclass
    //TODO: refactor stackpane
    private StackPane gameScreenPane;
    private MapWrapper mapWrapper;
    private MapParser mapParser;
    private MapView mapView;
    private boolean isPlaying = false;
    public static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");
    public static final int SCREEN_SIZE = Integer.parseInt(constants.getString("screenSize"));

    public MainGameScreen(){

    }
    public void startGamePlay() {
        isPlaying = true;
        String mapName = "MainMap"; //for now!
        mapParser = new MapParser(mapName);
        mapWrapper = mapParser.getMapWrapper();
        mapView = new MapView(mapWrapper, gameScreenPane);
    }

    //make new scene
    public Scene makeScene(){
        gameScreenPane = new StackPane();
        gameScreenPane.setPrefSize(SCREEN_SIZE, SCREEN_SIZE);
        StackPane.setAlignment(gameScreenPane, Pos.CENTER);
        Scene s = new Scene(gameScreenPane, SCREEN_SIZE, SCREEN_SIZE);
        return s;
    }
    public boolean isPlaying(){
        return isPlaying;
    }
    public void stopPlaying(){
        isPlaying = false;
    }

}

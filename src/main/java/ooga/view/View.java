package ooga.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View {
    private Scene scene;
    private BorderPane root;
    private Stage stage;
    private MapView mapView;

    public View(Stage stage){
        setupGame(stage);
        mapView = new MapView();
    }
    public void step(double elapsedTime){

    }

    public void setupGame(Stage stage){
        root = new BorderPane();
        scene = new Scene(root);
        scene.setOnKeyPressed(e->getKeyInput(e.getCode()));
        StartScreen startScreen = new StartScreen(stage);
        stage.setScene(startScreen.makeScene());
    }

    public void getKeyInput(KeyCode key){
        switch(key){
            case LEFT -> {
                mapView.moveRight();
            }
            case RIGHT -> {
                mapView.moveLeft();
            }
            case DOWN -> {
                mapView.moveUp();
            }
            case UP -> {
                mapView.moveDown();
            }
        }
    }

}

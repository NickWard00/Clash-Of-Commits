package ooga.view.screens;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.GameSlot;
import ooga.view.MapWrapper;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
//this screen is for when a player wishes to start a brand new game.
public class OpenNewGameScreen extends SceneCreator {
    private GameSlot slot1;
    private GameSlot slot2;
    private GameSlot slot3;
    private StackPane background;
    private ResourceBundle labels;
    private ResourceBundle styles;
    private Stage stage;
    private int screenSize;

    public OpenNewGameScreen(Stage stage, ResourceBundle label){
        this.stage = stage;
        labels = label;
        styles = getStyles();
        screenSize = getScreenSize();
    }

    @Override
    public Scene makeScene(){
        background = new StackPane();
        slot1 = new GameSlot(labels.getString("game1"), labels);
        slot2 = new GameSlot(labels.getString("game2"), labels);
        slot3 = new GameSlot(labels.getString("game3"), labels);
        VBox slots = new VBox(slot1,slot2,slot3);
        slots.getStyleClass().add("vbox");
        slots.setAlignment(Pos.CENTER);
        background.setAlignment(Pos.CENTER);
        background.getChildren().add(slots);
        Scene s = new Scene(background, screenSize, screenSize);
        handleEvents();
        s.getStylesheets().add(styles.getString("openNewGameCSS"));
        return s;
    }

    //parsing of files should occur here
    private void handleEvents(){
        slot1.setOnMouseClicked(event -> {
            Controller controller = new Controller(stage, "MainMap", labels);
            controller.startAnimation();
        });
        slot2.setOnMouseClicked(event -> {

        });
        slot3.setOnMouseClicked(event -> {

        });
    }
}

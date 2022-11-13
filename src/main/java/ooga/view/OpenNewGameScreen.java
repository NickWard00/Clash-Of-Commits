package ooga.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

//this screen is for when a player wishes to start a brand new game.
public class OpenNewGameScreen {

    private GameSlot slot1;
    private GameSlot slot2;
    private GameSlot slot3;
    private StackPane background;
    private ResourceBundle labels;

    public OpenNewGameScreen(ResourceBundle l){
        labels = l;
    }

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
        Scene s = new Scene(background, StartScreen.SCREEN_SIZE,StartScreen.SCREEN_SIZE );
        handleEvents();
        s.getStylesheets().add(StartScreen.styles.getString("openNewGameCSS"));
        return s;
    }

    //parsing of files should occur here
    public void handleEvents(){
        slot1.setOnMouseClicked(event -> {

                });
        slot2.setOnMouseClicked(event -> {

        });
        slot3.setOnMouseClicked(event -> {

        });
    }
}

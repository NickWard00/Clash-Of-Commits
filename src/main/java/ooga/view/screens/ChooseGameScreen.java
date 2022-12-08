package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.controller.Controller;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

/**
 * Allows the player to either load a previously saved game or to start a new game.
 */
public class ChooseGameScreen extends SceneCreator {
    private Stage stage;
    private Pane background;
    private Button loadSave;
    private Button newGame;
    private ResourceBundle labels;
    private ResourceBundle styles;
    private int screenSize;

    /**
     * Constructor for choose game screen
     * @param stage the stage it is based on
     * @param labels the language labels it is using
     */
    public ChooseGameScreen(Stage stage, ResourceBundle labels){
        this.stage = stage;
        this.labels = labels;
        this.styles = getStyles();
        this.screenSize = getScreenSize();
    }

    /**
     * creates the scene
     * @return Scene
     */
    @Override
    public Scene makeScene(){
        loadSave = new Button(labels.getString("loadSaveButton"));
        newGame = new Button(labels.getString("startNewGameButton"));
        newGame.setId("newGame");
        loadSave.setId("loadSave");
        background = new StackPane();
        VBox buttonCol = new VBox(loadSave, newGame);
        buttonCol.setId("buttonCol");
        buttonCol.setAlignment(Pos.CENTER);
        background.getChildren().add(buttonCol);
        handleEvents();
        Scene s = new Scene(background, screenSize, screenSize);
        s.getStylesheets().add(styles.getString("chooseGameCSS"));
        return s;
    }

    /**
     * handles the events of loading the load save screen or loading the start new game screen.
     */
    private void handleEvents(){
        loadSave.setOnAction(event ->{
            OpenSaveScreen oss = new OpenSaveScreen(stage, labels);
            stage.setScene(oss.makeScene());
            stage.show();
        });
        newGame.setOnAction(event->{
            OpenNewGameScreen openNewGameScreen = new OpenNewGameScreen(stage, labels);
            stage.setScene(openNewGameScreen.makeScene());
            stage.show();
        });
    }
}

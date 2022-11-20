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
public class ChooseGameScreen extends SceneCreator {
    private Stage stage;
    private Pane background;
    private Button loadSave;
    private Button newGame;
    private ResourceBundle labels;
    private ResourceBundle styles;
    private int screenSize;

    public ChooseGameScreen(Stage stage){
        this.stage = stage;
        this.labels = getLabels();
        this.styles = getStyles();
        this.screenSize = getScreenSize();
    }

    @Override
    public Scene makeScene(){
        loadSave = new Button(labels.getString("loadSaveButton"));
        newGame = new Button(labels.getString("startNewGameButton"));
        newGame.setId("newGame");
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

    private void handleEvents(){
        loadSave.setOnAction(event ->{

        });
        newGame.setOnAction(event->{
            OpenNewGameScreen openNewGameScreen = new OpenNewGameScreen(stage);
            stage.setScene(openNewGameScreen.makeScene());
            stage.show();
        });
    }
}

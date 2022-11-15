package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ChooseGameScreen {
    private Stage stage;
    private Pane background;

    private Button loadSave;
    private Button newGame;
    private ResourceBundle labels;


    public ChooseGameScreen(Stage s, ResourceBundle l){
        stage = s;
        labels = l;
    }

    public Scene makeScene(){
        loadSave=new Button(labels.getString("loadSaveButton"));
        newGame = new Button(labels.getString("startNewGameButton"));
        newGame.setId("newGame");
        background = new Pane();
        VBox buttonCol = new VBox(loadSave,newGame);
        buttonCol.setId("buttonCol");
        buttonCol.setAlignment(Pos.CENTER);
        background.getChildren().add(buttonCol);
        handleEvents();
        Scene s = new Scene(background, StartScreen.SCREEN_SIZE, StartScreen.SCREEN_SIZE);
        s.getStylesheets().add(StartScreen.styles.getString("chooseGameCSS"));
        return s;
    }

    public void handleEvents(){
        loadSave.setOnAction(event ->{

        });
        newGame.setOnAction(event->{
            OpenNewGameScreen os = new OpenNewGameScreen(labels, stage);
            stage.setScene(os.makeScene());
            stage.show();
        });
    }
}

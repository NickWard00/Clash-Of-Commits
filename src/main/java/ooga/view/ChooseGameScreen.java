package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChooseGameScreen {
    private Stage stage;
    private Pane background;


    public ChooseGameScreen(Stage s){
        stage = s;
    }

    public Scene makeScene(){
        return new Scene(background, StartScreen.SCREEN_SIZE, StartScreen.SCREEN_SIZE);
    }
}

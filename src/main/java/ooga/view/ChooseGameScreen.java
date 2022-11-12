package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChooseGameScreen {
    private Stage stage;
    private Pane background;

    private Button loadGame;
    private Button newGame;


    public ChooseGameScreen(Stage s){
        stage = s;
    }

    public Scene makeScene(){
        //loadGame=new Button()
        return new Scene(background, StartScreen.SCREEN_SIZE, StartScreen.SCREEN_SIZE);
    }
}

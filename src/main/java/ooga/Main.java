package ooga;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.screens.StartScreen;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }

    /**
     * Start of the program.
     */

    @Override
    public void start(Stage stage){
        StartScreen startScreen = new StartScreen(stage);
        stage.setScene(startScreen.makeScene());
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}

package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

public class StartScreen {
    //load images and constants
    public static final ResourceBundle images = ResourceBundle.getBundle(
            "ResourceBundles.Images");
    public static final ResourceBundle constants = ResourceBundle.getBundle(
            "ResourceBundles.ViewConstants");
    public static final ResourceBundle labels = ResourceBundle.getBundle(
            "ResourceBundles.LabelsBundle");

    public static final int SCREEN_SIZE = Integer.parseInt(constants.getString("screenSize"));

    private Button startGame;
    private ImageView background;
    private Pane startGamePane;
    private Stage currentStage;

    public StartScreen(Stage stage){
        currentStage = stage;
    }

    public Scene makeScene(){
        background = new ImageView(new Image(images.getString("startScreenImage")));
        startGame = new Button(labels.getString("startButton"));
        startGamePane = new Pane();
        startGamePane.getChildren().addAll(background,startGame);
        handleEvents();
        return new Scene(startGamePane, SCREEN_SIZE, SCREEN_SIZE);
    }

    public void nextScreen(){
        ChooseGameScreen c = new ChooseGameScreen(currentStage);
        currentStage.setScene(c.makeScene());
        currentStage.show();
    }

    public void handleEvents(){
        startGame.setOnAction(event -> {
            nextScreen();
        });
    }
}

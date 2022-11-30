package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.screens.AboutGamePopup;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.SceneCreator;
import ooga.view.screens.SettingsPopup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Melanie Wang
 */
public class HUD extends SceneCreator {

    private HealthStatus playerHealth;
    private int playerScore;
    private Label scoreText;
    private ToolBar HUDBar;

    private Button settings;
    private Button about;

    private Button playPause;

    private Controller controller;

    private ImageView playButton = new ImageView(new Image(images.getString("playImage")));

    private ImageView pauseButton = new ImageView(new Image(images.getString("pauseImage")));

    private Boolean play;

    private  Stage popup = new Stage();
    private Stage stage;

    private MainGameScreen main;

    private Map <Boolean, String> playPauseMethods = Map.of(
            true, "setUpPauseButton",
            false,"setUpPlayButton"
    );

    public HUD(Stage stage, MainGameScreen mainGameScreen, Controller control){
        controller = control;
        play = true;
        playPause = new Button("", pauseButton);
        playerScore = Integer.parseInt(getConstants().getString("defaultScore"));
        scoreText = new Label(getLabels().getString("score") + playerScore);
        settings = new Button("", new ImageView(new Image(images.getString("settingsImage"))));
        about = new Button("", new ImageView(new Image(images.getString("aboutImage"))));
        about.setFocusTraversable(false);
        settings.setFocusTraversable(false);
        playPause.setFocusTraversable(false);
        this.stage = stage;
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(stage);
        main = mainGameScreen;
    }

    public ToolBar makeHUD(){
        HUDBar = new ToolBar();
        playerHealth = new HealthStatus();;
        HUDBar.getItems().addAll(playerHealth, scoreText, about, playPause, settings);
        HUDBar.getStylesheets().add(styles.getString("HUDCSS"));
        handleEvents();
        return HUDBar;
    }

    public void handleEvents(){
        settings.setOnAction(event -> {
            SettingsPopup settingsPopup = new SettingsPopup(labels, stage, main);
            Scene sps = new Scene(settingsPopup);
            popup.setScene(sps);
            popup.show();

        });
        about.setOnAction(event ->{
            AboutGamePopup aboutPopup = new AboutGamePopup(labels);
            Scene ap = new Scene(aboutPopup);
            popup.setScene(ap);
            popup.show();
        });
        handlePlayPause();
    }
    public void handlePlayPause(){
        playPause.setOnAction(event ->{
            play = !play;
            try {
                Method tryButton = this.getClass().getDeclaredMethod(
                        playPauseMethods.get(play));
                tryButton.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setUpPauseButton(){
        playPause.setGraphic(pauseButton);
        controller.playAnimation();
    }

    public void setUpPlayButton(){
        playPause.setGraphic(playButton);
        controller.pauseAnimation();
    }

    public void updateScore(int newScore){
        playerScore = newScore;
    }
}

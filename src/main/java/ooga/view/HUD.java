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
    private ImageView playButton;
    private ImageView pauseButton;
    private Boolean play;
    private Stage popup;
    private Stage stage;
    private MainGameScreen mainGameScreen;
    private Map <Boolean, String> playPauseMethods;

    public HUD(Stage stage, MainGameScreen mainGameScreen, Controller controller){
        this.popup = new Stage();
        this.controller = controller;
        play = true;
        playPauseMethods = Map.of(
                true, "setUpPauseButton",
                false,"setUpPlayButton"
        );
        playPause = new Button("", pauseButton);
        playerScore = Integer.parseInt(getConstants().getString("defaultScore"));
        scoreText = new Label(String.format("%s %s",getLabels().getString("score"), playerScore));
        settings = new Button("", new ImageView(new Image(images.getString("settingsImage"))));
        about = new Button("", new ImageView(new Image(images.getString("aboutImage"))));
        playButton = new ImageView(new Image(images.getString("playImage")));
        pauseButton = new ImageView(new Image(images.getString("pauseImage")));
        about.setFocusTraversable(false);
        settings.setFocusTraversable(false);
        playPause.setFocusTraversable(false);
        this.stage = stage;
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(stage);
        this.mainGameScreen = mainGameScreen;
    }

    public ToolBar makeHUD(){
        HUDBar = new ToolBar();
        playerHealth = new HealthStatus();;
        HUDBar.getItems().addAll(playerHealth, scoreText, about, playPause, settings);
        HUDBar.getStylesheets().add(styles.getString("HUDCSS"));
        handleEvents();
        return HUDBar;
    }

    private void handleEvents(){
        settings.setOnAction(event -> {
            SettingsPopup settingsPopup = new SettingsPopup(labels, stage, mainGameScreen, controller);
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

    private void handlePlayPause(){
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

    private void setUpPauseButton(){
        playPause.setGraphic(pauseButton);
        controller.playAnimation();
    }

    private void setUpPlayButton(){
        playPause.setGraphic(playButton);
        controller.pauseAnimation();
    }

    public void updateScore(int newScore){
        playerScore = newScore;
    }

    public void updateHealth(int newHealth){
        playerHealth.updateHealth(newHealth);
    }
}

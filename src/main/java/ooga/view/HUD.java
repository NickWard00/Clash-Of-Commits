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

    /**
     * Constructor for the HUD class
     * @param stage
     * @param mainGameScreen
     * @param controller
     */
    public HUD(Stage stage, MainGameScreen mainGameScreen, Controller controller){
        this.popup = new Stage();
        this.controller = controller;
        play = true;
        playButton = new ImageView(new Image(images.getString("playImage")));
        pauseButton = new ImageView(new Image(images.getString("pauseImage")));
        playPauseMethods = Map.of(
                true, "setUpPauseButton",
                false,"setUpPlayButton"
        );
        playPause = new Button("", pauseButton);
        playPause.setId("playPause");
        playerScore = Integer.parseInt(getConstants().getString("defaultScore"));
        scoreText = new Label(String.format("%s  %s",getLabels().getString("score"), playerScore));
        settings = new Button("", new ImageView(new Image(images.getString("settingsImage"))));
        about = new Button("", new ImageView(new Image(images.getString("aboutImage"))));
        about.setFocusTraversable(false);
        settings.setFocusTraversable(false);
        playPause.setFocusTraversable(false);
        this.stage = stage;
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initOwner(stage);
        this.mainGameScreen = mainGameScreen;
    }

    /**
     * Creates the HUD
     * @return the HUD as a toolbar
     */
    public ToolBar makeHUD(){
        HUDBar = new ToolBar();
        HUDBar.getStyleClass().add("HUD");
        playerHealth = new HealthStatus();
        HUDBar.getItems().addAll(playerHealth, scoreText, about, playPause, settings);
        HUDBar.getStylesheets().add(styles.getString("HUDCSS"));
        handleEvents();
        about.setId("about");
        settings.setId("settings");
        return HUDBar;
    }

    /**
     * Handles the events for the HUD
     */
    private void handleEvents(){
        settings.setOnAction(event -> {
            SettingsPopup settingsPopup = new SettingsPopup(labels, stage, mainGameScreen, controller);
            settingsPopup.getStyleClass().add("vbox");
            settingsPopup.setId("settingsPopup");
            Scene sps = new Scene(settingsPopup);
            popup.setScene(sps);
            popup.show();

        });
        about.setOnAction(event ->{
            AboutGamePopup aboutPopup = new AboutGamePopup(labels);
            aboutPopup.setId("aboutPopup");
            Scene ap = new Scene(aboutPopup);
            popup.setScene(ap);
            popup.show();
        });
        handlePlayPause();
    }

    /**
     * Handles the play/pause button
     */
    private void handlePlayPause(){
        playPause.setOnAction(event ->{
            play = !play;
            try {
                Method tryButton = this.getClass().getDeclaredMethod(
                        playPauseMethods.get(play));
                tryButton.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("noMethodFound", e);
            }
        });
    }

    /**
     * Sets up the pause button
     */
    private void setUpPauseButton(){
        playPause.setGraphic(pauseButton);
        controller.playAnimation();
    }

    /**
     * Sets up the play button
     */
    private void setUpPlayButton(){
        playPause.setGraphic(playButton);
        controller.pauseAnimation();
    }

    /**
     * Updates the score
     * @param newScore the new score
     */
    public void updateScore(int newScore){
        playerScore = newScore;
        scoreText.setText(String.format("%s  %s",getLabels().getString("score"), playerScore));
    }

    /**
     * Updates the health
     * @param newHealth the new health
     */
    public void updateHealth(int newHealth){
        playerHealth.updateHealth(newHealth);
    }

    /**
     * Gets the score in the game
     * @return the player's score
     */
    public int getScore() {
        return playerScore;
    }
}

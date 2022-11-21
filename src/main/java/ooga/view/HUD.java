package ooga.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.view.screens.AboutGamePopup;
import ooga.view.screens.SceneCreator;

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

    private  Stage popup = new Stage();

    public HUD(){
        playerScore = Integer.parseInt(getConstants().getString("defaultScore"));
        scoreText = new Label(getLabels().getString("score")+playerScore);
        settings= new Button("",new ImageView(new Image(images.getString("settingsImage"))));
        about = new Button("",new ImageView(new Image(images.getString("aboutImage"))));
        about.setFocusTraversable(false);
        settings.setFocusTraversable(false);
    }
    public ToolBar makeHUD(){
        HUDBar = new ToolBar();
        playerHealth = new HealthStatus();

        HUDBar.getItems().addAll(playerHealth, scoreText, about, settings);
        HUDBar.getStylesheets().add(styles.getString("HUDCSS"));
        handleEvents();
        return HUDBar;
    }

    public void handleEvents(){
        settings.setOnAction(event -> {

        });
        about.setOnAction(event ->{
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(popup);
            AboutGamePopup aboutPopup = new AboutGamePopup(labels);
            Scene ap = new Scene(aboutPopup);
            popup.setScene(ap);
            popup.show();
        });
    }

    public void updateScore(int newScore){
        playerScore = newScore;
    }
}

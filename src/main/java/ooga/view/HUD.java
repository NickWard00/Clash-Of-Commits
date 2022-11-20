package ooga.view;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import ooga.view.screens.SceneCreator;

/**
 * @author Melanie Wang
 */
public class HUD extends SceneCreator {

    private HealthStatus playerHealth;
    private int playerScore;
    private Label scoreText;
    private ToolBar HUDBar;

    public HUD(){
        playerScore = Integer.parseInt(getConstants().getString("defaultScore"));
        scoreText = new Label(getLabels().getString("score")+playerScore);
    }
    public ToolBar makeHUD(){
        HUDBar = new ToolBar();
        playerHealth = new HealthStatus();

        HUDBar.getItems().addAll(playerHealth, scoreText);
        HUDBar.getStylesheets().add(styles.getString("HUDCSS"));
        return HUDBar;
    }

    public void updateScore(int newScore){
        playerScore = newScore;
    }
}

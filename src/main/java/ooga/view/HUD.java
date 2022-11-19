package ooga.view;

import javafx.scene.control.ToolBar;
import ooga.view.screens.SceneCreator;

public class HUD extends ToolBar {

    private HealthStatus playerHealth;
    private int playerScore;

    public HUD(){
        //playerScore =
        //this.getChildren().add();
    }

    public void updateScore(int newScore){
        playerScore = newScore;
    }
}

package ooga.view.screens;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the screen that is displayed when the game is lost.
 *
 * @author James Qu
 */
public class LoseScreen extends SceneCreator {
  private Scene loseGameScene;
  private Pane pane;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;

  public LoseScreen(Stage stage) {
    this.styles = getStyles();
    this.screenSize = getScreenSize();
  }

  //Currently implementing just a "You have lost" screen, will change later
  public Scene createScene() {
    this.pane = new Pane();
    pane.setId("LosingScreen");
    text = new Text("You have lost");
    pane.getChildren().add(text);
    loseGameScene = new Scene(pane, screenSize, screenSize);
    loseGameScene.getStylesheets().add(styles.getString("loseScreenCSS"));
    return loseGameScene;
  }
}

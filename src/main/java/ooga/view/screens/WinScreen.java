package ooga.view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.view.screens.SceneCreator;

import java.util.ResourceBundle;

/**
 * This class represents the screen that is displayed when the game is won.
 *
 * @author James Qu
 */
public class WinScreen extends SceneCreator {
  private Scene winGameScene;
  private Group root;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;
  private Stage currentStage;

  public WinScreen(Stage stage) {
    this.currentStage = stage;
    root = new Group();
    this.styles = getStyles();
    this.screenSize = getScreenSize();
    root.setId("WinningScreen");
  }

  //Currently implementing just a "Congratulations" screen, will change later
  @Override
  public Scene makeScene() {
    text = new Text("Congratulations");
    root.getChildren().add(text);
    winGameScene = new Scene(root, screenSize, screenSize);
    winGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
    return winGameScene;
  }
}

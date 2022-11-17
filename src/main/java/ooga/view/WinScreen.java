package ooga.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

  public WinScreen() {
    root = new Group();
    this.styles = getStyles();
    this.screenSize = getScreenSize();
    root.setId("WinningScreen");
  }

  //Currently implementing just a "Congratulations" screen, will change later
  public Scene createScene() {
    text = new Text("Congratulations");
    root.getChildren().add(text);
    winGameScene = new Scene(root, screenSize, screenSize);
    winGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
    return winGameScene;
  }
}

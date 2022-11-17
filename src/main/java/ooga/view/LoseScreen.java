package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;

/**
 * This class represents the screen that is displayed when the game is lost.
 *
 * @author James Qu
 */
public class LoseScreen extends SceneCreator {
  private Scene loseGameScene;
  private Group root;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;

  public LoseScreen() {
    this.root = new Group();
    this.styles = getStyles();
    this.screenSize = getScreenSize();
    root.setId("LosingScreen");
  }

  //Currently implementing just a "You have lost" screen, will change later
  public Scene createScene() {
    text = new Text("You have lost");
    root.getChildren().add(text);
    loseGameScene = new Scene(root, screenSize, screenSize);
    loseGameScene.getStylesheets().add(styles.getString("loseScreenCSS"));
    return loseGameScene;
  }
}

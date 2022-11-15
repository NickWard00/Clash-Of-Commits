package ooga.view;

import static ooga.view.StartScreen.SCREEN_SIZE;
import static ooga.view.StartScreen.styles;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the screen that is displayed when the game is won.
 *
 * @author James Qu
 */
public class WinScreen {
  private Scene winGameScene;
  private Group root;
  private Text text;

  public WinScreen() {
    root = new Group();
    root.setId("WinningScreen");
  }

  //Currently implementing just a "Congratulations" screen, will change later
  public Scene createScene() {
    text = new Text("Congratulations");
    root.getChildren().add(text);
    winGameScene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE);
    winGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
    return winGameScene;
  }
}

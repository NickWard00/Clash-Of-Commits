package ooga.view;

import static ooga.view.StartScreen.SCREEN_SIZE;
import static ooga.view.StartScreen.styles;

import java.awt.TextField;
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
public class LoseScreen {
  private Scene loseGameScene;
  private Group root;
  private Text text;

  public LoseScreen() {
    root = new Group();
    root.setId("LosingScreen");
  }

  //Currently implementing just a "You have lost" screen, will change later
  public Scene createScene() {
    text = new Text("You have lost");
    root.getChildren().add(text);
    loseGameScene = new Scene(root, SCREEN_SIZE, SCREEN_SIZE);
    loseGameScene.getStylesheets().add(styles.getString("loseScreenCSS"));
    return loseGameScene;
  }
}

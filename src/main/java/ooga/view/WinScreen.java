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
  private Stage winGameStage;
  private Scene winGameScene;
  private Pane pane;
  private Group root;
  private Text text;

  public WinScreen(Stage stage) {
    winGameStage = stage;
    pane = new Pane();
    root = new Group();
  }

  //Currently implementing just a "Congratulations" screen, will change later
  public Scene makeScene() {
    text = new Text("Congratulations");
    winGameScene = new Scene(pane, SCREEN_SIZE, SCREEN_SIZE);
    winGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
    root.getChildren().add(text);
    return winGameScene;
  }
}

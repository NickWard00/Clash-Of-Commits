package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
  private Pane pane;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;
  private Stage currentStage;
  private ResourceBundle resources;
  private Button playAgainButton;

  public WinScreen(Stage stage) {
    this.resources = getLabels();
    this.currentStage = stage;
    this.styles = getStyles();
    this.screenSize = getScreenSize();
  }

  //TODO: Refactor the constants out
  @Override
  public Scene makeScene() {
    this.pane = new Pane();
    pane.setId("WinningScreen");
    playAgainButton = new Button(resources.getString("playAgainButtonWin"));
    playAgainButton.setId("playAgainButton");
    text = new Text(screenSize/2, 20, "Congratulations");
    text.setId("congratsMessage");
    pane.getChildren().addAll(text, playAgainButton);
    winGameScene = new Scene(pane, screenSize, screenSize);
    winGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
    handleEvents();
    return winGameScene;
  }

  private void returnToBeginning() {
    StartScreen screen = new StartScreen(currentStage);
    currentStage.setScene(screen.makeScene());
    currentStage.show();
  }
  private void handleEvents() {
    playAgainButton.setOnAction(event -> {
      returnToBeginning();
    });
  }
}

package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * This class represents the screen that is displayed when the game is won.
 *
 * @author James Qu
 */
public class EndGameScreen extends SceneCreator {
  private Scene endGameScene;
  private Pane pane;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;
  private Stage currentStage;
  private ResourceBundle resources;
  private Button playAgainButton;
  private ResourceBundle constants;
  private VBox buttons;
  private String css;
  private boolean won;

  public EndGameScreen(Stage stage, boolean win) {
    this.resources = getLabels();
    this.constants = getConstants();
    this.currentStage = stage;
    this.styles = getStyles();
    this.screenSize = getScreenSize();
    this.won = win;
  }

  /**
   * Method for generating the scene for when the game ends
   * @return the scene
   */
  @Override
  public Scene makeScene() {
    this.pane = new StackPane();
    pane.setId("EndGameScreen");
    playAgainButton = new Button(resources.getString("playAgainButton"));
    playAgainButton.setId("playAgainButton");
    determineScreenType(won);
    buttons = new VBox();
    buttons.getChildren().addAll(text, playAgainButton);
    buttons.setAlignment(Pos.CENTER);
    pane.getChildren().add(buttons);
    endGameScene = new Scene(pane, screenSize, screenSize);
    endGameScene.getStylesheets().add(styles.getString(css));
    handleEvents();
    return endGameScene;
  }

  /**
   * Returns the scene to the start screen and displays that screen instead
   */
  private void returnToBeginning() {
    StartScreen screen = new StartScreen(currentStage);
    currentStage.setScene(screen.makeScene());
    currentStage.show();
  }

  /**
   * Handles all of the user interface events in this screen
   */
  private void handleEvents() {
    playAgainButton.setOnAction(event -> {
      returnToBeginning();
    });
  }

  /**
   * Used to determine whether to implement a winning screen or a losing screen to
   * show end of the game.
   */
  private void determineScreenType(boolean win) {
    if (win) {
      text = new Text(resources.getString("winMessage"));
      css = "winScreenCSS";
    }
    else {
      text = new Text(resources.getString("loseMessage"));
      css = "loseScreenCSS";
    }
  }
}

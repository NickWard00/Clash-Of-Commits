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
import ooga.controller.HighScoreParser;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class represents the screen that is displayed when the game is won.
 *
 * @author James Qu and Nick Ward
 */
public class EndGameScreen extends SceneCreator {
  private Scene endGameScene;
  private Pane pane;
  private Text text;
  private ResourceBundle styles;
  private int screenSize;
  private Stage currentStage;
  private ResourceBundle labels;
  private Button playAgainButton;
  private ResourceBundle constants;
  private int currentScore;
  private VBox buttons;
  private String myGameType;
  private String css;
  private boolean won;
  private Text scoreText;

  public EndGameScreen(Stage stage, ResourceBundle labels, int score, String gameType, boolean win) {
    this.labels = labels;
    this.currentScore = score;
    this.constants = getConstants();
    this.currentStage = stage;
    this.styles = getStyles();
    this.screenSize = getScreenSize();
    this.myGameType = gameType;
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
    determineScreenType(won);
    playAgainButton = new Button(labels.getString("playAgainButton"));
    playAgainButton.setId("playAgainButton");
    buttons = new VBox();
    buttons.getChildren().addAll(text, scoreText, playAgainButton);
    buttons.setAlignment(Pos.CENTER);
    pane.getChildren().add(buttons);
    handleEvents();
    endGameScene = new Scene(pane, screenSize, screenSize);
    endGameScene.getStylesheets().add(styles.getString(css));
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
      HighScoreParser highScoreParser = new HighScoreParser(labels.getString("highScoreFile"));
      highScoreParser.setHighScores(myGameType.replaceAll(" ", ""), currentScore);
      int newScore = highScoreParser.getHighScores().get(myGameType.replaceAll(" ", ""));
      if (newScore == currentScore) {
        scoreText = new Text(String.format("%s %s", labels.getString("newHighScore"), currentScore));
      } else {
        scoreText = new Text(String.format("%s %s", labels.getString("score"), currentScore));
      }
      text = new Text(labels.getString("winMessage"));
      css = "winScreenCSS";
    }
    else {
      scoreText = new Text(String.format("%s %s", labels.getString("score"), currentScore));
      text = new Text(labels.getString("loseMessage"));
      css = "loseScreenCSS";
    }
  }
}

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

  public EndGameScreen(Stage stage) {
    this.resources = getLabels();
    this.constants = getConstants();
    this.currentStage = stage;
    this.styles = getStyles();
    this.screenSize = getScreenSize();
  }

  //TODO: Refactor the constants out
  @Override
  public Scene makeScene() {
    this.pane = new StackPane();
    pane.setId("EndGameScreen");
    playAgainButton = new Button(resources.getString("playAgainButtonWin"));
    playAgainButton.setId("playAgainButton");
    text = new Text("Congratulations!!!");
//    text = new Text("You have lost");
    text.setId("message");
    buttons = new VBox();
    buttons.getChildren().addAll(text, playAgainButton);
    buttons.setAlignment(Pos.CENTER);
    pane.getChildren().add(buttons);
    endGameScene = new Scene(pane, screenSize, screenSize);
    endGameScene.getStylesheets().add(styles.getString("winScreenCSS"));
//    endGameScene.getStylesheets().add(styles.getString("loseScreenCSS"));
    handleEvents();
    return endGameScene;
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

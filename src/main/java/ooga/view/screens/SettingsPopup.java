package ooga.view.screens;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.controller.Controller;

import java.util.ResourceBundle;

import static ooga.view.screens.SceneCreator.constants;
import static ooga.view.screens.SceneCreator.styles;

/**
 * @author Melanie Wang
 */

/**
 * Popup screen used for pulling up settings. Contains CSV selection, quitting the game, and
 * save game functions.
 */
public class SettingsPopup extends VBox {
    private ResourceBundle labels;

    private Button saveGame;
    private Button quitGame;
    private ComboBox cssSelector;
    private Stage stage;

    private Stage saveStage;

    private Controller controller;

    private MainGameScreen main;
    private int popupSize = Integer.parseInt(constants.getString("popupSize"));

    /**
     * Constructor for the settings popup
     * @param l: the labels resourcebundle that allows for language flexibility
     * @param s: the stage the settings screen should be rooted to
     * @param m: the mainGameScreen which the screen opens next to
     * @param c: the controller used to save the game
     */
    public SettingsPopup(ResourceBundle l, Stage s, MainGameScreen m, Controller c){
        main = m;
        labels = l;
        stage = s;
        controller = c;
        cssSelector = new ComboBox<>();
        cssSelector.setId("cssSelector");
        cssSelector.getItems().addAll(
                labels.getString("css1"), labels.getString("css2"),
                labels.getString("css3"));
        saveGame = new Button(labels.getString("saveGameButton"));
        quitGame = new Button(labels.getString("quitGameButton"));
        Label cssLabel = new Label(labels.getString("cssLabel"));
        this.getChildren().addAll(cssLabel, cssSelector, saveGame, quitGame);
        this.setPrefWidth(popupSize);
        this.setPrefHeight(popupSize);
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add(styles.getString("settingsPopupCSS"));
        handleEvents();

    }

    /**
     * monitors the events for the buttons added (saving the game, quitting the game, changing CSS)
     */
    private void handleEvents(){
        saveGame.setOnAction(event->{
            saveStage = new Stage();
            saveStage.initModality(Modality.APPLICATION_MODAL);
            saveStage.initOwner(stage);
            CreateSavePopup csp = new CreateSavePopup(controller, labels, saveStage);
            saveStage.setScene(csp.makeScene());
            saveStage.show();

        });

        quitGame.setOnAction(event -> {
            stage.close();
            Stage newStage = new Stage();
            StartScreen s = new StartScreen(newStage);
            newStage.setScene(s.makeScene());
            newStage.show();
            Stage toClose = (Stage)((Node) event.getSource()).getScene().getWindow();
            toClose.close();
        });
        cssSelector.setOnAction(event->{
            main.changeStyle((String) cssSelector.getValue());
        });
    }


}

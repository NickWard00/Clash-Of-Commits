package ooga.view.screens;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class SettingsPopup extends VBox {
    private ResourceBundle labels;

    private Button saveGame;
    private Button quitGame;
    private ComboBox cssSelector;
    private Stage stage;
    public SettingsPopup(ResourceBundle l, Stage s){
        labels = l;
        stage = s;
        cssSelector = new ComboBox<>();
        cssSelector.setId("cssSelector");
        cssSelector.getItems().addAll(
                labels.getString("css1"), labels.getString("css2"),
                labels.getString("css3"));
        saveGame = new Button(labels.getString("saveGameButton"));
        quitGame = new Button(labels.getString("quitGameButton"));
        this.getChildren().addAll(cssSelector, saveGame, quitGame);
        handleEvents();

    }
    public void handleEvents(){
        saveGame.setOnAction(event->{

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

        });
    }


}

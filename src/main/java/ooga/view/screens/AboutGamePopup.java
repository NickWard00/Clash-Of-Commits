package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class AboutGamePopup extends VBox {
    private ResourceBundle labels;
    private Label intro;
    private Label instructions;
    public AboutGamePopup(ResourceBundle l){
        labels = l;
        intro = new Label(labels.getString("placeholder"));
        instructions = new Label(labels.getString("placeholder1"));
        this.getChildren().addAll(intro, instructions);
    }

}

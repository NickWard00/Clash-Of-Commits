package ooga.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
//game slot and save slot extend off of this class.
//slots are like manifestations of filechoosers in a more user-friendly format.
public abstract class Slot extends VBox {

    private SubLabel title;
    private SubLabel gameType;
    private ResourceBundle labels;


    public Slot(ResourceBundle l) {
        labels = l;
    }

    public Slot(String g, ResourceBundle labels) {
        gameType = new SubLabel(labels.getString("gameType") + " " + g);
        this.getChildren().addAll(gameType);
    }


}
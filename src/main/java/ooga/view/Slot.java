package ooga.view;

import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

public abstract class Slot extends VBox {
    private ResourceBundle labels;

    public Slot(ResourceBundle l) {
        labels = l;
    }
}
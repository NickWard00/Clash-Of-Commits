package ooga.view;

import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

/**
 * Small abstract class that SaveSlot and GameSlots build upon using the basic Slot structure
 */

public abstract class Slot extends VBox {
    private ResourceBundle labels;

    public Slot(ResourceBundle l) {
        labels = l;
    }
}
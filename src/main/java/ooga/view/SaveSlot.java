package ooga.view;

import javafx.scene.control.Label;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

public class SaveSlot extends Slot{
    private SubLabel time;
    private ResourceBundle labels;
    private SubLabel gameType;
    private int slotNumber;

    /**
     * The save slot is the physical "slot" that loads and saves games. There will always be three slots.
     * @param label the resource bundle for the labels
     * @param number the number of the slot
     */
    public SaveSlot(ResourceBundle label, int number){
        super(label);
        labels = label;
        slotNumber = number;
        time = new SubLabel(labels.getString("time"));
        gameType = new SubLabel(labels.getString("gameType"));
        this.getChildren().addAll(gameType,time);
        this.getStyleClass().add("SaveSlot");
    }

    /**
     * Gets the save slot number
     * @return the save slot number
     */
    public int getNumber() {
        return slotNumber;
    }
}

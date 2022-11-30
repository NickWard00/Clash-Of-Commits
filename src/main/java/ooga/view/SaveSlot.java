package ooga.view;

import javafx.scene.control.Label;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */
//the save slot is the physical "slot" that loads and saves games. there will always be three slots.
public class SaveSlot extends Slot{

    private SubLabel time;

    private ResourceBundle labels;
    private SubLabel gameType;

    private int slotNumber;


    public SaveSlot(ResourceBundle l, int number){
        super(l);
        labels = l;
        slotNumber = number;
        time = new SubLabel(labels.getString("time"));
        gameType = new SubLabel(labels.getString("gameType"));
        this.getChildren().addAll(gameType,time);
        this.getStyleClass().add("SaveSlot");
    }

    public void save(){
        //eventually save game files from this method.
    }
}

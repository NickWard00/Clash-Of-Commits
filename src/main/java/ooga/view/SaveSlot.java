package ooga.view;

import javafx.scene.control.Label;
import ooga.controller.SaveFileParser;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * @author Melanie Wang
 */

public class SaveSlot extends Slot{
    private SubLabel time;

    private String timeContent;
    private ResourceBundle labels;

    private String gameTypeContent;
    private SubLabel gameType;

    private SubLabel mapName;

    private String mapNameContent;

    private int slotNumber;
    private SaveFileParser saveFileParser= new SaveFileParser();

    /**
     * The save slot is the physical "slot" that loads and saves games. There will always be three slots.
     * @param label the resource bundle for the labels
     * @param number the number of the slot
     */
    public SaveSlot(ResourceBundle label, int number){
        super(label);
        labels = label;
        slotNumber =number;
        saveFileParser.loadSaveInformation(number);
        timeContent = saveFileParser.getTimeDate();
        gameTypeContent = saveFileParser.getGameType();
        mapNameContent = saveFileParser.getMapName();
        time = new SubLabel(String.format("%s %s",labels.getString("time"), timeContent));
        gameType = new SubLabel(String.format("%s %s", labels.getString("gameType"), gameTypeContent));
        mapName = new SubLabel(String.format("%s %s", labels.getString("mapName"), mapNameContent));
        this.getChildren().addAll(gameType,mapName,time);
        this.getStyleClass().add("SaveSlot");
    }

    /**
     * Gets the save slot number
     * @return the save slot number
     */
    public int getNumber() {
        return slotNumber;
    }

    /**
     * Gets game type
     * @return game type
     */
    public String getGameType(){
        return gameTypeContent;
    }
}


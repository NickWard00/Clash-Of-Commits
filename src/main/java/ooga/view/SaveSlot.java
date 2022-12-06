package ooga.view;

import javafx.scene.control.Label;
import ooga.controller.SaveFileParser;

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

    private SubLabel mapName;

    private int slotNumber;
    private SaveFileParser saveFileParser= new SaveFileParser();


    public SaveSlot(ResourceBundle l, int num){
        super(l);
        labels = l;
        slotNumber =num;
        saveFileParser.loadSaveInformation(num);
        time = new SubLabel(String.format("%s %s",labels.getString("time"),saveFileParser.getTimeDate()));
        gameType = new SubLabel(String.format("%s %s", labels.getString("gameType"), saveFileParser.getGameType()));
        mapName = new SubLabel(String.format("%s %s", labels.getString("mapName"), saveFileParser.getMapName()));
        this.getChildren().addAll(gameType,mapName,time);
        this.getStyleClass().add("SaveSlot");
    }

    public int getNumber(){
        return slotNumber;
    }

    public void save(){
        //eventually save game files from this method.
    }
}

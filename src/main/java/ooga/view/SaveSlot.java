package ooga.view;

import javafx.scene.control.Label;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

//the save slot is the physical "slot" that loads and saves games. there will always be three slots.
public class SaveSlot extends Slot{

    private SubLabel time;

    private ResourceBundle labels;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime currentTime = LocalDateTime.now();

    private Label rank;
    private SubLabel gameType;

    //for the default save slot with no save inside
    public SaveSlot(ResourceBundle l, String r){
        super(l);
        labels = l;
        time = new SubLabel(labels.getString("defaultTime"));
        rank = new Label(labels.getString("defaultRank")+" "+r);
        gameType = new SubLabel(labels.getString("defaultGameType"));
        this.getChildren().addAll(rank,gameType,time);
    }

    public void save(){
        time.setText(labels.getString("time")+" "+dtf.format(currentTime));
        //eventually save game files from this method.
    }
}

package ooga.view;


import javafx.scene.control.Label;

/**
 * @author Melanie Wang
 */

//just a mini object for smaller labels (allows me to differentiate in CSS)
public class SubLabel extends Label {
    public SubLabel(String text){
        this.setText(text);
    }
}

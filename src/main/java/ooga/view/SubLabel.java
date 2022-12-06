package ooga.view;


import javafx.scene.control.Label;

/**
 * @author Melanie Wang
 */
public class SubLabel extends Label {
    /**
     * Just a mini object for smaller labels (allows me to differentiate in CSS)
     * @param text the text of the label
     */
    public SubLabel(String text){
        this.setText(text);
    }
}

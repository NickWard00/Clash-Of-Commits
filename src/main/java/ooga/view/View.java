package ooga.view;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class View {
    private Scene scene;

    public View(){

    }
    public void step(double elapsedTime){

    }

    public Scene setupGame(){
        BorderPane root = new BorderPane();
        scene = new Scene(root);
        scene.setOnKeyPressed(e->getKeyInput(e.getCode()));
        return scene;
    }

    public void getKeyInput(KeyCode key){
        switch(key){
            case LEFT -> {

            }
            case RIGHT -> {

            }
            case DOWN -> {

            }
            case UP -> {

            }
        }
    }
}

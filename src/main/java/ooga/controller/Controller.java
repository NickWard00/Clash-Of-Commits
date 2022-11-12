package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.view.View;

public class Controller {
    private Timeline animation;
    private View view;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public Controller(){
        View view = new View();
    }
    public void startAnimation(){
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY),
                e->step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double elapsedTime){
        view.step(elapsedTime);
    }



}

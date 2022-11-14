package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.model.Entity;
import ooga.model.Model;
import ooga.model.HitBox.HitBox;
import ooga.view.MapWrapper;
import ooga.view.View;

import java.util.List;

public class Controller {
    private Timeline animation;
    private View view;
    private MapWrapper mapWrapper;
    private List<Entity> myEntities;
    private List<HitBox> myHitBoxes;
    private String mapName;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Model myModel;
    public Controller(Stage stage){
        view = new View(stage);
        // TODO: Change this so that maps can be chosen instead of hard coded here
        mapName = "MainMap";
        parseData(mapName);
        myModel = new Model(this);
    }
    public void startAnimation(){
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY),
                e->step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double elapsedTime) {
        view.step(elapsedTime);
    }

    private void parseData(String map) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
        myEntities = entityMapParser.getEntities();
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    public List<Entity> getEntities() {
        return myEntities;
    }



}

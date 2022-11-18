package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.model.Entity;
import ooga.model.Model;
import ooga.model.hitBox.HitBox;
import ooga.view.MapWrapper;
import ooga.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private Timeline animation;
    private View view;
    private MapWrapper mapWrapper;

    private List<HitBox> myHitBoxes;
    private String mapName;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Model myModel;
    private Map<String, EntityView> myViewEntities;
    private Map<String, Entity> myModelEntities;
    private boolean playingGame;
    private boolean choosingGame; //some sort of variable to control what is active at any given moment
    public Controller(Stage stage, String mapName){
        this.mapName = mapName;
        myViewEntities = new HashMap<>();
        initializeModel(mapName);
        view = new View(stage, this);
    }

    private void initializeModel(String mapName) {
        parseData(mapName);
        myModel = new Model(this);
    }

    public void startAnimation(){
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e->step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double elapsedTime) {
        view.step(elapsedTime);
        updateEntityPosition();
    }

    private void updateEntityPosition() {
        for (String entityName : myModelEntities.keySet()) {
            Entity modelEntity = myModelEntities.get(entityName);
            EntityView viewEntity = myViewEntities.get(entityName);
            viewEntity.setX(modelEntity.coordinates().get(0));
            viewEntity.setY(modelEntity.coordinates().get(1));
        }
    }

    private void parseData(String map) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        Map<Integer, String> stateToImageMap = mapParser.getStateToImageMap();
        mapWrapper.setStateToImageMap(stateToImageMap);

        EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
        myModelEntities = entityMapParser.getEntities();

        setupViewEntities();
    }

    private void setupViewEntities() {
        myModelEntities.forEach((key, value) -> {
            myViewEntities.put(key, createViewEntity(value));
        });
    }

    private EntityView createViewEntity(Entity entity){
        Map<String, String> entityAttributes = entity.getMyAttributes();
        String imageName = entityAttributes.get("Name");
        double xPos = Double.parseDouble(entityAttributes.get("XPosition"));
        double yPos = Double.parseDouble(entityAttributes.get("YPosition"));
        int size = Integer.parseInt(entityAttributes.get("Size"));
        String spriteLocation = entityAttributes.get("Sprites");
        String startingDirection = entityAttributes.get("Direction");
        return new EntityView(spriteLocation, startingDirection, imageName, xPos, yPos, size, size);
    }

    public Map<String, EntityView> getViewEntities(){
        return myViewEntities;
    }

    private void removeEntity(String entityName){
        myModelEntities.remove(entityName);
        myViewEntities.remove(entityName);
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    public Map<String, Entity> getModelEntities() {
        return myModelEntities;
    }
}

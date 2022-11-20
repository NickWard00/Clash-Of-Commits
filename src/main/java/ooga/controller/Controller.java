package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.model.Entity;
import ooga.model.Model;
import ooga.model.hitBox.HitBox;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.EntityView;
import ooga.view.MapWrapper;
import ooga.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private Timeline animation;
    private View myView;
    private MapWrapper mapWrapper;

    private List<HitBox> myHitBoxes;
    private String mapName;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Model myModel;
    private Map<String, EntityView> myViewEntities;
    private Map<String, Entity> myModelEntities;
    private String myMainHeroName;
    private Map<KeyCode, String> actions = Map.of(
            KeyCode.UP, "moveUp",
            KeyCode.DOWN, "moveDown",
            KeyCode.RIGHT, "moveRight",
            KeyCode.LEFT, "moveLeft",
            KeyCode.SPACE, "attack"
    );
    private boolean playingGame;
    private boolean choosingGame; //some sort of variable to control what is active at any given moment
    public Controller(Stage stage, String mapName){
        this.mapName = mapName;
        myViewEntities = new HashMap<>();
        initializeModel(mapName);
        myView = new View(stage, this);
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
        myView.step(elapsedTime);
        updateEntityPosition(elapsedTime);
    }

    private void updateEntityPosition(double elapsedTime) {
        for (String entityName : myModelEntities.keySet()) {
            Entity modelEntity = myModelEntities.get(entityName);
            EntityView viewEntity = myViewEntities.get(entityName);
            modelEntity.move(elapsedTime);
            viewEntity.setX(modelEntity.coordinates().get(0));
            viewEntity.setY(modelEntity.coordinates().get(1));
        }
    }

    private void parseData(String map) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        Map<Integer, String> stateToImageMap = mapParser.getStateToImageMap();
        mapWrapper.setStateToImageMap(stateToImageMap);
        mapWrapper.setVisualProperties(mapParser.getMapProperties());

        EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
        myModelEntities = entityMapParser.getEntities();
        // loop through all model entities and find the name of the MainHero entity and return the name of that entity
        for (Entity entity : myModelEntities.values()) {
            if (entity.getMyAttributes().get("EntityType").equals("MainHero")) {
                myMainHeroName = entity.getMyAttributes().get("Name");
            }
        }
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

    public void handleKeyPress(KeyCode keyCode){
        if (keyCode.isArrowKey()){
            try {
                Method currentAction = this.getClass().getDeclaredMethod(
                        actions.get(keyCode));
                currentAction.invoke(this);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void handleKeyRelease(KeyCode keyCode) {
        if (keyCode.isArrowKey()) {
            try {
                Method currentAction = this.getClass().getDeclaredMethod(
                        actions.get(keyCode) + "Stop");
                currentAction.invoke(this);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void moveUpStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.NORTH_STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH_STATIONARY);
    }
    private void moveDownStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.SOUTH_STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.SOUTH_STATIONARY);
    }
    private void moveRightStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.EAST_STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.EAST_STATIONARY);
    }

    private void moveLeftStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.WEST_STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.WEST_STATIONARY);
    }

    private void moveUp() {
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.NORTH);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH);
    }

    private void moveDown() {
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.SOUTH);
        myView.changeEntityState(myMainHeroName, DirectionState.SOUTH);
    }

    private void moveLeft(){
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.WEST);
        myView.changeEntityState(myMainHeroName, DirectionState.WEST);
    }
    private void moveRight(){
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.EAST);
        myView.changeEntityState(myMainHeroName, DirectionState.EAST);
    }

    public String getMainHeroName() {
        if (myMainHeroName == null) {
            throw new IllegalStateException("Main Hero not found");
        } else {
            return myMainHeroName;
        }
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    public Map<String, Entity> getModelEntities() {
        return myModelEntities;
    }
}

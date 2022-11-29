package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.model.Entity;
import ooga.model.Model;
import ooga.model.attack.Attack;
import ooga.model.obstacle.Obstacle;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.BlockView;
import ooga.view.AttackView;
import ooga.view.EntityView;
import ooga.view.MapWrapper;
import ooga.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Nick Ward, Melanie Wang
 */
public class Controller {
    private Timeline animation;
    private View myView;
    private Model myModel;
    private MapWrapper mapWrapper;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static Map<String, EntityView> myViewEntities;
    private static Map<String, Entity> myModelEntities;
    private static Map<Integer, Attack> myModelAttacks;
    private Map<List<Double>, Obstacle> myModelObstacles;
    private Map<List<Double>, BlockView> myViewObstacles;
    private static Map<Integer, AttackView> myViewAttacks;
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

    /**
     * Constructor for the controller, which initializes the model and view and sets up map based on map name
     * @param stage the stage to display the game on
     * @param mapName the name of the map to be displayed
     * @param labels the resource bundle containing the labels for the game
     */
    public Controller(Stage stage, String mapName, ResourceBundle labels){
        myViewEntities = new HashMap<>();
        myModelAttacks = new HashMap<>();
        myModelObstacles = new HashMap<>();
        myViewObstacles = new HashMap<>();
        myViewAttacks = new HashMap<>();
        initializeModel(mapName);
        myView = new View(stage, this, labels);
    }

    /**
     * Initializes the model and parses all the data based on the map name given
     * @param mapName the name of the map to be displayed
     */
    private void initializeModel(String mapName) {
        parseData(mapName);
        myModel = new Model(this);
    }

    /**
     * Begins the animation of the game
     */
    public void startAnimation(){
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e->step(SECOND_DELAY)));
        animation.play();
    }

    public void pauseAnimation(){
        animation.pause();
    }

    public void playAnimation(){
        animation.play();
    }

    /**
     * Steps the animation of the game
     * @param elapsedTime the time elapsed since the last step
     */
    private void step(double elapsedTime) {
        myView.step(elapsedTime);
        updateEntityPosition(elapsedTime);
        updateAttackPosition(elapsedTime);
    }

    /**
     * Updates the position of all entities in the game with their view and model counterparts
     * @param elapsedTime the time elapsed since the last step
     */
    private void updateEntityPosition(double elapsedTime) {
        for (String entityName : myModelEntities.keySet()) {
            Entity modelEntity = myModelEntities.get(entityName);
            EntityView viewEntity = myViewEntities.get(entityName);
            List<Double> newPosition = modelEntity.move(elapsedTime);
            viewEntity.setX(newPosition.get(0));
            viewEntity.setY(newPosition.get(1));
        }
    }

    /**
     * Updates the position of all attacks in the game with their view and model counterparts
     * @param elapsedTime the time elapsed since the last step
     */
    private void updateAttackPosition(double elapsedTime) {
        myModelAttacks.keySet().iterator().forEachRemaining(attackID -> {
            Attack attackModel = myModelAttacks.get(attackID);
            List<Double> newCoordinates = attackModel.move(elapsedTime);
            if (myViewAttacks.containsKey(attackID)) {
                AttackView attackView = myViewAttacks.get(attackID);
                attackView.setX(newCoordinates.get(0) - attackView.getFitWidth()/2);
                attackView.setY(newCoordinates.get(1) - attackView.getFitHeight()/2);
            } else {
                AttackView newAttackView = createViewAttack(attackModel);
                myViewAttacks.put(attackID, newAttackView);
                myView.getGameScreen().addAttackToScene(newAttackView);
            }
        });
    }

    /**
     * Parses all the data in the data files based on a certain map name
     * @param map the name of the map to be parsed
     */
    private void parseData(String map) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        Map<Integer, String> stateToImageMap = mapParser.getStateToImageMap();
        mapWrapper.setStateToImageMap(stateToImageMap);
        mapWrapper.setVisualProperties(mapParser.getMapProperties());

        EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
        myModelEntities = entityMapParser.getEntities();

        for (Entity entity : myModelEntities.values()) {
            if (entity.getMyAttributes().get("EntityType").equals("MainHero")) {
                myMainHeroName = entity.getMyAttributes().get("Name");
            }
        }

        setupViewEntities();
        setupModelObstacles(mapParser);
    }

    /**
     * Sets up the view entities based on the model entities
     */
    private void setupModelObstacles(MapParser parser) {
        for (int r=0; r<mapWrapper.getRowSize(0); r++) {
            for (int c=0; c<mapWrapper.getColumnSize(); c++) {
                try {
                    int thisState = mapWrapper.getState(c,r);
                    ResourceBundle obstacleBundle = ResourceBundle.getBundle("ResourceBundles.Obstacle");
                    String obstacleStateString = parser.getObstacleStateMap().get(thisState);
                    Class obstacleClass = Class.forName(obstacleBundle.getString(obstacleStateString));
                    makeObstacle(obstacleClass, r, c);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Sets up the view entities based on the model entities
     */
    private void setupViewEntities() {
        myModelEntities.forEach((key, value) -> {
            myViewEntities.put(key, createViewEntity(value));
        });
    }

    /**
     * Creates a view entity based on a model entity
     * @param entity the model entity to be converted to a view entity
     * @return the view entity created
     */
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

    /**
     * Creates a view attack based on a model attack
     * @param attack the model attack to be converted to a view attack
     * @return the view attack created
     */
    private AttackView createViewAttack(Attack attack){
        String imagePath = new AttackParser(attack.getMyEntity()).getImagePath();
        // imagePath = imagePath + attack.getDirection().getDirectionString() + ".png";
        imagePath = String.format("%s%s.png", imagePath, attack.getDirection().getDirectionString());
        String attackType = attack.getClass().getSimpleName();
        double size = Double.parseDouble("" + attack.getMyAttributes().get("Size"));
        return new AttackView(imagePath, attackType, attack.getCoordinates().get(0), attack.getCoordinates().get(1), (int) size, (int) size);
    }

    private Obstacle makeObstacle(Class<? extends Obstacle> obstacleClass, double xPosition, double yPosition) {
        try {
            Obstacle newObstacle = obstacleClass.getConstructor(Double.class, Double.class).newInstance(xPosition, yPosition);
            myModelObstacles.put(Arrays.asList(xPosition, yPosition), newObstacle);
            return newObstacle;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes an entity in model and view based on the entity name
     * @param entityName
     */
    public void removeEntity(String entityName){
        myModelEntities.remove(entityName);
        myViewEntities.remove(entityName);
    }

    /**
     * Removes an attack in model and view based on the attack ID
     * @param attackID
     */
    public void removeAttack(Integer attackID) {
        myView.getGameScreen().removeAttackFromScene(myViewAttacks.get(attackID));
        myViewAttacks.remove(attackID);
        myModelAttacks.remove(attackID);
    }

    /**
     * Handles the key input press from the user that is detected in the view
     * @param keyCode
     */
    public void handleKeyPress(KeyCode keyCode){
        if (keyCode.isArrowKey()){
            try {
                Method currentAction = this.getClass().getDeclaredMethod(
                        actions.get(keyCode));
                currentAction.invoke(this);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("noMethodFound", e);
            }
        } else if (keyCode == KeyCode.SPACE) {
            myModel.attack();
        }
    }

    /**
     * Handles the key input release from the user that is detected in the view
     * @param keyCode
     */
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

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the north direction
     */
    private void moveUpStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the south direction
     */
    private void moveDownStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the west direction
     */
    private void moveLeftStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the east direction
     */
    private void moveRightStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the north direction
     */
    private void moveUp() {
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.NORTH);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the south direction
     */
    private void moveDown() {
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.SOUTH);
        myView.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the west direction
     */
    private void moveLeft(){
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.WEST);
        myView.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the east direction
     */
    private void moveRight(){
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING, DirectionState.EAST);
        myView.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.MOVING);
    }

    /**
     * Method that returns the name of the main hero, if it exists
     * @return the name of the main hero
     */
    public String getMainHeroName() {
        if (myMainHeroName == null) {
            throw new IllegalStateException("Main Hero not found");
        } else {
            return myMainHeroName;
        }
    }

    /**
     * Returns the mapWrapper
     * @return
     */
    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    /**
     * Returns the model entities
     * @return myModelEntities
     */
    public static Map<String, Entity> getModelEntities() {
        return myModelEntities;
    }

    /**
     * Returns the view entities
     * @return myViewEntities
     */
    public static Map<String, EntityView> getViewEntities() {
        return myViewEntities;
    }

    /**
     * Returns the model obstacles
     * @return myModelObstacles
     */
    public Map<List<Double>, Obstacle> getModelObstacles() {
        return myModelObstacles;
    }

    /**
     * Returns the view obstacles
     * @return myViewObstacles
     */
    public Map<List<Double>, BlockView> getViewObstacles() {
        return myViewObstacles;
    }

    /**
     * Returns the model attacks
     * @return myModelAttacks
     */
    public static Map<Integer, Attack> getModelAttacks() {
        return myModelAttacks;
    }

    /**
     * Returns the view attacks
     * @return myViewAttacks
     */
    public static Map<Integer, AttackView> getViewAttacks() {
        return myViewAttacks;
    }
}

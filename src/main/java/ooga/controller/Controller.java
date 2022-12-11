package ooga.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.model.entities.Entity;
import ooga.model.Model;
import ooga.model.attack.Attack;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import ooga.model.powerup.PowerUp;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.BlockView;
import ooga.view.AttackView;
import ooga.view.EntityView;
import ooga.view.MapWrapper;
import ooga.view.View;

import java.io.FileNotFoundException;
import java.util.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Nick Ward, Melanie Wang, Nicki Lee
 */
public class Controller {
    private static final ResourceBundle scores = ResourceBundle.getBundle(
        "ResourceBundles.Score");
    private Timeline animation;
    private View myView;
    private Model myModel;
    private MapWrapper mapWrapper;
    private static final double FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Map<String, EntityView> myViewEntities;
    private Map<String, Entity> myModelEntities;
    private Map<List<Double>, Obstacle> myModelObstacles;
    private Map<List<Integer>, PowerUp> myModelPowerUps;
    private Map<List<Integer>, BlockView> myViewPowerUps;
    private Map<List<Double>, BlockView> myViewObstacles;
    private Map<Integer, Attack> myModelAttacks;
    private Map<Integer, AttackView> myViewAttacks;
    private String myMainHeroName;
    private Map<KeyCode, String> movementActions;
    private Map<KeyCode, String> attackActions;
    private String myGameType;
    private String mapName;
    private DirectionState playerDirection;
    private int score;

    private SaveFileParser saver = new SaveFileParser();

    /**
     * Constructor for the controller, which initializes the model and view and sets up map based on map name
     * @param stage the stage to display the game on
     * @param map the name of the map to be displayed
     * @param labels the resource bundle containing the labels for the game
     */
    public Controller(Stage stage, String map, String gameType, ResourceBundle labels){
        this.myModelEntities = new HashMap<>();
        this.myViewEntities = new HashMap<>();
        this.myModelAttacks = new HashMap<>();
        this.myViewAttacks = new HashMap<>();
        this.myModelObstacles = new HashMap<>();
        this.myModelPowerUps = new HashMap<>();
        this.myViewPowerUps = new HashMap<>();
        this.movementActions = Map.of(
                KeyCode.UP, "moveUp",
                KeyCode.DOWN, "moveDown",
                KeyCode.RIGHT, "moveRight",
                KeyCode.LEFT, "moveLeft",
                KeyCode.W, "moveUp",
                KeyCode.S, "moveDown",
                KeyCode.D, "moveRight",
                KeyCode.A, "moveLeft",
                KeyCode.SHIFT, "sprint"
        );
        this.attackActions = Map.of(
                KeyCode.SPACE, "attack",
                KeyCode.Z, "attack"
        );
        this.mapName = map;
        this.myGameType = gameType;
        this.score = Integer.parseInt(scores.getString("initialScore"));

        initializeModel();

        setupViewPowerUps();
        myView = new View(stage, this, myGameType, labels);
        myView.setViewPowerUps();
        myViewObstacles = myView.getViewObstacles();
    }

    /**
     * Initializes the model and parses all the data based on the map name given
     */
    private void initializeModel() {
        boolean loadSave = false;
        if (mapName.startsWith("Save")) {
            loadGame(Integer.parseInt(String.valueOf(mapName.charAt(mapName.length()-1))));
            loadSave = true;
        }
        parseData(mapName, loadSave);
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

    /**
     * Stops the animation of the game
     */
    public void pauseAnimation(){
        animation.pause();
    }

    /**
     * Resumes the animation of the game
     */
    public void playAnimation(){
        animation.play();
    }

    /**
     * Stops the animation of the game
     */
    public void stopAnimation() {
        animation.stop();
    }

    /**
     * Steps the animation of the game
     * @param elapsedTime the time elapsed since the last step
     */
    private void step(double elapsedTime) {
        myView.step(elapsedTime);
        updateEntityPosition(elapsedTime);
        updateAttackPosition(elapsedTime);
        myModel.checkForNewAttacks();
        updatePlayerHealth();
        updatePlayerScore();
    }

    /**
     * Updates the position of all entities in the game with their view and model counterparts
     * @param elapsedTime the time elapsed since the last step
     */
    private void updateEntityPosition(double elapsedTime) {
        List<EntityView> nowDead = new ArrayList<>();
        for (String entityName : myModelEntities.keySet()) {
            Entity modelEntity = myModelEntities.get(entityName);
            EntityView viewEntity = myViewEntities.get(entityName);
            if (modelEntity.getHp() > 0) {
                List<Double> newPosition = modelEntity.move(elapsedTime);
                viewEntity.setX(newPosition.get(0));
                viewEntity.setY(newPosition.get(1));
            } else {
                nowDead.add(viewEntity);
                score += Integer.parseInt(scores.getString("enemy"));
            }
        }
        for (EntityView deadEntityView : nowDead) {
            removeEntity(deadEntityView.getKey());
        }
    }

    /**
     * Updates the position of all attacks in the game with their view and model counterparts
     * @param elapsedTime the time elapsed since the last step
     */
    private void updateAttackPosition(double elapsedTime) {
        List<Integer> attackIDs = myModelAttacks.keySet().stream().toList();
        for (Integer attackID : attackIDs) {
            Attack modelAttack = myModelAttacks.get(attackID);
            if (myViewAttacks.containsKey(attackID)) {
                AttackView viewAttack = myViewAttacks.get(attackID);
                List<Double> newPosition = modelAttack.move(elapsedTime);
                viewAttack.setX(newPosition.get(0) - viewAttack.getFitWidth() / 2);
                viewAttack.setY(newPosition.get(1) - viewAttack.getFitHeight() / 2);
            } else {
                AttackView newAttackView = createViewAttack(modelAttack, attackID);
                myViewAttacks.put(attackID, newAttackView);
                myView.getGameScreen().addAttackToScene(newAttackView);
            }
        }
    }

    /**
     * Checks if any obstacles have been destroyed and removes them from the game
     */
    private void updateObstacles() {
        for (List<Double> coordinate : myViewObstacles.keySet()) {
            if (myModelObstacles.get(coordinate).getClass() == DestroyableWall.class) {
                if (!((DestroyableWall) myModelObstacles.get(coordinate)).determineOnScreen()) {
                    removeObstacle(coordinate);
                }
            }
        }
    }

    /**
     * Parses all the data in the data files based on a certain map name
     * @param map the name of the map to be parsed
     */
    private void parseData(String map, boolean loadSave) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        Map<Integer, String> stateToImageMap = mapParser.getStateToImageMap();
        mapWrapper.setStateToImageMap(stateToImageMap);
        mapWrapper.setVisualProperties(mapParser.getMapProperties());
        mapWrapper.setObstacleStateMap(mapParser.getObstacleStateMap());

        if (!loadSave) {
            EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
            myModelEntities = entityMapParser.getEntities();
            PowerUpParser powerUpMapParser = new PowerUpParser();
            myModelPowerUps = powerUpMapParser.allPowerUps();
        }

        for (Entity entity : myModelEntities.values()) {
            if (entity.getMyAttributes().get("EntityType").equals("MainHero") || entity.getMyAttributes().get("EntityType").equals("Link")) {
                myMainHeroName = entity.getMyAttributes().get("Name");
            }
        }
        setupViewEntities();
        setupModelObstacles(mapParser);
        Attack.setMyController(this);
    }

    /**
     * Sets up the model obstacles based on the map parser
     * @param num
     */
    public void saveGame(int num){
        saver.saveGame(num, myModelEntities, mapName, myGameType,String.valueOf(myModelEntities.get(myMainHeroName).getHp()), String.valueOf(score));
    }

    /**
     * saves game to online database (slot 4)
     * @param num the number of the slot
     */
    public void saveGametoWeb(int num) throws FileNotFoundException {
        saver.saveGameToWeb(num, myModelEntities, mapName, myGameType, String.valueOf(myModelEntities.get(myMainHeroName).getHp()), String.valueOf(score));
    }

    /**
     * Loads a game from a save file
     * @param num
     */
    public void loadGame(int num){
        saver.loadGame(num);
        this.myGameType = saver.getGameType();

        myModelEntities = saver.getEntities();
        for (Entity entity : myModelEntities.values()) {
            if (entity.getMyAttributes().get("EntityType").equals("MainHero") || entity.getMyAttributes().get("EntityType").equals("Link")) {
                myMainHeroName = entity.getMyAttributes().get("Name");
            }
        }
        myModelEntities.get(myMainHeroName).setHP(Integer.parseInt(saver.getHeroHP()));
        score = Integer.parseInt(saver.getSaveScore());
        mapName = saver.getMapName();
    }

    /**
     * Sets up the view entities based on the model entities
     */
    private void setupModelObstacles(MapParser parser) {
        for (int row = 0; row < mapWrapper.getRowSize(0); row++) {
            for (int col = 0; col < mapWrapper.getColumnSize(); col++) {
                try {
                    int thisState = mapWrapper.getState(col,row);
                    if (mapWrapper.getObstacleFromState(thisState).contains("Wall") || mapWrapper.getObstacleFromState(thisState).contains("PowerUp")) {
                        ResourceBundle obstacleBundle = ResourceBundle.getBundle("ResourceBundles.Obstacle");
                        String obstacleStateString = parser.getObstacleStateMap().get(thisState);
                        Class obstacleClass = Class.forName(obstacleBundle.getString(obstacleStateString));
                        makeObstacle(obstacleClass, row, col);
                    }
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("classNotFound", e);
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
     * Sets up the view powerups based on the model powerups
     */
    public void setupViewPowerUps() {
        myModelPowerUps.forEach((key, value) -> {
            myViewPowerUps.put(key, createViewPowerUp(value));
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

    private BlockView createViewPowerUp(PowerUp powerup) {
        int x = powerup.getKey().get(0);
        int y = powerup.getKey().get(1);
        int size = mapWrapper.getVisualProperties().get(0).intValue();
        String path = powerup.getImagePath();
        return new BlockView(x,y,size,0, path);
    }

    /**
     * Creates a view attack based on a model attack
     * @param attack the model attack to be converted to a view attack
     * @return the view attack created
     */
    private AttackView createViewAttack(Attack attack, int attackID){
        String imagePath = new AttackParser(attack.getMyEntity()).getImagePath();
        imagePath = String.format("%s%s.png", imagePath, attack.getDirection().getDirectionString());
        String attackType = attack.getClass().getSimpleName();
        double size = Double.parseDouble("" + attack.getMyAttributes().get("Size"));
        return new AttackView(imagePath, attackType, attack.coordinates().get(0), attack.coordinates().get(1), (int) size, (int) size, attackID);
    }

    /**
     * Creates an obstacle based on a class and a location
     * @param obstacleClass the class of the obstacle to be created
     * @param xPosition the x position of the obstacle
     * @param yPosition the y position of the obstacle
     */
    private Obstacle makeObstacle(Class<? extends Obstacle> obstacleClass, double xPosition, double yPosition) {
        try {
            Obstacle newObstacle = obstacleClass.getConstructor(Double.class, Double.class).newInstance(xPosition, yPosition);
            myModelObstacles.put(Arrays.asList(xPosition, yPosition), newObstacle);
            return newObstacle;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException("classNotFound", e);
        }
    }

    /**
     * Removes an entity in model and view based on the entity name
     * @param entityName
     */
    public void removeEntity(String entityName){
        if (entityName != myMainHeroName) {
            myView.getGameScreen().removeEntityFromScene(entityName);
            myModelEntities.remove(entityName);
            myViewEntities.remove(entityName);
        }
        else {
            myView.getGameScreen().nextScene();
            stopAnimation();
        }
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
     * Removes an obstacle in model and view based on the obstacle location
     * @param coordinate
     */
    public void removeObstacle(List<Double> coordinate) {
        myView.getGameScreen().removeObstacleFromScene(myViewObstacles.get(coordinate));
        myViewObstacles.remove(coordinate);
        myModelObstacles.remove(coordinate);
    }

    /**
     * Translates the collision of an entity with an obstacle
     * @param viewObject1
     * @param viewObject2
     */
    public void passCollision(Object viewObject1, Object viewObject2) {
        //updateObstacles();
        CollisionHandler handler = new CollisionHandler(getViewModelMaps());
        Map<?,?> modelMap1 = getCorrectModelMap(viewObject1);
        Map<?,?> modelMap2 = getCorrectModelMap(viewObject2);
        handler.translateCollision(viewObject1, viewObject2, modelMap1, modelMap2);
    }

    /**
     * Gives the correct model map based on the view object
     * @param obj the view object
     * @return the correct model map
     */
    private Map<?,?> getCorrectModelMap(Object obj) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("ResourceBundles.ViewToModel");
            String objType = bundle.getString(obj.getClass().getSimpleName());
            Object mapObject = Controller.class.getDeclaredMethod(String.format("getModel%s", objType)).invoke(this);
            return (Map<?,?>) mapObject;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("classNotFound", e);
        }
    }

    /**
     * Checks if the key pressed is associated with a movement or attack action and passes it into handleKeyPress appropriately
     * Calls changeHeroAttack if attack action was pressed
     * @param keyCode
     */
    public void checkKeyPress(KeyCode keyCode) {
        if (movementActions.containsKey(keyCode)) {
            handleKeyPress(movementActions.get(keyCode));
        } else if (attackActions.containsKey(keyCode)) {
            changeHeroAttack(keyCode);
            handleKeyPress(attackActions.get(keyCode));
        }
    }

    /**
     * Changes the type of attack associated with the hero
     * @param keyCode
     */
    private void changeHeroAttack(KeyCode keyCode) {
        if (keyCode.isWhitespaceKey()) {
            myModelEntities.get(myMainHeroName).setAttackType("LongRange");
        } else {
            myModelEntities.get(myMainHeroName).setAttackType("ShortRange");
        }
    }

    /**
     * Handles the key input press from the user that is detected in the view
     * @param action
     */
    private void handleKeyPress(String action){
            try {
                Method currentAction = this.getClass().getDeclaredMethod(action);
                currentAction.invoke(this);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("methodNotFound", e);
            }
    }

    /**
     * Checks if the key released is associated with a movement or attack action and passes it into handleKeyRelease appropriately
     * @param keyCode
     */
    public void checkKeyRelease(KeyCode keyCode) {
        if (movementActions.containsKey(keyCode)) {
            handleKeyRelease(movementActions.get(keyCode));
        } else if (attackActions.containsKey(keyCode)) {
            handleKeyRelease(attackActions.get(keyCode));
        }
    }

    /**
     * Handles the key input release from the user that is detected in the view
     * @param action
     */
    private void handleKeyRelease(String action) {
            try {
                Method currentAction = this.getClass().getDeclaredMethod(action + "Stop");
                currentAction.invoke(this);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("illegalKeyPress", e);
            }
    }

    /**
     * Tells the model to attack and changes the state to attack
     */
    private void attack(){
        myModel.attack();
        myView.changeEntityState(myMainHeroName, playerDirection, MovementState.ATTACK);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from moving in the north direction
     */
    private void moveUpStop(){
        myModel.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.STATIONARY);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from attacking
     */
    private void attackStop(){
        myModel.changeEntityState(myMainHeroName, playerDirection, MovementState.STATIONARY);
        myView.changeEntityState(myMainHeroName, playerDirection, MovementState.STATIONARY);
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
        playerDirection = DirectionState.NORTH;
        myModel.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.MOVING);
        myView.changeEntityState(myMainHeroName, DirectionState.NORTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the south direction
     */
    private void moveDown() {
        playerDirection = DirectionState.SOUTH;
        myModel.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.MOVING);
        myView.changeEntityState(myMainHeroName, DirectionState.SOUTH, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the west direction
     */
    private void moveLeft(){
        playerDirection = DirectionState.WEST;
        myModel.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.MOVING);
        myView.changeEntityState(myMainHeroName, DirectionState.WEST, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move the hero in the east direction
     */
    private void moveRight(){
        playerDirection = DirectionState.EAST;
        myModel.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.MOVING);
        myView.changeEntityState(myMainHeroName, DirectionState.EAST, MovementState.MOVING);
    }

    /**
     * Reflection method that is called from handleKeyPress to move allow the player to sprint
     */
    private void sprint(){
        myModel.changeEntityState(myMainHeroName, MovementState.SPRINTING);
    }

    /**
     * Reflection method that is called from handleKeyRelease to stop the hero from sprinting
     */
    private void sprintStop(){
        myModel.changeEntityState(myMainHeroName, MovementState.MOVING);
    }

    /**
     * Method that returns the name of the main hero, if it exists
     * @return the name of the main hero
     */
    public String getMainHeroName() {
        if (myMainHeroName == null) {
            throw new IllegalStateException("mainHeroNotFound");
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
    public Map<String, Entity> getModelEntities() {
        return myModelEntities;
    }

    /**
     * Returns the view entities
     * @return myViewEntities
     */
    public Map<String, EntityView> getViewEntities() {
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
     * Returns the model attacks
     * @return myModelAttacks
     */
    public Map<Integer, Attack> getModelAttacks() {
        return myModelAttacks;
    }

    /**
     * Returns the view attacks
     * @return myViewAttacks
     */
    public Map<Integer, AttackView> getViewAttacks() {
        return myViewAttacks;
    }

    /**
     * Returns the view powerups
     * @return myViewPowerUps
     */
    public Map<List<Integer>, BlockView> getMyViewPowerUps() { return myViewPowerUps; }

    /**
     * Returns the corresponding map based on string input
     * @return the corresponding map
     */
    private Map<String, Map<?,?>> getViewModelMaps() {
        return Map.of("modelEntities", myModelEntities, "viewEntities", myViewEntities,
                "modelAttacks", myModelAttacks, "viewAttacks", myViewAttacks,
                "modelObstacles", myModelObstacles, "viewObstacles", myViewObstacles,
                "modelPowerUps", myModelPowerUps, "viewPowerUps", myViewPowerUps);
    }

    /**
     * updates the player's health bar on the HUD
     */
    private void updatePlayerHealth() {
        myView.updateHealth(myModelEntities.get(myMainHeroName).getHp());
    }

    /**
     * Updates the player's score on the HUD
     */
    public void updatePlayerScore() {
        myView.updateScore(this.score);
    }
}

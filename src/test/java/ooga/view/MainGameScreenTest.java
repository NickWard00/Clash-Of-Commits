package ooga.view;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.MapParser;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MainGameScreenTest extends DukeApplicationTest {
    private Map<String, String> entityAttributes;
    private String imageName;
    private double xPos;
    private double yPos;
    private int size;
    private String spriteLocation;
    private String startingDirection;
    private EntityView entityView;
    private BlockView blockView;
    private Stage myStage;
    private Controller controller;
    private ResourceBundle labels = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");

    @Override
    public void start(Stage stage) {
        this.myStage = stage;
        StartScreen ss = new StartScreen(myStage);
        myStage.setScene(ss.makeScene());
        controller = new Controller(stage,"MainMap", "", labels);

        entityAttributes = Map.of("Name", "Hero1", "XPosition", "50", "YPosition", "103", "Size", "20", "Sprites", "/sprites/hero/", "Direction", "SOUTH");
        imageName = entityAttributes.get("Name");
        xPos = Double.parseDouble(entityAttributes.get("XPosition"));
        yPos = Double.parseDouble(entityAttributes.get("YPosition"));
        size = Integer.parseInt(entityAttributes.get("Size"));
        spriteLocation = entityAttributes.get("Sprites");
        startingDirection = entityAttributes.get("Direction");
        entityView = new EntityView(spriteLocation, startingDirection, imageName, xPos, yPos, size, size);
        blockView = new BlockView((int) xPos, (int) yPos, size, 0, spriteLocation);
    }

    @Test
    void startGamePlayTest(){
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());
        map.setObstacleStateMap(mapParser.getObstacleStateMap());
        map.setVisualProperties(mapParser.getMapProperties());
        MapView mapPane = new MapView(map);
        GridPane mapGrid = mapPane.createMap();

        MainGameScreen mainGameScreen = new MainGameScreen(myStage, controller);
        mainGameScreen.startGamePlay(mapGrid, Map.of("Joe Mama", entityView), Map.of(Arrays.asList((int)xPos, (int)yPos), blockView));
        assertTrue(mainGameScreen.isPlaying());
    }
    @Test
    void startGamePlayErrorTest(){
        MainGameScreen mainGameScreen = new MainGameScreen(myStage, controller);
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());
        map.setObstacleStateMap(mapParser.getObstacleStateMap());
        map.setVisualProperties(mapParser.getMapProperties());
        MapView mapPane = new MapView(map);
        GridPane mapGrid = mapPane.createMap();

        try {
            mainGameScreen.makeScene();
            mainGameScreen.startGamePlay(mapGrid, Map.of("Joe Mama", entityView), Map.of(Arrays.asList((int)xPos, (int)yPos), blockView));
        } catch(Exception e){
            //any other exception should be thrown by US
            assertEquals("Incorrect GamePlay", e.getMessage());
        }
    }
}
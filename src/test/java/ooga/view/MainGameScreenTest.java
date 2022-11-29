package ooga.view;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.controller.MapParser;
import ooga.model.state.DirectionState;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;

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
    private Stage myStage;

    @Override
    public void start(Stage stage) {
        this.myStage = stage;
        StartScreen ss = new StartScreen(myStage);
        myStage.setScene(ss.makeScene());


        entityAttributes = Map.of("Name", "Hero1", "XPosition", "50", "YPosition", "103", "Size", "20", "Sprites", "/sprites/hero/", "Direction", "SOUTH");
        imageName = entityAttributes.get("Name");
        xPos = Double.parseDouble(entityAttributes.get("XPosition"));
        yPos = Double.parseDouble(entityAttributes.get("YPosition"));
        size = Integer.parseInt(entityAttributes.get("Size"));
        spriteLocation = entityAttributes.get("Sprites");
        startingDirection = entityAttributes.get("Direction");
        entityView = new EntityView(spriteLocation, startingDirection, imageName, xPos, yPos, size, size);
    }

    @Test
    void startGamePlayTest(){
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());
        map.setVisualProperties(mapParser.getMapProperties());
        MapView mapPane = new MapView(map);
        GridPane mapGrid = mapPane.createMap();

        MainGameScreen mainGameScreen = new MainGameScreen(myStage);
        mainGameScreen.startGamePlay(mapGrid, Map.of("Joe Mama", entityView));
        mainGameScreen.makeScene();
        mainGameScreen.startGamePlay(mapGrid, Map.of("Joe Mama", entityView));
        assertTrue(mainGameScreen.isPlaying());
    }
    @Test
    void startGamePlayErrorTest(){
        MainGameScreen mainGameScreen = new MainGameScreen(myStage);
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());
        MapView mapPane = new MapView(map);
        GridPane mapGrid = mapPane.createMap();

        try{
            mainGameScreen.makeScene();
            mainGameScreen.startGamePlay(mapGrid, Map.of("Joe Mama", entityView));
        }catch(Exception e){
            //any other exception should be thrown by US
            assertEquals("Incorrect GamePlay", e.getMessage());
        }
    }
}
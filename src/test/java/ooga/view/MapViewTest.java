package ooga.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ooga.controller.MapParser;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MapViewTest extends DukeApplicationTest {
    private static final String MAP_BLOCKS_PROPERTIES = "ResourceBundles.MapBlocks";
    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
    }

    @Test
    void createMapTest() {
        String mapName = "MainMap"; //for now!
        MapParser mapParser = new MapParser(mapName);
        MapWrapper mapWrapper = mapParser.getMapWrapper();
        mapWrapper.setVisualProperties(mapParser.getMapProperties());
        mapWrapper.setStateToImageMap(mapParser.getStateToImageMap());
        mapWrapper.setObstacleStateMap(mapParser.getObstacleStateMap());
        MapView mapView = new MapView(mapWrapper);
        mapView.createMap();
        int state;

        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                state = mapWrapper.getState(i,j);

                switch(state) {
                    case(0)-> {
                        assertEquals("/blocks/grass.jpeg", mapWrapper.getImageFromState(state));
                        break;
                    } case(1)->{
                        assertEquals("/blocks/bush.jpeg", mapWrapper.getImageFromState(state));
                        break;
                    } case(2)->{
                        assertEquals("/blocks/water.jpeg", mapWrapper.getImageFromState(state));
                        break;
                    } case(3)->{
                        assertEquals("/blocks/winter_grass.jpeg", mapWrapper.getImageFromState(state));
                        break;
                    } default->{
                        System.out.println("not one of the provided states");
                    }
                }
            }
        }
    }
}
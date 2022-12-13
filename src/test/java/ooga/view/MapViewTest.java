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

/**
 * @author Mayari Merchant, Nick Ward
 */
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
    }
}
package ooga.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import ooga.controller.MapParser;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class MapViewTest {
    private static final String MAP_BLOCKS_PROPERTIES = "ResourceBundles.MapBlocks";
    private static ResourceBundle blockStates = ResourceBundle.getBundle(MAP_BLOCKS_PROPERTIES);



    @Test
    void createMapTest() {
        String mapName = "MainMap"; //for now!
        MapParser mapParser = new MapParser(mapName);
        MapWrapper mapWrapper = mapParser.getMapWrapper();
        mapWrapper.setStateToImageMap(mapParser.getStateToImageMap());
        MapView mapView = new MapView(mapWrapper);
        mapView.createMap();
        int state;
        String expected;
        for(int i = 0; i<32; i++){
            for(int j = 0; j<32; j++){
                state = mapWrapper.getState(i,j);
                expected = blockStates.getString("state"+Integer.toString(Math.abs(state %4)));
                switch(state) {
                    case(0)-> {
                        assertEquals("/blocks/grass.jpeg",expected);
                        break;
                    } case(1)->{
                        assertEquals("/blocks/bush.jpeg",expected);
                        break;
                    } case(2)->{
                        assertEquals("/blocks/water.jpeg",expected);
                        break;
                    } case(3)->{
                        assertEquals("/blocks/winter_grass.jpeg",expected);
                        break;
                    } default->{
                        System.out.println("not one of the provided states");
                    }
                }
            }
        }
    }
}
package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapWrapperTest {
    @Test
    void sizeTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 6);
        assertEquals(6, mapWrapper.getRowSize(0));
        assertEquals(5, mapWrapper.getColumnSize());
    }

    @Test
    void setStateTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 6);
        mapWrapper.setState(0, 0, 1);
        assertEquals(1, mapWrapper.getState(0, 0));
    }

    @Test
    void setVisualPropertiesTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 6);
        List<Double> visualProperties = new ArrayList<>();
        visualProperties.add(1.0);
        visualProperties.add(2.0);
        visualProperties.add(3.0);
        mapWrapper.setVisualProperties(visualProperties);
        assertEquals(visualProperties, mapWrapper.getVisualProperties());
    }

    @Test
    void setStateImageMapTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 6);
        Map<Integer, String> stateImageMap = new HashMap<>();
        stateImageMap.put(1, "test");
        mapWrapper.setStateToImageMap(stateImageMap);
        // for each key in the map, check that the value is the same
        for (int key : stateImageMap.keySet()) {
            assertEquals(stateImageMap.get(key), mapWrapper.getImageFromState(key));
        }
    }

    @Test
    void addRowTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 7);
        mapWrapper.addRow();
        assertEquals(6, mapWrapper.getColumnSize());
    }

    @Test
    void addColumnTest() {
        MapWrapper mapWrapper = new MapWrapper(5, 7);
        mapWrapper.addValueToRow(0, 1);
        assertEquals(8, mapWrapper.getRowSize(0));
    }

    @Test
    void outOfBoundsTestRows() {
        MapWrapper mapWrapper = new MapWrapper(5, 7);
        try {
            mapWrapper.setState(5, 0, 1);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

    @Test
    void outOfBoundsTestCols() {
        MapWrapper mapWrapper = new MapWrapper(5, 7);
        try {
            mapWrapper.setState(0, 7, 1);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }


}

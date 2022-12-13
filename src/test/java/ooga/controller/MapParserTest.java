package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapParserTest {
    @Test
    void testMapParser() {
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        assertEquals(70, map.getColumnSize());
        assertEquals(117, map.getRowSize(0));
    }

    @Test
    void testMapParserStateMap() {
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());
    }

    @Test
    void testMapParserError() {
        try {
            MapParser mapParser = new MapParser("noexistmap");
        } catch (IllegalStateException e) {
            assertEquals("simFileUploadError", e.getMessage());
        }
    }

    @Test
    void testMapParserGetProperties() {
        MapParser mapParser = new MapParser("MainMap");
        assertEquals(30, mapParser.getMapProperties().get(0));
        assertEquals(2100, mapParser.getMapProperties().get(1));
        assertEquals(3510, mapParser.getMapProperties().get(2));
    }

    @Test
    void testMapParserGetInfo() {
        MapParser mapParser = new MapParser("MainMap");
        assertTrue(mapParser.getMapInfo().containsKey("Title"));
        assertTrue(mapParser.getMapInfo().containsKey("Author"));
    }

    @Test
    void testMapParserGetStateToImage() {
        MapParser mapParser = new MapParser("MainMap");
        assertTrue(mapParser.getStateToImageMap().containsKey(0));
        assertTrue(mapParser.getStateToImageMap().containsKey(1));
    }
}

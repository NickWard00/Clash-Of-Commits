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
        assertEquals(32, map.getColumnSize());
        assertEquals(50, map.getRowSize(0));
    }

    @Test
    void testMapParserError() {
        try {
            MapParser mapParser = new MapParser("noexistmap");
        } catch (IllegalStateException e) {
            assertEquals("fileUploadError", e.getMessage());
        }
    }

    @Test
    void testMapParserGetProperties() {
        MapParser mapParser = new MapParser("MainMap");
        assertEquals(20, mapParser.getMapProperties().get(0));
        assertEquals(640, mapParser.getMapProperties().get(1));
        assertEquals(1000, mapParser.getMapProperties().get(2));
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

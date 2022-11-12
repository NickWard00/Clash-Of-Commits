package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapParserTest {
    @Test
    void testMapParser() {
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        assertEquals(32, map.getColumnSize());
        assertEquals(50, map.getRowSize(0));
    }
}

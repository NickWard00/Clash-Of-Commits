package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVParserTest {
    @Test
    void testMapParserMapBoundsTest() {
        CSVParser parser = new CSVParser();
        MapWrapper map = parser.parseData("data/maps/oldMainMap.csv");
        assertEquals(32, map.getColumnSize());
        assertEquals(50, map.getRowSize(0));
    }

    @Test
    void testMapParserMapErrorTest() {
        CSVParser parser = new CSVParser();
        try{
            MapWrapper map = parser.parseData("data/maps/noexist.csv");
        } catch (IllegalStateException e) {
            assertEquals("badCsvFile", e.getMessage());
        }
    }
}

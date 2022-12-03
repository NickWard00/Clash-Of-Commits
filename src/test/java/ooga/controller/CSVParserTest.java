package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVParserTest {
    @Test
    void testMapParserMapBoundsTest() {
        CSVParser parser = new CSVParser();
        MapWrapper map = parser.parseData("data/maps/MainMap.csv");
        assertEquals(70, map.getColumnSize());
        assertEquals(119, map.getRowSize(0));
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

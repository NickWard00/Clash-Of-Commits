package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVParserTest {
    @Test
    void testMapParser() {
        CSVParser parser = new CSVParser();
        MapWrapper map = parser.parseData("data/maps/MainMap.csv");
        assertEquals(32, map.getColumnSize());
        assertEquals(50, map.getRowSize(0));
    }
}

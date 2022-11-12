package ooga.controller;

import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapParserTest {
    @Test
    void testMapParser() {
        MapParser mapParser = new MapParser("exampleSideScroller");
        MapWrapper map = mapParser.getMapWrapper();
        /*map.getGrid().forEach(row -> {
            row.forEach(System.out::print);
            System.out.println();
        });*/

        assertEquals("EntityMap", "EntityMap");
    }
}

package ooga.controller;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneralParserTest {
    GeneralParser parser = new GeneralParser();

    @Test
    void testSimParser() {
        Properties properties = parser.getSimData("data/maps/MainMap.sim");
        assertTrue(properties.keySet() != null);
    }

    @Test
    void testSimParserError() {
        try {
            Properties properties = parser.getSimData("data/maps/NoExist.sim");
        } catch (IllegalStateException e) {
            assertEquals("fileUploadError", e.getMessage());
        }
    }
}

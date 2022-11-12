package ooga.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityParserTest {
    int xPosition = 213;
    int yPosition = 136;
    String name = "bug1";
    String[] entityData = {"bug", String.valueOf(xPosition), String.valueOf(yPosition)};
    @Test
    void testEntityParserXPosition() {
        EntityParser entityParser = new EntityParser(name, entityData);
        assertEquals(String.valueOf(xPosition), entityParser.getAttributeMap().get("XPosition"));
    }

    @Test
    void testEntityParserYPosition() {
        EntityParser entityParser = new EntityParser(name, entityData);
        assertEquals(String.valueOf(yPosition), entityParser.getAttributeMap().get("YPosition"));
    }

    @Test
    void testEntityParserName() {
        EntityParser entityParser = new EntityParser(name, entityData);
        assertEquals(name, entityParser.getAttributeMap().get("Name"));
    }

    @Test
    void testEntityParserError() {
        String[] entityData = {"noexist", "213", "136"};
        try{
            EntityParser entityParser = new EntityParser(name, entityData);
        } catch (IllegalStateException e) {
            assertEquals("fileUploadError", e.getMessage());
        }
    }


}

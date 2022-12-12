package ooga.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityMapParserTest {
    @Test
    void testEntityMapParserGetEntities() {
        EntityMapParser entityMapParser = new EntityMapParser("data/entity-powerup-maps/Entity_MainMap.sim");
        assertTrue(entityMapParser.getEntities().size() > 0);
    }

    @Test
    void testEntityMapParserError() {
        try{
            EntityMapParser entityMapParser = new EntityMapParser("Entity_Noexist");
        } catch (IllegalStateException e) {
            assertEquals("simFileUploadError", e.getMessage());
        }
    }
}

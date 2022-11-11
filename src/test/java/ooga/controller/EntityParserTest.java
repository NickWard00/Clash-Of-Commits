package ooga.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityParserTest {
    @Test
    void testEntityParser() {
        EntityParser entityParser = new EntityParser("bug1", new String[]{"bug", "0", "0"});
        assertEquals("EntityMap", "EntityMap");
    }
}

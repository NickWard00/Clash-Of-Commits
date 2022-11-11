package ooga.controller;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityMapParserTest {
    @Test
    void testEntityMapParser() {
        EntityMapParser entityMapParser = new EntityMapParser(new File("data/EntityMap.sim"));
        assertEquals("Entity", "EntityMap");

    }
}

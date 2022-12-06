package ooga.model.attack;

import ooga.controller.EntityParser;
import ooga.model.entities.Entity;
import ooga.model.enemy.MagicValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LongRangeTest {
    EntityParser magicValueParser;
    Entity testMagicValue;
    LongRange testLongRange;

    @BeforeEach
    void setUp() {
        magicValueParser = new EntityParser("TestMagicValue", new String[]{"MagicValue", "0", "0"});
        testMagicValue = new MagicValue(magicValueParser.getAttributeMap());
        testLongRange = (LongRange) testMagicValue.attack();
    }

    @Test
    void moveTest() {
        Double[] actual = testLongRange.move(1.0).toArray(new Double[0]);
        Double[] expected = new Double[] {0.0, -40.0};
        assertArrayEquals(expected, actual);
    }

}
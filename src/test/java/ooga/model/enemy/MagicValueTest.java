package ooga.model.enemy;

import ooga.controller.EntityParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicValueTest {

    private EntityParser magicValueParser = new EntityParser("TestMagicValue", new String[]{"MagicValue", "0", "0"});

    @Test
    void magicValueTest() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertInstanceOf(MagicValue.class, magicValue);
        assertInstanceOf(Enemy.class, magicValue);
    }

}
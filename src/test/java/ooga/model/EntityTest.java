package ooga.model;

import ooga.controller.EntityParser;
import ooga.model.attack.Attack;
import ooga.model.attack.LongRange;
import ooga.model.enemy.Bug;
import ooga.model.enemy.MagicValue;
import ooga.model.hero.MainHero;
import ooga.model.state.MovementState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    EntityParser bugParser;
    EntityParser magicValueParser;
    EntityParser heroParser;
    Entity testBug;
    Entity testMagicValue;
    Entity testHero;


    @BeforeEach
    void setUp() {
        bugParser = new EntityParser("TestBug", new String[]{"Bug", "0", "0"});
        magicValueParser = new EntityParser("TestMagicValue", new String[]{"MagicValue", "0", "0"});
        heroParser = new EntityParser("TestHero", new String[] {"MainHero", "5", "10"});
        testBug = new Bug(bugParser.getAttributeMap());
        testMagicValue = new MagicValue(magicValueParser.getAttributeMap());
        testHero = new MainHero(heroParser.getAttributeMap());
    }


    @Test
    void changeHpTest() {
        testHero.changeHp(10);
        assertEquals(110, testHero.getHp());
        testHero.changeHp(-50);
        assertEquals(60, testHero.getHp());
    }

    @Test
    void coordinatesTest() {
        Double[] actual = testHero.coordinates().toArray(new Double[0]);
        Double[] expected = new Double[] {5.0, 10.0};
        assertArrayEquals(expected, actual);
    }

    @Test
    void getAttackTypeTest() {
        String actualHeroAttack = testHero.getAttackType();
        String actualBugAttack = testBug.getAttackType();
        String actualMagicValueAttack = testMagicValue.getAttackType();
        assertEquals("ShortRange", actualHeroAttack);
        assertEquals("ShortRange", actualBugAttack);
        assertEquals("LongRange", actualMagicValueAttack);
    }

    @Test
    void getStateStringsTest() {
        String[] actualHeroStates = testHero.getStateStrings().toArray(new String[0]);
        String[] expectedHeroStates = new String[] {"SOUTH", "STATIONARY"};
        assertArrayEquals(expectedHeroStates, actualHeroStates);
    }

    @Test
    void moveTest_Moving() {
        testBug.changeMovement(MovementState.MOVING);
        Double[] actual = testBug.move(1.0).toArray(new Double[0]);
        Double[] expected = new Double[] {0.0, -5.0};
        assertArrayEquals(expected, actual);
    }

    @Test
    void moveTest_Stationary() {
        Double[] actual = testBug.move(1.0).toArray(new Double[0]);
        Double[] expected = new Double[] {0.0, 0.0};
        assertArrayEquals(expected, actual);
    }
}
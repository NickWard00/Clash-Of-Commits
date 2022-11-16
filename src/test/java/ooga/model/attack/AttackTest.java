package ooga.model.attack;

import ooga.controller.EntityParser;
import ooga.model.Entity;
import ooga.model.enemy.Bug;
import ooga.model.enemy.MagicValue;
import ooga.model.hero.MainHero;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

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
        heroParser = new EntityParser("TestHero", new String[]{"MainHero", "0", "0"});
        testBug = new Bug(bugParser.getAttributeMap());
        testMagicValue = new MagicValue(magicValueParser.getAttributeMap());
        testHero = new MainHero(heroParser.getAttributeMap());
    }

    @Test
    void getMyEntityTest_Enemy() {
        Attack testAttack = Attack.attack(testBug);
        assertEquals(testBug, testAttack.getMyEntity());
    }

    @Test
    void getMyEntityTest_Hero() {
        Attack testAttack = Attack.attack(testHero);
        assertEquals(testHero, testAttack.getMyEntity());
    }

    @Test
    void attackTest_LongRange() {
        Attack testLongRange = Attack.attack(testMagicValue);
        assertInstanceOf(LongRange.class, testLongRange);
    }

    @Test
    void attackTest_ShortRange() {
        Attack testShortRange = Attack.attack(testBug);
        assertInstanceOf(ShortRange.class, testShortRange);
    }}
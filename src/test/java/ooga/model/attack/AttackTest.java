package ooga.model.attack;

import ooga.controller.EntityParser;
import ooga.model.Entity;
import ooga.model.enemy.Bug;
import ooga.model.hero.MainHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

    EntityParser bugParser;
    EntityParser heroParser;
    Entity testBug;
    Entity testHero;

    @BeforeEach
    void setUp() {
        bugParser = new EntityParser("TestBug", new String[]{"Bug", "0", "0"});
        heroParser = new EntityParser("TestHero", new String[]{"MainHero", "0", "0"});
        testBug = new Bug(bugParser.getAttributeMap());
        testHero = new MainHero(heroParser.getAttributeMap());
    }

    @Test
    void getMyEntityTest_Enemy() {
        Attack testAttack = new LongRange(testBug);
        assertEquals(testBug, testAttack.getMyEntity());
    }

    @Test
    void getMyEntityTest_Hero() {
        Attack testAttack = new LongRange(testHero);
        assertEquals(testHero, testAttack.getMyEntity());
    }
}
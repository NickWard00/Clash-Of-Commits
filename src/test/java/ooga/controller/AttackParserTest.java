package ooga.controller;

import ooga.model.Entity;
import ooga.model.hero.MainHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AttackParserTest {

    EntityParser heroParser;
    Entity testHero;
    AttackParser attackParser;

    @BeforeEach
    void setUp() {
        heroParser = new EntityParser("TestHero", new String[]{"MainHero", "0", "0"});
        testHero = new MainHero(heroParser.getAttributeMap());
    }

    @Test
    void getAttributeMapTest() {
        attackParser = new AttackParser(testHero);
        Map<String, Double> attributes = attackParser.getAttributeMap();
        Double expectedSpeed = 20.0;
        Double expectedDamage = 2.0;
        Double expectedCoolDown = 0.2;
        assertEquals(expectedSpeed, attributes.get("Speed"));
        assertEquals(expectedDamage, attributes.get("Damage"));
        assertEquals(expectedCoolDown, attributes.get("CoolDown"));
    }

}
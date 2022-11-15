package ooga.model.hitBox;

import ooga.controller.EntityParser;
import ooga.model.Entity;
import ooga.model.hero.MainHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HitBoxTest {

    EntityParser heroParser;
    Entity testHero;
    EntityHitBox testEntityHitBox;

    @BeforeEach
    void setUp() {
        heroParser = new EntityParser("TestHero", new String[] {"MainHero", "5", "10"});
        testHero = new MainHero(heroParser.getAttributeMap());
        testEntityHitBox = new EntityHitBox(testHero, 0,0,10,4);
    }

    @Test
    void moveTest() {
        testEntityHitBox.move(2.0, 3.0);
        Double[] expected = new Double[] {2.0, 3.0};
        Double[] actual = testEntityHitBox.coordinates().toArray(new Double[0]);
        assertArrayEquals(expected, actual);
    }

}
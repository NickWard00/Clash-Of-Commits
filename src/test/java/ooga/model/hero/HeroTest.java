package ooga.model.hero;

import ooga.controller.EntityParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    private EntityParser mainHeroParser = new EntityParser("TestHero", new String[]{"MainHero", "0", "0"});
    private EntityParser linkParser = new EntityParser("TestLink", new String[]{"Link", "0", "0"});

    @Test
    void testMainHeroType() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Type"), "Hero");
    }

    @Test
    void testLinkType() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Type"), "Hero");
    }

    @Test
    void testMainHeroName() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Name"), "TestHero");
    }

    @Test
    void testLinkName() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Name"), "TestLink");
    }

    @Test
    void testMainHeroSpeed() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Speed"), "100");
    }

    @Test
    void testLinkSpeed() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Speed"), "100");
    }

    @Test
    void testMainHeroHP() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("HP"), "5");
        assertEquals(hero.getHp(), 5);
    }

    @Test
    void testLinkHP() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("HP"), "5");
        assertEquals(link.getHp(), 5);
    }

    @Test
    void testMainHeroDamage() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        hero.setHP(1);
        hero.changeHp(-1);
        assertEquals(hero.getHp(), 0);
    }

    @Test
    void testLinkDamage() {
        Link link = new Link(linkParser.getAttributeMap());
        link.setHP(1);
        link.changeHp(-1);
        assertEquals(link.getHp(), 0);
    }

    @Test
    void testMainHeroSprites() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Sprites"), "/sprites/hero/");
    }

    @Test
    void testLinkSprites() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Sprites"), "/sprites/link/");
    }

    @Test
    void testMainHeroSize() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Size"), "30");
        assertEquals(hero.getSize(), 30);
    }

    @Test
    void testLinkSize() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Size"), "30");
        assertEquals(link.getSize(), 30);
    }

    @Test
    void testMainHeroAttack() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Attack"), "LongRange");
    }

    @Test
    void testLinkAttack() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Attack"), "LongRange");
    }

    @Test
    void testMainHeroXPosition() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("XPosition"), "0");
        assertEquals(0, hero.coordinates().get(0));
    }

    @Test
    void testLinkXPosition() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("XPosition"), "0");
        assertEquals(0, link.coordinates().get(0));
    }

    @Test
    void testMainHeroYPosition() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("YPosition"), "0");
        assertEquals(0, hero.coordinates().get(1));
    }

    @Test
    void testLinkYPosition() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("YPosition"), "0");
        assertEquals(0, link.coordinates().get(1));
    }

    @Test
    void testMainHeroDirectionState() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Direction"), "SOUTH");
    }

    @Test
    void testLinkDirectionState() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Direction"), "SOUTH");
    }

    @Test
    void testMainHeroMovementState() {
        MainHero hero = new MainHero(mainHeroParser.getAttributeMap());
        assertEquals(hero.getMyAttributes().get("Movement"), "STATIONARY");
    }

    @Test
    void testLinkMovementState() {
        Link link = new Link(linkParser.getAttributeMap());
        assertEquals(link.getMyAttributes().get("Movement"), "STATIONARY");
    }
}

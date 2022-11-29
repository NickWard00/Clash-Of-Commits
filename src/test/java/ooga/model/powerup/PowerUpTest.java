package ooga.model.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import ooga.controller.EntityParser;
import ooga.model.hero.MainHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerUpTest {

  private EntityParser heroParser;
  private EntityParser powerUpParser;
  private PowerUp powerUp;
  private MainHero hero;

  private static final int TEST_HP = 1;

  @BeforeEach
  void setUp() {
    powerUpParser = new EntityParser("TestPowerUp", new String[]{"PowerUp", "1", "1"});
    heroParser = new EntityParser("TestHero", new String[]{"MainHero", "5", "5"});
    powerUp = new PowerUp(powerUpParser.getAttributeMap());
    hero = new MainHero(heroParser.getAttributeMap());
  }

  @Test
  void testUpdateHP() {
    int hpBefore = hero.getHp();
    powerUp.upgradeHP(hero, TEST_HP);
    assertEquals(hpBefore + TEST_HP, hero.getHp());
  }

}

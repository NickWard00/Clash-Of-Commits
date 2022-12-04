package ooga.model.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.controller.EntityParser;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.powerup.PowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerUpTest {

  private EntityParser heroParser;
  private EntityParser powerUpParser;
  private PowerUp powerUp;
  private MainHero hero;

  private static final int TEST_HP = 1;
  private static final double TEST_DEFAULT_X_POSITION = 1.0;
  private static final double TEST_DEFAULT_Y_POSITION = 1.0;

  @BeforeEach
  void setUp() {
//    powerUpParser = new EntityParser("TestPowerUp", new String[]{"PowerUp", "1", "1"});
//    powerUp = new PowerUp(powerUpParser.getAttributeMap());
    powerUp = new PowerUp(TEST_DEFAULT_X_POSITION, TEST_DEFAULT_Y_POSITION);
    heroParser = new EntityParser("TestHero", new String[]{"MainHero", "5", "5"});
    hero = new MainHero(heroParser.getAttributeMap());
  }

  @Test
  void testUpdateHP() {
    int hpBefore = hero.getHp();
    powerUp.upgradeHP(hero, TEST_HP);
    assertEquals(hpBefore + TEST_HP, hero.getHp());
  }

}

package ooga.model.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  private static final int TEST_DEFAULT_X_POSITION = 1;
  private static final int TEST_DEFAULT_Y_POSITION = 1;

  @BeforeEach
  void setUp() {
    powerUp = new HealPowerUp("test1", TEST_DEFAULT_X_POSITION, TEST_DEFAULT_Y_POSITION);
    heroParser = new EntityParser("TestHero", new String[]{"MainHero", "5", "5"});
    hero = new MainHero(heroParser.getAttributeMap());
  }

  @Test
  void testUpdateHP() {
    int hpBefore = hero.getHp();
    powerUp.activatePowerUp(hero);
    assertEquals(hpBefore + 1, hero.getHp());
  }

}

package ooga.model.powerup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.controller.EntityParser;
import ooga.controller.PowerUpParser;
import ooga.model.hero.MainHero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class PowerUpTest {
  private EntityParser heroParser;
  private PowerUpParser powerUpParser;


  private PowerUp powerUp;
  private MainHero hero;


  @BeforeEach
  void setUp() {
    powerUpParser = new PowerUpParser("data/entity-powerup-maps/PowerUp_MainMap.sim");
    powerUp = powerUpParser.createPowerUp("TestPowerUp", "HealPowerUp", 2,2);
    heroParser = new EntityParser("TestHero", new String[]{"MainHero", "5", "5"});
    hero = new MainHero(heroParser.getAttributeMap());
  }


  @Test
  void getKeyTest() {
    List<Integer> expected = Arrays.asList(2,2);
    List<Integer> actual = powerUp.getKey();
    assertEquals(expected, actual);
  }

  @Test
  void getPowerUpTypeTest() {
    String expected = "HealPowerUp";
    String actual = powerUp.getPowerUpType();
    assertEquals(expected, actual);
  }

  @Test
  void getNameTest() {
    String expected = "TestPowerUp";
    String actual = powerUp.getName();
    assertEquals(expected, actual);
  }

  @Test
  void getImagePathTest() {
    String expected = "/blocks/heart.gif";
    String actual = powerUp.getImagePath();
    assertEquals(expected, actual);
  }

  @Test
  void availableTest() {
    Boolean expected = true;
    Boolean actual = powerUp.available();
    assertEquals(expected, actual);
  }

  @Test
  void setAvailableTest() {
    Boolean expectedBefore = true;
    Boolean actualBefore = powerUp.available();
    powerUp.setAvailable(false);
    Boolean expectedAfter = false;
    Boolean actualAfter = powerUp.available();
    assertEquals(expectedBefore, actualBefore);
    assertEquals(expectedAfter, actualAfter);
  }

  @Test
  void activatePowerUpTest() {
    int expectedBefore = 5;
    int actualBefore = hero.getHp();
    powerUp.activatePowerUp(hero);
    int expectedAfter = 6;
    int actualAfter = hero.getHp();
    assertEquals(expectedBefore, actualBefore);
    assertEquals(expectedAfter, actualAfter);
  }



}

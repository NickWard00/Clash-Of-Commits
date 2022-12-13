package ooga.controller;

import ooga.model.hero.MainHero;
import ooga.model.powerup.HealPowerUp;
import ooga.model.powerup.PowerUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpParserTest {

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
    void createPowerUpTest() {
        PowerUp testPowerUp = powerUpParser.createPowerUp("Testing", "HealPowerUp", 10, 15);
        assertInstanceOf(PowerUp.class, testPowerUp);
        assertInstanceOf(HealPowerUp.class, testPowerUp);
    }

    @Test
    void creatPowerUpTest_IncorrectType() {
        try {
            PowerUp badPowerUp= powerUpParser.createPowerUp("Testing", "WrongType", 0,0);
        } catch (IllegalStateException e) {
            assertEquals("noPowerUpFound", e.getMessage());
        }
    }

    @Test
    void getPowerUpsTest() {
        int expected = 2;
        int actual = powerUpParser.getPowerUps().size();
        assertEquals(expected, actual);
    }


}
package ooga.model.collisions;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.EntityParser;
import ooga.model.hero.MainHero;
import ooga.model.powerup.HealPowerUp;
import ooga.view.EntityView;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class EntityPowerUpCollisionTest extends DukeApplicationTest {

    private EntityParser heroParser;
    private MainHero hero;
    private EntityView heroView;
    private Map<String, Map<?,?>> viewEntities;
    private HealPowerUp powerUp;
    private EntityPowerUpCollision myCollision;

    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        ResourceBundle labels = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
        Controller controller = new Controller(stage, "MainMap", "The Beginning", labels);
    }

    @BeforeEach
    void setUp() {
        heroParser = new EntityParser("TestHero", new String[]{"MainHero", "1", "1"});
        hero = new MainHero(heroParser.getAttributeMap());
        heroView = new EntityView("sprites/hero/SOUTH.gif", "TestHeroView");
        viewEntities = Map.of("viewEntities", Map.of("TestHero", heroView));
        powerUp = new HealPowerUp("TestPowerUp", 5, 5);
        myCollision = new EntityPowerUpCollision(viewEntities);
    }

    @Test
    void collideTest() {
        myCollision.collide(hero, powerUp);
        assertInstanceOf(EntityPowerUpCollision.class, myCollision);
    }

}
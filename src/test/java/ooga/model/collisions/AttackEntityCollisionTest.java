package ooga.model.collisions;

import javafx.stage.Stage;
import ooga.controller.AttackParser;
import ooga.controller.Controller;
import ooga.controller.EntityParser;
import ooga.model.attack.Attack;
import ooga.model.enemy.MagicValue;
import ooga.model.entities.Entity;
import ooga.model.hero.MainHero;
import ooga.view.EntityView;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class AttackEntityCollisionTest extends DukeApplicationTest {

    EntityParser heroParser;
    Entity hero;
    EntityView heroView;
    Map<String, Map<?,?>> viewEntities;
    EntityParser enemyParser;
    MagicValue magicValue;
    AttackParser attackParser;
    Attack attack;
    AttackEntityCollision myCollision;



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
        enemyParser = new EntityParser("TestEnemy", new String[]{"MagicValue", "5", "5"});
        magicValue = new MagicValue(enemyParser.getAttributeMap());
        attackParser = new AttackParser(magicValue);
        attack = magicValue.attack();
        myCollision = new AttackEntityCollision(viewEntities);
    }

    @Test
    void collideTest() {
        myCollision.collide(attack, hero);
    }
}
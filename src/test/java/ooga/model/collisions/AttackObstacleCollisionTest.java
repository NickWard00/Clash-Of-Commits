package ooga.model.collisions;

import javafx.stage.Stage;
import ooga.controller.AttackParser;
import ooga.controller.Controller;
import ooga.controller.EntityParser;
import ooga.model.attack.Attack;
import ooga.model.enemy.MagicValue;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import ooga.view.EntityView;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class AttackObstacleCollisionTest extends DukeApplicationTest {

    private EntityParser enemyParser;
    private MagicValue magicValue;
    private AttackParser attackParser;
    private Attack attack;
    private Obstacle obstacle;
    private AttackObstacleCollision myCollision;
    private Map<String, Map<?,?>> viewEntities;

    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        ResourceBundle labels = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
        Controller controller = new Controller(stage, "MainMap", "The Beginning", labels);
    }

    @BeforeEach
    void setUp() {
        enemyParser = new EntityParser("TestEnemy", new String[]{"MagicValue", "5", "5"});
        magicValue = new MagicValue(enemyParser.getAttributeMap());
        attackParser = new AttackParser(magicValue);
        attack = magicValue.attack();
        obstacle = new DestroyableWall(5.0,5.0);
        viewEntities = Map.of("viewEntities", Map.of());
        myCollision = new AttackObstacleCollision(viewEntities);
    }

    @Test
    void collideTest() {
        myCollision.collide(attack, obstacle);
        assertInstanceOf(AttackObstacleCollision.class, myCollision);
    }

}
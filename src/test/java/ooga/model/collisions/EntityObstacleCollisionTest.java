package ooga.model.collisions;

import javafx.stage.Stage;
import ooga.controller.AttackParser;
import ooga.controller.Controller;
import ooga.controller.EntityParser;
import ooga.model.enemy.MagicValue;
import ooga.model.hero.MainHero;
import ooga.view.EntityView;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.BeforeEach;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class EntityObstacleCollisionTest extends DukeApplicationTest {

    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        ResourceBundle labels = ResourceBundle.getBundle("ResourceBundles.LabelsBundle");
        Controller controller = new Controller(stage, "MainMap", "The Beginning", labels);
    }


}
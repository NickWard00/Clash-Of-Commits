package ooga.controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.model.entities.Entity;
import ooga.view.EntityView;
import ooga.view.MapWrapper;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest extends DukeApplicationTest {
    private Controller controller;
    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        setupController(stage);
    }

    void setupController(Stage stage) {
        controller = new Controller(stage, "MainMap","", null);
    }

    @Test
    void getMapWrapperTest() {
        MapWrapper mapWrapper = controller.getMapWrapper();
        assertEquals(32, mapWrapper.getColumnSize());
        assertEquals(50, mapWrapper.getRowSize(0));
    }

    @Test
    void getModelEntitiesTest() {
        Map<String, Entity> entities = controller.getModelEntities();
        assertTrue(entities != null);
    }

    @Test
    void getEntityNamesTest() {
        Map<String, EntityView> entities = controller.getViewEntities();
        assertTrue(entities != null);
    }

    @Test
    void keyPressTest() {
        controller.handleKeyPress(KeyCode.UP);
        controller.handleKeyRelease(KeyCode.UP);
        controller.handleKeyPress(KeyCode.DOWN);
        controller.handleKeyRelease(KeyCode.DOWN);
        controller.handleKeyPress(KeyCode.LEFT);
        controller.handleKeyRelease(KeyCode.LEFT);
        controller.handleKeyPress(KeyCode.RIGHT);
        controller.handleKeyRelease(KeyCode.RIGHT);
    }

    @Test
    void stepTest() {
        Map<String, EntityView> viewEntities = controller.getViewEntities();
        for (String entityName : viewEntities.keySet()) {
            EntityView entityView = controller.getViewEntities().get(entityName);
            Entity entity = controller.getModelEntities().get(entityName);
            assertEquals(entityView.getX(), entity.coordinates().get(0));
            assertEquals(entityView.getY(), entity.coordinates().get(1));
        }
    }
}

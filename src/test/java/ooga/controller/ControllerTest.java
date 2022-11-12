package ooga.controller;

import ooga.model.Entity;
import ooga.view.MapWrapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {
    @Test
    void controllerTest() {
        Controller controller = new Controller("MainMap");
        MapWrapper map = controller.getMapWrapper();
        List<Entity> entities = controller.getEntities();
        assertEquals(32, map.getColumnSize());
        assertEquals(50, map.getRowSize(0));
        assertEquals(4, entities.size());
    }
}

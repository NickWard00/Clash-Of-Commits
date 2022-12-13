package ooga.view;

import javafx.stage.Stage;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityViewTest extends DukeApplicationTest {
    private Map<String, String> entityAttributes;
    private String imageName;
    private double xPos;
    private double yPos;
    private int size;
    private String spriteLocation;
    private String startingDirection;
    private EntityView entityView;

    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());

        entityAttributes = Map.of("Name", "Hero1", "XPosition", "50", "YPosition", "103", "Size", "20", "Sprites", "/sprites/hero/", "Direction", "SOUTH");
        imageName = entityAttributes.get("Name");
        xPos = Double.parseDouble(entityAttributes.get("XPosition"));
        yPos = Double.parseDouble(entityAttributes.get("YPosition"));
        size = Integer.parseInt(entityAttributes.get("Size"));
        spriteLocation = entityAttributes.get("Sprites");
        startingDirection = entityAttributes.get("Direction");
        entityView = new EntityView(spriteLocation, startingDirection, imageName, xPos, yPos, size, size);
    }

    @Test
    void testEntityViewNames() {
        assertEquals(imageName, entityView.getKey());
    }

    @Test
    void testEntityViewPosX() {
        assertEquals(xPos, entityView.getX());
    }

    @Test
    void testEntityViewPosY() {
        assertEquals(yPos, entityView.getY());
    }

    @Test
    void testEntityViewSize() {
        assertEquals(size, entityView.getFitWidth());
        assertEquals(size, entityView.getFitHeight());
    }

    @Test
    void testChangeDirection() {
        entityView.changeDirectionAndMovement(DirectionState.NORTH, MovementState.MOVING);
        entityView.changeDirectionAndMovement(DirectionState.SOUTH, MovementState.MOVING);
        entityView.changeDirectionAndMovement(DirectionState.EAST, MovementState.MOVING);
        entityView.changeDirectionAndMovement(DirectionState.WEST, MovementState.MOVING);
        entityView.changeDirectionAndMovement(DirectionState.NORTH, MovementState.STATIONARY);
        entityView.changeDirectionAndMovement(DirectionState.SOUTH, MovementState.STATIONARY);
        entityView.changeDirectionAndMovement(DirectionState.EAST, MovementState.STATIONARY);
        entityView.changeDirectionAndMovement(DirectionState.WEST, MovementState.STATIONARY);
        assertTrue(entityView.getKey() != null);
    }
}

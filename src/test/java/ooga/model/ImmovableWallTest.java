package ooga.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import ooga.model.hero.MainHero;
import ooga.model.obstacle.ImmovableWall;
import ooga.model.state.MovementState;
import org.junit.jupiter.api.Test;

class ImmovableWallTest {
  private static final double TEST_X_POSITION = 1.0;
  private static final double TEST_Y_POSITION = 1.0;
  private ImmovableWall wall;
  private Map<String, String> attributes;
  private Entity entity;


  @Test
  void createWall() {
    wall = new ImmovableWall(TEST_X_POSITION, TEST_Y_POSITION);
    assertEquals(TEST_X_POSITION, wall.getPositionX());
    assertEquals(TEST_Y_POSITION, wall.getPositionY());
  }

  //TODO: Implement test here once figure out what to do with the block method
  @Test
  void testWallBlock() {
    wall = new ImmovableWall(TEST_X_POSITION, TEST_Y_POSITION);
    attributes = new HashMap<>();
    attributes.put("XPosition", "1.0");
    attributes.put("YPosition", "1.0");
    attributes.put("Type", "Hero");
    attributes.put("Speed", "100");
    attributes.put("Size", "30");
    attributes.put("HP", "100");
    attributes.put("Attack", "LongRange");
    attributes.put("Direction", "SOUTH");
    attributes.put("Movement", "MOVING");
    entity = new MainHero(attributes);
    wall.block(entity);
    assertEquals(MovementState.STATIONARY.getMovement(), entity.getStateStrings().get(1));

  }
}

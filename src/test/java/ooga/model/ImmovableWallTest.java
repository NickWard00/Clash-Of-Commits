package ooga.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.obstacle.ImmovableWall;
import org.junit.jupiter.api.Test;

class ImmovableWallTest {
  private static final double TEST_X_POSITION = 1.0;
  private static final double TEST_Y_POSITION = 1.0;
  private ImmovableWall wall;


  @Test
  void createWall() {
    wall = new ImmovableWall(TEST_X_POSITION, TEST_Y_POSITION);
    assertEquals(TEST_X_POSITION, wall.getPositionX());
    assertEquals(TEST_Y_POSITION, wall.getPositionY());
  }

  //TODO: Implement test here once figure out what to do with the block method
  @Test
  void testWallBlock() {

  }
}

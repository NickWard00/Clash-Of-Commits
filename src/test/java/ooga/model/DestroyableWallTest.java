package ooga.model;

import static ooga.model.obstacle.DestroyableWall.DEFAULT_HP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import ooga.model.obstacle.DestroyableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyableWallTest {
  private static final double TEST_DEFAULT_X_POSITION = 1.0;
  private static final double TEST_DEFAULT_Y_POSITION = 1.0;
  private DestroyableWall defaultWall;
  private DestroyableWall wall;
  private int testHP;

  @BeforeEach
  void setup() {
    defaultWall = new DestroyableWall(TEST_DEFAULT_X_POSITION, TEST_DEFAULT_Y_POSITION);
    testHP = 50;
  }

  @Test
  void createWallWithoutHitpoints() {
    assertEquals(DEFAULT_HP, defaultWall.determineHP());
  }

  @Test
  void createWallWithHitpoints() {
    wall = new DestroyableWall(TEST_DEFAULT_X_POSITION, TEST_DEFAULT_Y_POSITION, testHP);
    assertEquals(testHP, wall.determineHP());
  }

  //TODO: Implement test here once figure out what to do with the block method
  @Test
  void testBlock() {

  }

  @Test
  void changeHP() {
    defaultWall.updateHP(-10);
    assertEquals(90, defaultWall.determineHP());
  }

  @Test
  void testRemoveWallWhenNoHitpointsLeft() {
    defaultWall.updateHP(-100);
    assertFalse(defaultWall.determineOnScreen());
  }
}

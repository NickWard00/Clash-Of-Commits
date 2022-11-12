package ooga.model;

import static ooga.model.obstacle.DestroyableWall.DEFAULT_HP;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.obstacle.DestroyableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyableWallTest {
  private DestroyableWall defaultWall;
  private DestroyableWall wall;
  private int testHP;

  @BeforeEach
  void setup() {
    defaultWall = new DestroyableWall(1.0, 1.0);
    testHP = 50;
  }

  @Test
  void createWallWithoutHitpoints() {
    assertEquals(DEFAULT_HP, defaultWall.getHP());
  }

  @Test
  void createWallWithHitpoints() {
    wall = new DestroyableWall(1.0, 1.0, testHP);
    assertEquals(testHP, wall.getHP());
  }
//
//  @Test
//  void testRemoveWall() {
//
//  }
//
//  @Test
//  void testBlock() {
//
//  }

  @Test
  void changeHP() {
    defaultWall.updateHP(-10);
    assertEquals(90, defaultWall.getHP());
  }



}

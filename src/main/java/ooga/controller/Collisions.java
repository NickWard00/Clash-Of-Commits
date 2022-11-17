package ooga.controller;

import ooga.model.Entity;
import ooga.model.attack.Attack;
import ooga.model.obstacle.DestroyableWall;
import ooga.model.obstacle.Obstacle;
import ooga.model.obstacle.Wall;

public class Collisions {

  public void detectEntityAttackOnHit(Entity entity, Attack attack) {
//    entity.changeHP(attack.getDamage());
  }

  public void detectWallEntityOnHit(Wall obstacle, Entity entity) {
    obstacle.block(entity);
  }

  public void detectWallAttackOnHit(Wall obstacle, Attack attack) {
    if (obstacle.getClass() == DestroyableWall.class) {
//      ((DestroyableWall) obstacle).updateHP(attack.getDamage());
    }
  }

}

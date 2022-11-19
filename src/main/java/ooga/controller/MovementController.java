package ooga.controller;

import ooga.model.Entity;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

public class MovementController {

  public void moveLeft(Entity entity) {
    entity.changeMovement(MovementState.MOVING);
    entity.changeDirection(DirectionState.WEST);
//    entity.move()
  }

  public void moveRight(Entity entity) {
    entity.changeMovement(MovementState.MOVING);
    entity.changeDirection(DirectionState.EAST);
  }

  public void moveUp(Entity entity) {
    entity.changeMovement(MovementState.MOVING);
    entity.changeDirection(DirectionState.NORTH);
  }

  public void moveDown(Entity entity) {
    entity.changeMovement(MovementState.MOVING);
    entity.changeDirection(DirectionState.SOUTH);
  }

  public void moveRightStop(Entity entity) {
    entity.changeMovement(MovementState.STATIONARY);
    entity.changeDirection(DirectionState.EAST_STATIONARY);
  }
  public void moveLeftStop(Entity entity) {
    entity.changeMovement(MovementState.STATIONARY);
    entity.changeDirection(DirectionState.WEST_STATIONARY);
  }

  public void moveUpStop(Entity entity) {
    entity.changeMovement(MovementState.STATIONARY);
    entity.changeDirection(DirectionState.NORTH_STATIONARY);
  }

  public void moveDownStop(Entity entity) {
    entity.changeMovement(MovementState.STATIONARY);
    entity.changeDirection(DirectionState.SOUTH_STATIONARY);
  }


}

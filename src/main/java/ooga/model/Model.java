package ooga.model;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.ObstacleEnum;
import ooga.model.attack.Attack;
import ooga.model.obstacle.Obstacle;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;
import ooga.view.MapWrapper;

import java.util.List;
import java.util.Map;

public class Model {
    private Controller myController;
    private Map<String, Entity> myModelEntities;
    private Entity myHeroModel;
    public Model(Controller controller) {
        myController = controller;
        setupGame();
    }

    private void setupGame() {
        myModelEntities = myController.getModelEntities();
        myHeroModel = myModelEntities.get(myController.getMainHeroName());
    }

    public void changeEntityState(String entityName, MovementState movement, DirectionState direction) {
        Entity entity = myModelEntities.get(entityName);
        entity.changeMovement(movement);
        entity.changeDirection(direction);
    }

    public void changeEntityState(String entityName, DirectionState direction, MovementState movement) {
        Entity entity = myModelEntities.get(entityName);
        entity.changeDirection(direction);
        entity.changeMovement(movement);
    }

    public void attack(){
        Attack.attack(myHeroModel).activateAttack();
    }

}

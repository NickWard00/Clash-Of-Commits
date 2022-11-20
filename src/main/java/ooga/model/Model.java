package ooga.model;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

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

    public void changeEntityState(String entityName, DirectionState direction) {
        Entity entity = myModelEntities.get(entityName);
        entity.changeDirection(direction);
    }

    private void attack(){
        myHeroModel.getMyAttack().activateAttack();
    }

    private void attackStop() {

    }
}

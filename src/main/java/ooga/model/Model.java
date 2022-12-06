package ooga.model;

import ooga.controller.Controller;
import ooga.model.entities.Entity;
import ooga.model.state.DirectionState;
import ooga.model.state.MovementState;

import java.util.Map;

public class Model {
    private Controller myController;
    private Map<String, Entity> myModelEntities;
    private Entity myHeroModel;

    /**
     * Constructor for the Model class
     * @param controller the controller that this model is associated with
     */
    public Model(Controller controller) {
        myController = controller;
        setupGame();
    }

    /**
     * Method to set up the model for a new game
     */
    private void setupGame() {
        myModelEntities = myController.getModelEntities();
        myHeroModel = myModelEntities.get(myController.getMainHeroName());
    }

    /**
     * Method to change the entity direction and movement states
     * @param entityName the name of the entity whose states are being changed
     * @param movement the new movement state
     * @param direction the new direction state
     */
    public void changeEntityState(String entityName, DirectionState direction, MovementState movement) {
        Entity entity = myModelEntities.get(entityName);
        entity.changeMovement(movement);
        entity.changeDirection(direction);
    }

    /**
     * Actives the attack of the hero
     */
    public void attack() {
        myHeroModel.attack().activateAttack();
    }
}

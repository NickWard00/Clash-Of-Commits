package ooga.model;

import ooga.controller.Controller;

import java.util.List;

public class Model {
    private Controller myController;
    private List<Entity> myEntities;
    public Model(Controller controller) {
        myController = controller;
        myEntities = myController.getEntities();
    }
}

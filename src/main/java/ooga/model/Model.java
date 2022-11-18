package ooga.model;

import ooga.controller.Controller;

import java.util.List;
import java.util.Map;

public class Model {
    private Controller myController;
    private Map<String, Entity> myEntities;
    public Model(Controller controller) {
        myController = controller;
        myEntities = myController.getModelEntities();
    }
}

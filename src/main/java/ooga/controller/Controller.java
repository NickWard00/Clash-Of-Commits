package ooga.controller;

import ooga.model.Entity;
import ooga.view.MapWrapper;

import java.util.List;

public class Controller {
    private MapWrapper mapWrapper;
    private List<Entity> myEntities;
    public Controller(String map) {
        startGame(map);
    }

    private void startGame(String map) {
        MapParser mapParser = new MapParser(map);
        mapWrapper = mapParser.getMapWrapper();
        EntityMapParser entityMapParser = new EntityMapParser("Entity_" + map);
        myEntities = entityMapParser.getEntities();
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    public List<Entity> getEntities() {
        return myEntities;
    }
}

package ooga.controller;

import ooga.model.Entity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class EntityMapParser {
    private Properties properties;
    private List<Entity> entities;
    private static final String ENTITY_PACKAGE = "ooga.model.%s.%s";
    private static final String ENTITY_MAP_DIRECTORY = "data/%s.sim";

    public EntityMapParser(String entityMap) throws IllegalStateException {
        entities = new ArrayList<>();
        getSimData(entityMap);
        properties.entrySet().forEach(entry->{
            String entityName = (String) entry.getKey();
            String[] entityDataArray = ((String) entry.getValue()).replaceAll("\\s+","").split(",");
            EntityParser entityParser = new EntityParser(entityName, entityDataArray);
            entities.add(createEntityInstance(entityParser.getAttributeMap(), entityParser.getStateMap()));
        });
    }

    private Entity createEntityInstance(Map<String, String> attributeMap, Map<Integer, String> stateMap) {
        try {
            String type = attributeMap.get("Type").toLowerCase();
            String entityType = attributeMap.get("EntityType");
            String className = String.format(ENTITY_PACKAGE, type, entityType);

            Class<?> entityClass = Class.forName(className);
            return (Entity) entityClass.getDeclaredConstructor(Map.class, Map.class).newInstance(attributeMap, stateMap);

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that gets the simulation data
     */
    private void getSimData(String map) throws IllegalStateException {
        File simFile = new File(String.format(ENTITY_MAP_DIRECTORY, map));
        properties = new Properties();
        try {
            properties.load(new FileReader(simFile));
        }
        catch (IOException e) {
            throw new IllegalStateException("fileUploadError", e);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }
}


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
    private File simFile;
    private List<Entity> entities;

    public EntityMapParser(File simFile) throws IllegalStateException {
        this.simFile = simFile;
        entities = new ArrayList<>();
        getSimData();
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
            double x = Double.parseDouble(attributeMap.get("XPosition"));
            double y = Double.parseDouble(attributeMap.get("YPosition"));

            Class<?> entityClass = Class.forName("ooga.model." + type + "." + entityType);
            return (Entity) entityClass.getDeclaredConstructor(Double.class, Double.class).newInstance(x, y);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that gets the simulation data
     */
    private void getSimData() throws IllegalStateException {
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


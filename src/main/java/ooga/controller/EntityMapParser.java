package ooga.controller;

import ooga.model.entities.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * This class takes in an entitymap and creates the entities associated with the map
 */
public class EntityMapParser {
    private Properties properties;
    private Map<String, Entity> entities;
    private static final String ENTITY_PACKAGE = "ooga.model.%s.%s";
    private static final String ENTITY_MAP_DIRECTORY = "data/%s.sim";

    /**
     * Constructor for EntityMapParser, constructs the entities from the entity map (parsing)
     * @param entityMapLocation - the name of the entity map
     */
    public EntityMapParser(String entityMapLocation) throws IllegalStateException {
        entities = new HashMap<>();
        GeneralParser simParser = new GeneralParser();
        properties = simParser.getSimData(String.format(ENTITY_MAP_DIRECTORY, entityMapLocation));
        properties.entrySet().forEach(entry->{
            String entityName = (String) entry.getKey();
            String[] entityDataArray = ((String) entry.getValue()).replaceAll("\\s+","").split(",");
            EntityParser entityParser = new EntityParser(entityName, entityDataArray);
            entities.put(entityName, createEntityInstance(entityParser.getAttributeMap()));
        });
    }

    /**
     * Creates the model entity list based on the entity map data (no parsing)
     * @param entityMapData
     * @throws IllegalStateException
     */
    public EntityMapParser(Map<String, String> entityMapData) throws IllegalStateException {
        entities = new HashMap<>();
        entityMapData.entrySet().forEach(entry->{
            String entityName = entry.getKey();
            String[] entityDataArray = (entry.getValue()).replaceAll("\\s+","").split(",");
            EntityParser entityParser = new EntityParser(entityName, entityDataArray);
            entities.put(entityName, createEntityInstance(entityParser.getAttributeMap()));
        });
    }

    /**
     * Dynamically creates an instance of an entity
     * @param attributeMap
     * @return
     */
    private Entity createEntityInstance(Map<String, String> attributeMap) {
        try {
            String type = attributeMap.get("Type").toLowerCase();
            String entityType = attributeMap.get("EntityType");
            String className = String.format(ENTITY_PACKAGE, type, entityType);

            Class<?> entityClass = Class.forName(className);
            return (Entity) entityClass.getDeclaredConstructor(Map.class).newInstance(attributeMap);

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the list of entities
     */
    public Map<String, Entity> getEntities() {
        return entities;
    }
}


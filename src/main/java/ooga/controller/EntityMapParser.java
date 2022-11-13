package ooga.controller;

import ooga.model.Entity;

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

    /**
     * Constructor for EntityMapParser
     * @param entityMap
     */
    public EntityMapParser(String entityMap) throws IllegalStateException {
        entities = new ArrayList<>();
        GeneralParser simParser = new GeneralParser();
        properties = simParser.getSimData(String.format(ENTITY_MAP_DIRECTORY, entityMap));
        properties.entrySet().forEach(entry->{
            String entityName = (String) entry.getKey();
            String[] entityDataArray = ((String) entry.getValue()).replaceAll("\\s+","").split(",");
            EntityParser entityParser = new EntityParser(entityName, entityDataArray);
            entities.add(createEntityInstance(entityParser.getAttributeMap(), entityParser.getStateMap()));
        });
    }

    /**
     * Dynamically creates an instance of an entity
     * @param attributeMap
     * @param stateMap
     * @return
     */
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
     * Returns the list of entities
     */
    public List<Entity> getEntities() {
        return entities;
    }
}


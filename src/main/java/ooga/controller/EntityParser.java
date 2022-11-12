package ooga.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EntityParser {
    private String entityName;
    private String entityType;
    private String XPosition;
    private String YPosition;
    private Properties entityProperties;
    private Map<String, String> attributeMap;
    private Map<Integer, String> stateMap;

    public EntityParser(String entityName, String[] entityData) {
        this.entityName = entityName;
        this.entityType = entityData[0];
        this.XPosition = entityData[1];
        this.YPosition = entityData[2];
        getEntitySimData(entityType);
        attributeMap = new HashMap<>();
        stateMap = new HashMap<>();
        createAttributeAndStateMap();
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public Map<Integer, String> getStateMap() {
        return stateMap;
    }

    private void createAttributeAndStateMap() {
        attributeMap.put("XPosition", XPosition);
        attributeMap.put("YPosition", YPosition);
        attributeMap.put("EntityType", entityType);
        attributeMap.put("Name", entityName);

        entityProperties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            String value = ((String) entry.getValue()).replaceAll("\\s+","");
            if (key.startsWith("State")) {
                int state = Integer.parseInt(key.replace("State", ""));
                stateMap.put(state, value);
            }
            else {
                attributeMap.put(key, value);
            }

        });
    }

    private void getEntitySimData(String entityType) throws IllegalStateException {
        File simFile = new File("data/" + entityType + ".sim");
        entityProperties = new Properties();
        try {
            entityProperties.load(new FileReader(simFile));
        }
        catch (IOException e) {
            throw new IllegalStateException("fileUploadError", e);
        }
    }

}

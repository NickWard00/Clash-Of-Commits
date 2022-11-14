package ooga.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EntityParser {
    private String entityName;
    private String entityType;
    private String XPosition;
    private String YPosition;
    private Properties entityProperties;
    private Map<String, String> attributeMap;
    private Map<Integer, List<String>> stateMap;

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

    public Map<Integer, List<String>> getStateMap() {
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
                List<String> stateStrings = getStateData(value);
                stateMap.put(state, stateStrings);
            }
            else {
                attributeMap.put(key, value);
            }

        });
    }

    private List<String> getStateData(String stringData) {
        List<String> states = new ArrayList<>();
        try {
            List<String> listData = Arrays.stream(stringData.split(",")).toList();
            String directionString = String.valueOf(listData.stream().filter(data -> data.startsWith("Direction")).findAny()).replaceAll("Direction=", "");
            String movementString = String.valueOf(listData.stream().filter(data -> data.startsWith("Movement")).findAny()).replaceAll("Movement=", "");
            states.add(directionString);
            states.add(movementString);
            return states;
        }
        catch(NullPointerException n) {
            throw new RuntimeException(n);
        }
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

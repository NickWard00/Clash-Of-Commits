package ooga.controller;

import ooga.model.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AttackParser {

    private String attackType;
    private Properties attackProperties;
    private static final String ATTACK_DIRECTORY = "data/attack/%s.sim";
    private Map<String, Double> attributeMap;
    private String imagePath;


    public AttackParser(Entity entity) {
        GeneralParser simParser = new GeneralParser();
        attackType = entity.getAttackType();
        attackProperties = simParser.getSimData(String.format(ATTACK_DIRECTORY, attackType));
        attributeMap = new HashMap<>();
        createAttributeMapAndImagePath();
    }

    /**
     * Returns the attribute map
     */
    public Map<String, Double> getAttributeMap() {
        return attributeMap;
    }


    /**
     * Creates the attribute map
     */
    private void createAttributeMapAndImagePath() {
        attackProperties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            if (!key.equals("Sprites")) {
                Double value = Double.parseDouble(((String) entry.getValue()).replaceAll("\\s+",""));
                attributeMap.put(key, value);
            } else {
                imagePath = ((String) entry.getValue()).replaceAll("\\s+","");
            }
        });
    }

    public String getImagePath() {
        return imagePath;
    }

}

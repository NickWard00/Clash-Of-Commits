package ooga.controller;

import ooga.model.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class is responsible for parsing the attack properties file and creating the attack map
 * that will be used to create the attacks for the entities in the game.
 * @author Nicki Lee & Nick Ward
 */

public class AttackParser {

    private Entity myEntity;
    private String attackType;
    private Properties attackProperties;
    private static final String ATTACK_DIRECTORY = "data/attack/%s.sim";
    private Map<String, Double> attributeMap;
    private String imagePath;

    /**
     * Constructor for the AttackParser class
     * @param entity
     */
    public AttackParser(Entity entity) {
        GeneralParser simParser = new GeneralParser();
        myEntity = entity;
        attackType = myEntity.getAttackType();
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
                imagePath = String.format("%s%s_", imagePath, myEntity.getClass().getSuperclass().getSimpleName());
            }
        });
    }

    /**
     * Returns the image path
     * @return imagePath - the image path
     */
    public String getImagePath() {
        return imagePath;
    }

}

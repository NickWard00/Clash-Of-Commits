package ooga.controller;

import ooga.model.entities.Entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Nick Ward, Melanie Wang
 */
public class SaveFileParser {
    private Properties properties;
    private static final String SAVE_DIRECTORY = "data/Saves/Save_%s.sim";
    private String mapName;
    private String gameType;
    private String timeDate;
    private Map<String, String> entityMap;
    private EntityMapParser entityMapParser;

    /**
     * Constructor for SaveFileParser
     */
    public SaveFileParser(){
        entityMap = new HashMap<>();
        properties = new Properties();
    }

    /**
     * Saves the game's current state
     * @param saveFile the save file number
     * @param modelEntities the entities in the game
     * @param mapName the name of the map
     * @param gameType the type of game
     */
    public void saveGame(int saveFile, Map<String, Entity> modelEntities, String mapName, String gameType) {
        properties.setProperty("Map", mapName);
        properties.setProperty("GameType", gameType);
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        properties.setProperty("TimeDate", LocalDateTime.now().format(myFormat));
        modelEntities.entrySet().forEach(entry->{
            String entityName = entry.getKey();
            Entity entity = entry.getValue();
            properties.setProperty(entityName, entity.getMyAttributes().get("EntityType") + ", " + entity.coordinates().get(0) + ", " + entity.coordinates().get(1));
        });
        try {
            properties.store(new FileWriter(String.format(SAVE_DIRECTORY, saveFile)), null);
        } catch (IOException e) {
            throw new IllegalStateException("saveFileCannotSave", e);
        }
    }

    /**
     * Loads the game's current state
     * @param saveFile the number of the save file
     * @return
     */
    public void loadGame(int saveFile) throws IllegalStateException {
        GeneralParser simParser = new GeneralParser();
        try {
            properties = simParser.getSimData(String.format(SAVE_DIRECTORY, saveFile));
        } catch (IllegalStateException e) {
            throw new IllegalStateException("saveFileNotFound", e);
        }
        properties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            if (key.equals("Map")){
                mapName = (String) entry.getValue();
            }
            else if (key.equals("GameType")){
                gameType = (String) entry.getValue();
            }
            else if (key.equals("TimeDate")){
                timeDate = (String) entry.getValue();
            }
            else {
                String[] entityDataArray = ((String) entry.getValue()).replaceAll("\\s+","").split(",");
                if (entityDataArray.length < 1){
                    throw new IllegalStateException("saveFileCorrupted");
                }
                File[] files = new File("data/entity").listFiles();
                String[] fileNames = Arrays.stream(files).map(File::getName).toArray(String[]::new);
                if (!Arrays.asList(fileNames).contains(String.format("%s.sim", entityDataArray[0]))){
                    throw new IllegalStateException("saveFileCorrupted");
                }
                entityMap.put(key, (String) entry.getValue());
            }
        });
        entityMapParser = new EntityMapParser(entityMap);
    }

    /**
     * Deletes the save file based on the save file number
     * @param saveFile the save file number
     */
    public void deleteSaveFile(int saveFile) {
        File file = new File(String.format(SAVE_DIRECTORY, saveFile));
        if (!file.delete()){
            throw new IllegalStateException("cannotDeleteSaveFile");
        }
    }

    /**
     * Loads the information of a save file if given the save file number
     * @param num the number of the save file
     */
    public void loadSaveInformation(int num){
        GeneralParser simParser = new GeneralParser();
        try {
            properties = simParser.getSimData(String.format(SAVE_DIRECTORY, num));
        } catch (IllegalStateException e) {
            throw new IllegalStateException("saveFileNotFound", e);
        }
        properties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            if (key.equals("Map")){
                mapName = (String) entry.getValue();
            }
            else if (key.equals("GameType")){
                gameType = (String) entry.getValue();
            }
            else if (key.equals("TimeDate")){
                timeDate = (String) entry.getValue();
            }
    });
    }

    /**
     * Returns the map name associated with the save file
     * @return the map name
     */

    public String getMapName(){
        return mapName;
    }

    /**
     * Returns the game type associated with the save file
     * @return the game type
     */
    public String getGameType(){
        return gameType;
    }

    /**
     * Returns the time and date associated with the save file
     * @return the time and date
     */
    public String getTimeDate(){
        return timeDate;
    }

    /**
     * Returns the entity map associated with the save file
     * @return the entity map
     */
    public Map<String, Entity> getEntities(){
        return entityMapParser.getEntities();
    }

}


package ooga.controller;

import ooga.model.entities.Entity;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ooga.model.powerup.PowerUp;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Nick Ward, Melanie Wang
 */
public class SaveFileParser {
    private JSONObject jsonProperties;
    private static final String SAVE_DIRECTORY = "data/Saves/Save_%s.json";
    private static final String TIME_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    private String mapName;
    private String gameType;
    private String timeDate;
    private String myHP;
    private String myScore;
    private Map<String, String> entityMap;
    private Map<String, String> powerUpMap;
    private EntityMapParser entityMapParser;
    private PowerUpParser powerUpParser;
    private FireBase fireBase;

    /**
     * Constructor for SaveFileParser
     */
    public SaveFileParser(){
        entityMap = new HashMap<>();
        powerUpMap = new HashMap<>();
        jsonProperties = new JSONObject();
    }

    /**
     * Saves the game's current state
     * @param saveFile the save file number
     * @param modelEntities the entities in the game
     * @param mapName the name of the map
     * @param gameType the type of game
     */
    public void saveGame(int saveFile, Map<String, Entity> modelEntities, Map<List<Double>, PowerUp> modelPowerUps, String mapName, String gameType, String hp, String score) throws IllegalStateException {
        jsonProperties = new JSONObject();

        jsonProperties.put("Map", mapName);
        jsonProperties.put("GameType", gameType);
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern(TIME_DATE_FORMAT);
        jsonProperties.put("TimeDate", LocalDateTime.now().format(myFormat));
        jsonProperties.put("HP", hp);
        jsonProperties.put("Score", score);
        modelEntities.entrySet().forEach(entry->{
            String entityName = entry.getKey();
            Entity entity = entry.getValue();
            jsonProperties.put(entityName, entity.getMyAttributes().get("EntityType") + ", " + entity.coordinates().get(0) + ", " + entity.coordinates().get(1));
        });
        modelPowerUps.entrySet().forEach(entry->{
            String powerUpName = entry.getValue().getName();
            String powerUpType = entry.getValue().getPowerUpType();
            List<Double> coordinates = entry.getKey();
            jsonProperties.put(powerUpName, powerUpType + ", " + coordinates.get(0) + ", " + coordinates.get(1));
        });
        try {
            FileWriter file = new FileWriter(String.format(SAVE_DIRECTORY, saveFile));
            file.write(jsonProperties.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new IllegalStateException("saveFileCannotSave", e);
        }
    }

    /**
     * Loads the save file saved to the web database.
     * Uses a callback interface because getting information from the web is asynchronous
     * (essentially, this method waits until the data is downloaded before returning the JSONObject)
     * Then, writes the JSONObject from the web into our local save, Save_4, and then calls
     * loadGame in order to load our newly updated save 4.
     */
    public void loadGameFromWeb() throws IllegalStateException {
        if (fireBase == null) {
            fireBase = new FireBase(String.format(SAVE_DIRECTORY, 4));
        }
        fireBase.readData(new CallBack() {
            @Override
            public void onCallback(JSONObject value) {
                try {
                    FileWriter localSave = new FileWriter(String.format(SAVE_DIRECTORY, 4));
                    localSave.write(value.toJSONString());
                    localSave.close();
                } catch (IOException e) {
                    throw new IllegalStateException("webFileCannotLoad", e);
                }
            }
        });
    }

    /**
     * Used to save our save file to the web.
     * Saves the file locally first, and then calls on Firebase to send the file to the online database.
     * @param saveFile the number of the file we want to save
     * @param modelEntities the entities present during our save
     * @param mapName the name of the map the save is on
     * @param gameType the type of game the player wants to save
     * @param hp how much health the player had at the time of the save
     * @param score the score at the time of the save
     */
    public void saveGameToWeb(int saveFile, Map<String, Entity> modelEntities, Map<List<Double>, PowerUp> modelPowerUps, String mapName, String gameType, String hp, String score){
        if (fireBase == null) {
            fireBase = new FireBase(String.format(SAVE_DIRECTORY, 4));
        }
        saveGame(saveFile, modelEntities, modelPowerUps, mapName, gameType, hp, score);
        try {
            JSONObject file = (JSONObject) new JSONParser().parse(new FileReader(String.format(SAVE_DIRECTORY, saveFile)));
            fireBase.update(file);
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("cloudCannotSave", e);
        }
    }

    /**
     * Loads the game's current state
     * @param saveFile the number of the save file
     */
    public void loadGame(int saveFile) throws IllegalStateException {
        try {
            jsonProperties = (JSONObject) new JSONParser().parse(new FileReader(String.format(SAVE_DIRECTORY, saveFile)));
        } catch (IOException e) {
            throw new IllegalStateException("saveFileCannotLoad", e);
        } catch (ParseException e) {
            throw new IllegalStateException("saveFileCannotParse", e);
        }
        jsonProperties.keySet().forEach(key->{
            String keyStr = key.toString();
            if (keyStr.equals("Map")){
                mapName = jsonProperties.get(key).toString();
            }
            else if (keyStr.equals("GameType")){
                gameType = jsonProperties.get(key).toString();
            }
            else if (keyStr.equals("TimeDate")){
                timeDate = jsonProperties.get(key).toString();
            }
            else if (keyStr.equals("HP")){
                myHP = jsonProperties.get(key).toString();
            }
            else if (keyStr.equals("Score")){
                myScore = jsonProperties.get(key).toString();
            }
            else {
                String[] entityDataArray = (jsonProperties.get(key).toString()).replaceAll("\\s+","").split(",");
                if (entityDataArray.length < 1){
                    throw new IllegalStateException("saveFileCorrupted");
                }
                if (entityDataArray[0].contains("PowerUp")){
                    powerUpMap.put(keyStr, jsonProperties.get(key).toString());
                } else {
                    File[] entityFiles = new File("data/entity").listFiles();
                    String[] entityFileNames = Arrays.stream(entityFiles).map(File::getName).toArray(String[]::new);
                    if (!Arrays.asList(entityFileNames).contains(String.format("%s.sim", entityDataArray[0]))){
                        throw new IllegalStateException("saveFileCorrupted");
                    }
                    entityMap.put(keyStr, jsonProperties.get(key).toString());
                }
            }
        });
        entityMapParser = new EntityMapParser(entityMap);
        powerUpParser = new PowerUpParser(powerUpMap);
    }

    /**
     * Loads the information of a save file if given the save file number
     * @param num the number of the save file
     */
    public void loadSaveInformation(int num) throws IllegalStateException {
        try {
            jsonProperties = (JSONObject) new JSONParser().parse(new FileReader(String.format(SAVE_DIRECTORY, num)));
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("saveFileNotFound", e);
        }
        mapName = jsonProperties.get("Map").toString();
        gameType = jsonProperties.get("GameType").toString();
        timeDate = jsonProperties.get("TimeDate").toString();
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

    /**
     * Returns the power ups associated with the save file
     * @return the power ups
     */
    public Map<List<Double>, PowerUp> getPowerUps(){
        return powerUpParser.getPowerUps();
    }

    /**
     * Returns the HP associated with the save file
     * @return the HP of the hero
     */
    public String getHeroHP() {
    	return myHP;
    }

    /**
     * Returns the score associated with the save file
     * @return the score
     */
    public String getSaveScore() {
    	return myScore;
    }
}


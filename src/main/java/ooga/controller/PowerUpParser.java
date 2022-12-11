package ooga.controller;

import ooga.model.powerup.PowerUp;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PowerUpParser {

    private Map<List<Integer>, PowerUp> myPowerUps;
    private static final String POWER_UP_DATA_DIRECTORY = "data/PowerUp_MainMap.sim";
    private static final String POWER_UP_PATH = "ooga.model.powerup.";
    private Properties powerUpProperties;

    public PowerUpParser() {
        GeneralParser simParser = new GeneralParser();
        myPowerUps = new HashMap<>();
        powerUpProperties = simParser.getSimData(POWER_UP_DATA_DIRECTORY);
    }

    public Map<List<Integer>, PowerUp> allPowerUps() {
        powerUpProperties.entrySet().forEach(entry->{
            String powerUpType = (String) entry.getKey();
            List<String> val = Arrays.stream(entry.getValue().toString().replaceAll("\\s+","").split(",")).toList();
            List<Integer> coordinates = Arrays.asList(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)));
            PowerUp powerUp = createPowerUp(powerUpType, coordinates.get(0), coordinates.get(1));
            myPowerUps.put(coordinates, powerUp);
        });
        return myPowerUps;
    }

    private PowerUp createPowerUp(String type, int x, int y) {
        try {
            Class powerUpClass = Class.forName(String.format("%s%sPowerUp", POWER_UP_PATH, type));
            Object o = powerUpClass.getConstructor(int.class, int.class).newInstance(x,y);
            return (PowerUp) o;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}

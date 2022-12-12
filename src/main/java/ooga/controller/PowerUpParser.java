package ooga.controller;

import ooga.model.powerup.PowerUp;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PowerUpParser {
    private Map<List<Double>, PowerUp> myPowerUps;
    private static final String POWER_UP_PATH = "ooga.model.powerup.";
    private Properties powerUpProperties;

    public PowerUpParser(String powerUpPath) throws IllegalStateException {
        myPowerUps = new HashMap<>();
        GeneralParser simParser = new GeneralParser();
        powerUpProperties = simParser.getSimData(powerUpPath);

        powerUpProperties.entrySet().forEach(entry->{
            String name = (String) entry.getKey();
            String[] powerUpDataArray = ((String) entry.getValue()).replaceAll("\\s+","").split(",");
            List<Double> coordinates = Arrays.asList(Double.parseDouble(powerUpDataArray[1]), Double.parseDouble(powerUpDataArray[2]));
            PowerUp powerUp = createPowerUp(name, powerUpDataArray[0], coordinates.get(0).intValue(), coordinates.get(1).intValue());
            myPowerUps.put(Arrays.asList(coordinates.get(0), coordinates.get(1)), powerUp);
        });
    }

    public PowerUpParser(Map<String, String> powerUpData) throws IllegalStateException {
        myPowerUps = new HashMap<>();

        powerUpData.entrySet().forEach(entry->{
            String name = entry.getKey();
            String[] powerUpDataArray = (entry.getValue()).replaceAll("\\s+","").split(",");
            List<Double> coordinates = Arrays.asList(Double.parseDouble(powerUpDataArray[1]), Double.parseDouble(powerUpDataArray[2]));
            PowerUp powerUp = createPowerUp(name, powerUpDataArray[0], coordinates.get(0).intValue(), coordinates.get(1).intValue());
            myPowerUps.put(Arrays.asList(coordinates.get(0), coordinates.get(1)), powerUp);
        });
    }

    public Map<List<Double>, PowerUp> getPowerUps() {
        return myPowerUps;
    }

    private PowerUp createPowerUp(String name, String type, int x, int y) {
        try {
            Class powerUpClass = Class.forName(String.format("%s%s", POWER_UP_PATH, type));
            Object o = powerUpClass.getConstructor(String.class, int.class, int.class).newInstance(name, x, y);
            return (PowerUp) o;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException("noPowerUpFound", e);
        }
    }

}

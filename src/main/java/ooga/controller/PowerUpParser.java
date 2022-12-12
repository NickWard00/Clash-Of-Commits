package ooga.controller;

import ooga.model.powerup.PowerUp;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PowerUpParser {
    private Map<List<Double>, PowerUp> myPowerUps;
    private static final String POWER_UP_PATH = "ooga.model.powerup.";
    private Properties powerUpProperties;

    public PowerUpParser(String powerUpPath) throws IllegalStateException {
        GeneralParser simParser = new GeneralParser();
        myPowerUps = new HashMap<>();
        powerUpProperties = simParser.getSimData(powerUpPath);
    }

    public Map<List<Double>, PowerUp> allPowerUps() {
        powerUpProperties.entrySet().forEach(entry->{
            String powerUpType = (String) entry.getKey();
            List<String> val = Arrays.stream(entry.getValue().toString().replaceAll("\\s+","").split(",")).toList();
            List<Double> coordinates = Arrays.asList(Double.parseDouble(val.get(0)), Double.parseDouble(val.get(1)));
            PowerUp powerUp = createPowerUp(powerUpType, coordinates.get(0).intValue(), coordinates.get(1).intValue());
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

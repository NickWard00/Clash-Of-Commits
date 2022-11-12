package ooga.controller;

import ooga.view.MapWrapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MapParser {
    private Properties properties;
    private File simFile;
    private Map<String, String> mapInfo;
    private MapWrapper mapWrapper;
    private double mapSize_X;
    private double mapSize_Y;
    private double cellSize;

    public MapParser(String mapSim) throws IllegalStateException {
        this.simFile = new File("data/maps/" + mapSim + ".sim");
        getSimData();
        mapInfo = new HashMap<>();
        properties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            String value = ((String) entry.getValue()).replaceAll("\\s+","");
            mapInfo.put(key, value);
        });
        CSVParser csvParser = new CSVParser();
        mapWrapper = csvParser.parseData(mapInfo.get("Map"));
        generateMapProperties();
    }

    private void generateMapProperties() {
        cellSize = Double.parseDouble(mapInfo.get("BoxSize"));
        mapSize_X = cellSize * mapWrapper.getColumnSize();
        mapSize_Y = cellSize * mapWrapper.getRowSize(0);
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }

    /**
     * Method that gets the simulation data
     */
    private void getSimData() throws IllegalStateException {
        properties = new Properties();
        try {
            properties.load(new FileReader(simFile));
        }
        catch (IOException e) {
            throw new IllegalStateException("fileUploadError", e);
        }
    }
}


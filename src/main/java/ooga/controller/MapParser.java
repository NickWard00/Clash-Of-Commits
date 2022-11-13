package ooga.controller;

import ooga.view.MapWrapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MapParser {
    private Properties properties;
    private Map<String, String> mapInfo;
    private MapWrapper mapWrapper;
    private double mapSize_X;
    private double mapSize_Y;
    private double cellSize;
    private static final String MAP_DIRECTORY = "data/maps/%s.sim";

    public MapParser(String mapSim) throws IllegalStateException {
        GeneralParser simParser = new GeneralParser();
        properties = simParser.getSimData(String.format(MAP_DIRECTORY, mapSim));
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

    public List<Double> getMapProperties() {
        return List.of(cellSize, mapSize_X, mapSize_Y);
    }

    public MapWrapper getMapWrapper() {
        return mapWrapper;
    }
}


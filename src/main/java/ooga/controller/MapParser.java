package ooga.controller;

import ooga.view.MapWrapper;

import java.util.*;

public class MapParser {
    private Properties properties;
    private Map<String, String> mapInfo;
    private Map<String, String> mapCSVSelection;
    private double mapSize_X;
    private double mapSize_Y;
    private double cellSize;
    private Map<String, MapWrapper> allMaps;
    private static final String MAP_DIRECTORY = "data/maps/%s.sim";

    public MapParser(String mapSim) throws IllegalStateException {
        GeneralParser simParser = new GeneralParser();
        mapInfo = new HashMap<>();
        mapCSVSelection = new HashMap<>();
        allMaps = new HashMap<>();

        properties = simParser.getSimData(String.format(MAP_DIRECTORY, mapSim));

        populateCSVandInfoMaps();

        CSVParser csvParser = new CSVParser();

        mapCSVSelection.entrySet().forEach(entry->{
            String key = entry.getKey();
            String value = entry.getValue();

            MapWrapper mapData = csvParser.parseData(value);
            allMaps.put(key, mapData);
        });
        generateMapProperties();
    }

    private void populateCSVandInfoMaps() {
        properties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            String value = ((String) entry.getValue()).replaceAll("\\s+","");
            if (key.startsWith("Map")) {
                mapCSVSelection.put(key, value);
            }
            else {
                mapInfo.put(key, value);
            }
        });
    }

    private void generateMapProperties() {
        cellSize = Double.parseDouble(mapInfo.get("BoxSize"));
        mapSize_X = cellSize * allMaps.get("Map").getColumnSize();
        mapSize_Y = cellSize * allMaps.get("Map").getRowSize(0);
    }

    public List<Double> getMapProperties() {
        return List.of(cellSize, mapSize_X, mapSize_Y);
    }

    public MapWrapper getMapWrapper() {
        return allMaps.get("Map");
    }
}


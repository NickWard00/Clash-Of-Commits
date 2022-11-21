package ooga.controller;

import ooga.model.obstacle.Obstacle;
import ooga.view.MapWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class MapParser {
    private Properties properties;
    private Map<String, String> mapInfo;
    private Map<String, String> mapCSVSelection;
    private Map<Integer, String> stateToImage;
    private Map<String, ObstacleEnum> obstacleMap;
    private double mapSize_X;
    private double mapSize_Y;
    private double cellSize;
    private Map<String, MapWrapper> allMaps;
    private static final String MAP_DIRECTORY = "data/maps/%s.sim";

    /**
     * Constructor for MapParser
     * @param mapSim
     */
    public MapParser(String mapSim) {
        GeneralParser simParser = new GeneralParser();
        mapInfo = new HashMap<>();
        mapCSVSelection = new HashMap<>();
        allMaps = new HashMap<>();
        stateToImage = new HashMap<>();
        obstacleMap = new HashMap<>();
        properties = simParser.getSimData(String.format(MAP_DIRECTORY, mapSim));

        setImageToState();
        populateCSVandInfoMaps();
        setupMapWrapperMap();
        generateMapProperties();
    }

    /**
     * Sets up the map wrapper map will all of the maps per level
     */
    private void setupMapWrapperMap() {
        CSVParser csvParser = new CSVParser();
        mapCSVSelection.entrySet().forEach(entry->{
            String key = entry.getKey();
            String value = entry.getValue();

            MapWrapper mapData = csvParser.parseData(value);
            allMaps.put(key, mapData);
        });
    }

    /**
     * Populates the mapInfo and mapCSVSelection maps
     */
    private void populateCSVandInfoMaps() {
        properties.entrySet().forEach(entry->{
            String key = (String) entry.getKey();
            String value = ((String) entry.getValue()).replaceAll("\\s+","");
            if (key.startsWith("Map")) {
                mapCSVSelection.put(key, value);
            }
            else if (key.startsWith("Cell")) {
                int state = Integer.parseInt(key.replace("Cell", ""));
                stateToImage.put(state, value);
            }
            else if(key.startsWith("Obstacle")){
                int state = Integer.parseInt(key.replace("Obstacle", ""));
                //Class obstacleClass =
                //  public static Obstacle makeObstacle(Class<? extends Obstacle> obstacleClass, Map<String, String> attributes, Map<Integer, String> states) {
                obstacleMap.put(key,ObstacleEnum.valueOf(key.toUpperCase()));
                Obstacle obstacle = Obstacle.makeObstacle()
                Controller.getModelObstacles().add(obstacle);
            }
            else {
                mapInfo.put(key, value);
            }
        });
    }

    private void setImageToState() {
//        obstacleMap.put("", ObstacleEnum.BUSH);
//        obstacleMap.put("", ObstacleEnum.NORMAL_GRASS);
//        obstacleMap.put("", ObstacleEnum.STUMP);
    }

    /**
     * Generates the map properties
     */
    private void generateMapProperties() {
        cellSize = Double.parseDouble(mapInfo.get("BoxSize"));
        mapSize_X = cellSize * allMaps.get("Map").getColumnSize();
        mapSize_Y = cellSize * allMaps.get("Map").getRowSize(0);
    }

    /**
     * Returns the map properties
     * @return
     */
    public List<Double> getMapProperties() {
        return List.of(cellSize, mapSize_X, mapSize_Y);
    }

    /**
     * Returns the Map Wrapper
     * @return
     */
    public MapWrapper getMapWrapper() {
        return allMaps.get("Map");
    }

    /**
     * Returns the state to image map (given a cell state, returns the path to image)
     * @return stateToImage
     */
    public Map<Integer, String> getStateToImageMap() {
        return stateToImage;
    }

    /**
     * Returns the map info
     * @return mapInfo
     */
    public Map<String, String> getMapInfo() {
        return mapInfo;
    }

    public Map<String, ObstacleEnum> getObstacleMap() {
        return obstacleMap;
    }
}


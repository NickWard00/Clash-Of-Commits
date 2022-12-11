package ooga.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * @author Nick Ward
 */
public class MapView {
    private int numRows;
    private int numColumns;
    private MapWrapper wrapper;
    private GridPane grid;
    private int blockSize;
    private double mapSizeX;
    private double mapSizeY;
    private Map<List<Double>, BlockView> myViewObstacles;
    private String grassBackgroundPath = "blocks/grass.jpeg";

    /**
     * Constructor for MapView
     * @param mapWrapper the map wrapper that holds the map information
     */
    public MapView(MapWrapper mapWrapper){
        this.wrapper = mapWrapper;
        myViewObstacles = new HashMap<>();
    }

    /**
     * Creates the visual map based on the mapWrapper
     * @return
     */
    public GridPane createMap(){
        numRows = wrapper.getColumnSize();
        numColumns = wrapper.getRowSize(0);
        grid = new GridPane();

        blockSize = wrapper.getVisualProperties().get(0).intValue();
        mapSizeX = wrapper.getVisualProperties().get(1);
        mapSizeY = wrapper.getVisualProperties().get(2);

        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numColumns; col++){
                int state = wrapper.getState(row, col);
                String imagePath = wrapper.getImageFromState(state);
                BlockView blockView = new BlockView(col, row, blockSize, state, imagePath);
                grid.add(blockView, col, row);
                if (wrapper.getObstacleFromState(state).contains("Wall") || wrapper.getObstacleFromState(state).contains("PowerUp")) {
                    myViewObstacles.put(Arrays.asList((double) row, (double) col), blockView);
                }
            }
        }
        return grid;
    }

    /**
     * Gets the block views that are wall types
     * @return map of block views that are walls
     */
    public Map<List<Double>, BlockView> getViewObstacles() {
        return this.myViewObstacles;
    }
}

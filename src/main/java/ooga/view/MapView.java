package ooga.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.layout.GridPane;
import ooga.controller.Controller;
import ooga.model.obstacle.Obstacle;

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
    private List<BlockView> obstacleList;

    public MapView(MapWrapper mapWrapper){
        this.wrapper = mapWrapper;
        obstacleList = new ArrayList<>();
    }

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
                BlockView blockView = new BlockView(col, row, blockSize, state, imagePath, grid);
                if (state == 1) {
                    Controller.getViewObstacles().put(Arrays.asList((double) row, (double) col), blockView);
                }

            }
        }
        return grid;
    }

    public List<BlockView> getObstacleList() {
        return this.obstacleList;
    }
}

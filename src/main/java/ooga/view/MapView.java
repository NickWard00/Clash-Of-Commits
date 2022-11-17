package ooga.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import ooga.controller.MapParser;

public class MapView {
    private int numRows;
    private int numColumns;
    private static final double BLOCK_X_OFFSET = 20.0;
    private static final double BLOCK_Y_OFFSET = 20.0;
    //private StackPane stackPane;
    private MapWrapper wrapper;

    private GridPane grid;
    public MapView(MapWrapper mapWrapper){
        this.wrapper = mapWrapper;
    }
    public void moveUp(){

    }
    public void moveDown(){

    }
    public void moveRight(){

    }
    public void moveLeft(){

    }

    public GridPane createMap(){
        numRows = wrapper.getRowSize(0);
        numColumns = wrapper.getColumnSize();
        grid = new GridPane();
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numColumns; col++){
                int state = wrapper.getState(row, col);
                String imagePath = wrapper.getImageFromState(state);

                BlockView blockView = new BlockView(row, col, imagePath, grid);
            }
        }
        return grid;
    }
}

package ooga.view;

import javafx.scene.layout.GridPane;

public class MapView {
    private int numRows;
    private int numColumns;
    private MapWrapper wrapper;
    private GridPane grid;
    public MapView(MapWrapper mapWrapper){
        this.wrapper = mapWrapper;
    }

    public GridPane createMap(){
        numRows = wrapper.getColumnSize();
        numColumns = wrapper.getRowSize(0);
        grid = new GridPane();
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numColumns; col++){
                int state = wrapper.getState(row, col);
                String imagePath = wrapper.getImageFromState(state);
                BlockView blockView = new BlockView(col, row, imagePath, grid);
            }
        }
        return grid;
    }
}

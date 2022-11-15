package ooga.view;

import javafx.scene.layout.StackPane;
import ooga.controller.MapParser;

public class MapView {
    private int numRows;
    private int numColumns;
    private static final double BLOCK_X_OFFSET = 20.0;
    private static final double BLOCK_Y_OFFSET = 20.0;
    private StackPane stackPane;
    public MapView(MapWrapper mapWrapper, StackPane stackPane){
        this.stackPane = stackPane;
        createMap(mapWrapper);
    }
    public void moveUp(){

    }
    public void moveDown(){

    }
    public void moveRight(){

    }
    public void moveLeft(){

    }

    public void createMap(MapWrapper mapWrapper){
        numRows = mapWrapper.getRowSize(0);
        numColumns = mapWrapper.getColumnSize();
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numColumns; col++){
                int state = mapWrapper.getState(row, col);
                BlockView blockView = new BlockView(
                        row*BLOCK_X_OFFSET, col*BLOCK_Y_OFFSET,
                        state, stackPane
                );
            }
        }
//        HARDCODED VERSION BEFORE MAP FILE READ IN:
//        BlockView blockView = new BlockView(
//                BLOCK_X_OFFSET, BLOCK_Y_OFFSET,0, stackPane
//        );
    }
}

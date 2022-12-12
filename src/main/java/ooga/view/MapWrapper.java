package ooga.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nick Ward
 */

public class MapWrapper {
  private int row;
  private int column;
  private List<List<Integer>> grid;
  private Map<Integer, String> stateImageMap;
  private Map<Integer, String> obstacleStateMap;
  private List<Double> visualProperties;

  /**
   * Constructor for MapWrapper
   * @param row
   * @param column
   */
  public MapWrapper(int row, int column) {
    this.row = row;
    this.column = column;
    this.stateImageMap = new HashMap<>();
    this.visualProperties = new ArrayList<>();

    grid = new ArrayList<>();
    for (int i = 0; i < this.row; i++) {
      List<Integer> singleList = new ArrayList<>();
      for (int j = 0; j < this.column; j++) {
        singleList.add(0);
      }
      grid.add(singleList);
    }
  }

  /**
   * Constructor for MapWrapper
   */
  public MapWrapper() {
    this.row = 0;
    this.column = 0;
    this.stateImageMap = new HashMap<>();
    this.visualProperties = new ArrayList<>();
    grid = new ArrayList<>();
  }

  /**
   * Get the value held at the given row and column
   * @param row
   * @param column
   * @return
   */
  public int getState(int row, int column) {
    int ret = 0;
    try {
      ret = grid.get(row).get(column);
    }
    catch(IllegalStateException e) {
      throw new IllegalStateException("invalidRowColumn");
    }
    return ret;
  }

  /**
   * Set the value at the given row and column
   * @param row
   * @param column
   * @param state
   */
  public void setState(int row, int column, int state) {
    if (row < this.row && column < this.column) {
      grid.get(row).set(column, state);
    } else {
      throw new IllegalStateException("outOfBounds");
    }
  }

  /**
   * Adds row to the grid
   */
  public void addRow() {
    grid.add(new ArrayList<>());
    row = grid.size();
  }

  /**
   * Adds value to a certain row
   */
  public void addValueToRow(int row, int value) {
    grid.get(row).add(value);
  }

  /**
   * Returns the number of columns
   * @return
   */
  public int getColumnSize() {
    return grid.size();
  }

  /**
   * Returns the number of rows
   * @param row
   * @return
   */
  public int getRowSize(int row) {
    return grid.get(row).size();
  }

  /**
   * Sets the visual properties of the map
   * @param visualProperties
   */
  public void setVisualProperties(List<Double> visualProperties) {
    this.visualProperties = visualProperties;
  }

  /**
   * Returns the visual properties of the map
   * @return visualProperties of the map (width, height, block size)
   */
  public List<Double> getVisualProperties() {
    return visualProperties;
  }

  /**
   * Sets the state image map
   * @param map State Image Map
   */
  public void setStateToImageMap(Map<Integer, String> map) {
    this.stateImageMap = map;
  }

  /**
   * Gets the image associated with the state
   * @param state
   * @return
   */
  public String getImageFromState(int state) {
    return stateImageMap.get(state);
  }

  /**
   * Sets the obstacle state map
   * @param obstacleStateMap
   */
  public void setObstacleStateMap(Map<Integer, String> obstacleStateMap) {
    this.obstacleStateMap = obstacleStateMap;
  }

  /**
   * Gets the obstacle from the state
   * @param state state of the obstacle
   * @return the obstacle associated with the state
   */
  public String getObstacleFromState(int state) {
    return obstacleStateMap.get(state);
  }

}

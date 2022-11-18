package ooga.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapWrapper {
  private int row = 0;
  private int column = 0;
  private List<List<Integer>> grid;
  private Map<Integer, String> stateImageMap;
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
    try{
      ret = grid.get(row).get(column);
    }
    catch(IllegalStateException e) {
      throw new IllegalStateException("Invalid row or column");
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

  public void setVisualProperties(List<Double> visualProperties) {
    this.visualProperties = visualProperties;
  }

  public List<Double> getVisualProperties() {
    return visualProperties;
  }

  public void setStateToImageMap(Map<Integer, String> map) {
    this.stateImageMap = map;
  }

  public String getImageFromState(int state) {
    return stateImageMap.get(state);
  }
}

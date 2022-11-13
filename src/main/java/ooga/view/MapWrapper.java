package ooga.view;

import java.util.ArrayList;
import java.util.List;

public class MapWrapper {
  private int row = 0;
  private int column = 0;
  private List<List<Integer>> grid;

  public MapWrapper(int row, int column) {
    this.row = row;
    this.column = column;

    grid = new ArrayList<>();
    for (int i = 0; i < this.row; i++) {
      List<Integer> singleList = new ArrayList<>();
      for (int j = 0; j < this.column; j++) {
        singleList.add(0);
      }
      grid.add(singleList);
    }
  }

  public MapWrapper() {
    grid = new ArrayList<>();
  }

  public int getState(int row, int column) {
    return grid.get(row).get(column);
  }

  public void setState(int row, int column, int state) {
    if (row < this.row && column < this.column) {
      grid.get(row).set(column, state);
    } else {
      throw new IllegalStateException("outOfBounds");
    }
  }

  public void addRow() {
    grid.add(new ArrayList<>());
    row = grid.size();
  }

  public void addValueToRow(int row, int value) {
    grid.get(row).add(value);
  }

  public int getColumnSize() {
    return grid.size();
  }

  public int getRowSize(int row) {
    return grid.get(row).size();
  }
}

package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ooga.view.MapWrapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVParser {
  private FileReader myFileReader;

  /**
   * Parses the data from the csv file
   *
   * @param csvPath
   * @return
   */
  public MapWrapper parseData(String csvPath) throws IllegalStateException {
    System.out.println(csvPath);
    try {
      myFileReader = new FileReader(csvPath);
      MapWrapper mapWrapper = new MapWrapper();
      CSVReader csvReader = new CSVReader(myFileReader);
      String[] nextRecord;
      int row = 0;
      while ((nextRecord = csvReader.readNext()) != null) {
        // if any strings in nextRecord start with "#" or "", ignore them
        nextRecord = Arrays.stream(nextRecord).filter(s -> !s.startsWith("#") && !s.isEmpty()).toArray(String[]::new);
        if (nextRecord.length > 0) {
          if (row >= 0) {
            mapWrapper.addRow();
            for (String cell : nextRecord) {
              mapWrapper.addValueToRow(row, Integer.parseInt(cell.trim()));
            }
          }
          row++;
        }
      }
      return mapWrapper;
    } catch (CsvValidationException | IOException e) {
      throw new IllegalStateException("badCsvFile", e);
    }
  }
}

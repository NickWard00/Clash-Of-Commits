package ooga.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This class is responsible for parsing the high scores
 * @author Nick Ward
 */
public class HighScoreParser {
    private Properties highScoreProperties;
    private String myFilePath;
    /**
     * Constructor for HighScoreParser
     * @param filePath
     */
    public HighScoreParser(String filePath) {
        this.myFilePath = filePath;
        GeneralParser simParser = new GeneralParser();
        highScoreProperties = simParser.getSimData(filePath);
    }

    public Map<String, Integer> getHighScores() {
        Map<String, Integer> highScores = new HashMap<>();
        if (!highScoreProperties.isEmpty()) {
            highScoreProperties.entrySet().forEach(entry->{
                String key = (String) entry.getKey();
                String value = ((String) entry.getValue()).replaceAll("\\s+","");
                if (value.matches("[0-9]+")) {
                    highScores.put(key, Integer.parseInt(value));
                } else {
                    throw new IllegalStateException("highScoreFileError");
                }
            });
        } else {
            throw new IllegalStateException("highScoreFileError");
        }
        return highScores;
    }

    public void setHighScores(String map, int score) {
        if (!highScoreProperties.isEmpty() && highScoreProperties.containsKey(map)) {
            int currentHighScore = Integer.parseInt(highScoreProperties.getProperty(map));
            if (score > currentHighScore) {
                highScoreProperties.setProperty(map, Integer.toString(score));
                try {
                    highScoreProperties.store(new FileWriter(myFilePath), null);
                } catch (IOException e) {
                    throw new IllegalStateException("highScoreFileError");
                }
            }
        } else {
            throw new IllegalStateException("highScoreFileError");
        }

    }
}

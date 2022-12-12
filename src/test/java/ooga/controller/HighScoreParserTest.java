package ooga.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HighScoreParserTest {
    private HighScoreParser parser = new HighScoreParser("data/HighScores.sim");

    @Test
    void testHighScoreParser() {
        Map<String, Integer> highScores = parser.getHighScores();
        assertTrue(!highScores.keySet().isEmpty());
    }

    @Test
    void testHighScoreParserError() {
        try {
            HighScoreParser parser = new HighScoreParser("data/NoExist.sim");
        } catch (IllegalStateException e) {
            assertEquals("simFileUploadError", e.getMessage());
        }
    }

    @Test
    void testHighScoreParserSetHighScores() {
        parser.setHighScores("MainMap", 100);
        Map<String, Integer> highScores = parser.getHighScores();
        assertEquals(100, highScores.get("MainMap"));
    }
}

package ooga.controller;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaveFileParserTest {
    @Test
    void saveFileParserGetEntitiesTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        assertTrue(saveFileParser.getEntities().size() > 0);
    }

    @Test
    void saveFileParserErrorTest() {
        try{
            SaveFileParser saveFileParser = new SaveFileParser();
            saveFileParser.loadGame(0);
        } catch (IllegalStateException e) {
            assertEquals("saveFileCannotLoad", e.getMessage());
        }
    }

    @Test
    void saveFileParserGetMapTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        List<String> mapChoices = List.of("MainMap", "ZeldaMap", "BulletHellMap", "CreativeMap");
        assertTrue(mapChoices.contains(saveFileParser.getMapName()));
    }

    @Test
    void saveFileParserGetHeroHPTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        assertTrue(Integer.parseInt(saveFileParser.getHeroHP()) > 0);
    }

    @Test
    void saveFileParserGetTimeDateTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        assertTrue(saveFileParser.getTimeDate() != null);
    }

    @Test
    void saveFileParserGetScoreTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        assertTrue(Integer.parseInt(saveFileParser.getSaveScore()) >= 0);
    }

    @Test
    void saveFileParserGetGameTypeTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        List<String> mapChoices = List.of("The Beginning", "The Legend of Zelda for NES", "Survive", "Creative Mode");
        assertTrue(mapChoices.contains(saveFileParser.getGameType()));
    }

    @Test
    void saveFileParserGetGameLevelTest() {
        SaveFileParser saveFileParser = new SaveFileParser();
        saveFileParser.loadGame(1);
        assertTrue(saveFileParser.getPowerUps().size() >= 0);
    }
}

package ooga.view;

import ooga.controller.MapParser;
import ooga.view.screens.MainGameScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainGameScreenTest {
    @Test
    void startGamePlayTest(){
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());

        MainGameScreen mainGameScreen = new MainGameScreen();
        mainGameScreen.startGamePlay(map, null);
        mainGameScreen.makeScene();
        mainGameScreen.startGamePlay(map, null);
        assertTrue(mainGameScreen.isPlaying());
    }
    @Test
    void startGamePlayErrorTest(){
        MainGameScreen mainGameScreen = new MainGameScreen();
        MapParser mapParser = new MapParser("MainMap");
        MapWrapper map = mapParser.getMapWrapper();
        map.setStateToImageMap(mapParser.getStateToImageMap());

        try{
            mainGameScreen.makeScene();
            mainGameScreen.startGamePlay(map, null);
        }catch(NullPointerException | IllegalStateException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(Exception e){
            //any other exception should be thrown by US
            assertEquals("Incorrect GamePlay", e.getMessage());
        }
    }
}
package ooga.view;

import ooga.view.screens.MainGameScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainGameScreenTest {
    @Test
    void startGamePlayTest(){
        MainGameScreen mainGameScreen = new MainGameScreen();
        mainGameScreen.startGamePlay();
        mainGameScreen.makeScene();
        mainGameScreen.startGamePlay();
        assertTrue(mainGameScreen.isPlaying());
    }
    @Test
    void startGamePlayErrorTest(){
        MainGameScreen mainGameScreen = new MainGameScreen();
        try{
            mainGameScreen.makeScene();
            mainGameScreen.startGamePlay();
        }catch(NullPointerException | IllegalStateException | IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(Exception e){
            //any other exception should be thrown by US
            assertEquals("Incorrect GamePlay", e.getMessage());
        }
    }
}
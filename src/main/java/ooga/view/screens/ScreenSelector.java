package ooga.view.screens;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.controller.Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * @author Nick Ward
 */
public class ScreenSelector {
    private StartScreen startScreen;
    private MainGameScreen mainGameScreen;
    private OpenSaveScreen openSaveScreen;
    private OpenNewGameScreen openNewGameScreen;
    private ChooseGameScreen chooseGameScreen;
    private WinScreen winScreen;
    private LoseScreen loseScreen;
    private Stage myStage;

    public ScreenSelector(Stage stage, ResourceBundle labels) {
        myStage = stage;
        //mainGameScreen = new MainGameScreen(stage, new Controller());
        openSaveScreen = new OpenSaveScreen();
        openNewGameScreen = new OpenNewGameScreen(stage, labels);
        winScreen = new WinScreen();
        loseScreen = new LoseScreen();
        startScreen = new StartScreen(stage);
    }

    public void selectScreen(String screenName) {
        try {
            Scene scene = (Scene) this.getClass().getDeclaredField(screenName).get(this).getClass().getMethod("makeScene").invoke(this.getClass().getDeclaredField(screenName).get(this));
            displayScreen(scene);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("screenNotFound", e);
        }
    }

    private void displayScreen(Scene scene){
        myStage.setScene(scene);
        myStage.show();
    }
}

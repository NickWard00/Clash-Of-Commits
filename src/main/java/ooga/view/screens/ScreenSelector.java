package ooga.view.screens;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ooga.Main;
import ooga.controller.Controller;

import java.lang.reflect.InvocationTargetException;

public class ScreenSelector {
    private StartScreen startScreen;
    private MainGameScreen mainGameScreen;
    private OpenSaveScreen openSaveScreen;
    private OpenNewGameScreen openNewGameScreen;
    private ChooseGameScreen chooseGameScreen;
    private WinScreen winScreen;
    private LoseScreen loseScreen;

    public ScreenSelector(Stage stage, Controller controller) {
        startScreen = new StartScreen(stage);
        mainGameScreen = new MainGameScreen();
        openSaveScreen = new OpenSaveScreen();
        openNewGameScreen = new OpenNewGameScreen(stage, controller);
        chooseGameScreen = new ChooseGameScreen(stage);
        winScreen = new WinScreen();
        loseScreen = new LoseScreen();
    }

    public Scene selectScreen(String screenName) {
        try {
            return (Scene) this.getClass().getDeclaredField(screenName).get(this).getClass().getMethod("makeScene").invoke(this.getClass().getDeclaredField(screenName).get(this));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("methodNotFound", e);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("screenNotFound", e);
        }
    }
}

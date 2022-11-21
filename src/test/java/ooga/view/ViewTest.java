package ooga.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.view.screens.MainGameScreen;
import ooga.view.screens.ScreenSelector;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

public class ViewTest {

    public void step(double elapsedTime){

    }

    public void changeEntityState(String entityName, DirectionState direction) {
        EntityView entity = myViewEntities.get(entityName);
        entity.changeDirection(direction);
    }

package ooga.view;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.state.DirectionState;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nick Ward
 */
class AttackViewTest extends DukeApplicationTest {
    private String attackType;
    private double xPos;
    private double yPos;
    private int size;
    private String spriteLocation;
    private AttackView attackView;
    private DirectionState direction;

    @Override
    public void start(Stage stage) {
        StartScreen ss = new StartScreen(stage);
        stage.setScene(ss.makeScene());
        Controller controller = new Controller(stage, "MainMap", "", ResourceBundle.getBundle("ResourceBundles.LabelsBundle"));
        size = 50;
        xPos = 100;
        yPos = 100;
        attackType = "LongRange";
        spriteLocation = "/attacks/";
        direction = DirectionState.EAST;
        attackView = new AttackView(spriteLocation, attackType, xPos, yPos, size, size, 0);
    }

    @Test
    void testEntityViewNames() {
        assertEquals(attackType, attackView.getAttackType());
    }

    @Test
    void testEntityViewPosX() {
        assertEquals(xPos - size/2, attackView.getX());
    }

    @Test
    void testEntityViewPosY() {
        assertEquals(yPos - size/2, attackView.getY());
    }

    @Test
    void testEntityViewSize() {
        assertEquals(size, attackView.getFitWidth());
        assertEquals(size, attackView.getFitHeight());
    }
}

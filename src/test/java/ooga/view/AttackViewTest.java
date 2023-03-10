package ooga.view;

import javafx.stage.Stage;
import ooga.model.state.DirectionState;
import ooga.view.screens.StartScreen;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        size = 50;
        xPos = 100;
        yPos = 100;
        attackType = "LongRange";
        spriteLocation = "/attacks/";
        direction = DirectionState.EAST;
        attackView = new AttackView(spriteLocation, attackType, size, size);
        attackView.setX(xPos);
        attackView.setY(yPos);
    }

    @Test
    void testEntityViewNames() {
        assertEquals(attackType, attackView.getAttackType());
    }

    @Test
    void testEntityViewPosX() {
        assertEquals(xPos, attackView.getX());
    }

    @Test
    void testEntityViewPosY() {
        assertEquals(yPos, attackView.getY());
    }

    @Test
    void testEntityViewSize() {
        assertEquals(size, attackView.getFitWidth());
        assertEquals(size, attackView.getFitHeight());
    }
}

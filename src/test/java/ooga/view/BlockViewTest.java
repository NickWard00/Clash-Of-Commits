package ooga.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlockViewTest {
    private BlockView blockView;
    private HashMap<Integer, String> expectedValues;

    void mapBlocks(){
        expectedValues = new HashMap<>();
        expectedValues.put(0, "grass.jpeg");
        expectedValues.put(1, "bush.jpeg");
        expectedValues.put(2, "ice.jpeg");
        expectedValues.put(3, "winter_grass.jpeg");

    }

    @Test
    void createBlockView(){
        mapBlocks();
        for (int i = 0; i < 4; i++){
            blockView = new BlockView(0, 0, 0, i, "/blocks/zelda/0.png", "Obstacle");
            assertEquals(List.of(0,0), blockView.getKey());
        }
    }

}
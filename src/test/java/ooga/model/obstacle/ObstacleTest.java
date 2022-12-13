package ooga.model.obstacle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    DestroyableWall testDestroyable;
    ImmovableWall testImmovable;

    @BeforeEach
    void setUp() {
        testDestroyable = new DestroyableWall(2.0, 5.0, 1);
        testImmovable = new ImmovableWall(1.0, 1.0);
    }

    @Test
    void getPositionXTest() {
        assertEquals(2.0, testDestroyable.getPositionX());
        assertEquals(1.0, testImmovable.getPositionX());
    }

    @Test
    void getPositionYTest() {
        assertEquals(5.0, testDestroyable.getPositionY());
        assertEquals(1.0, testImmovable.getPositionY());
    }

    @Test
    void getOnScreenTest() {
        assertTrue(testDestroyable.getOnScreen());
        assertTrue(testImmovable.getOnScreen());
    }


}
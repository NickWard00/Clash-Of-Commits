package ooga.model.obstacle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FeatureTest {
  private static final double TEST_X_POSITION = 1.0;
  private static final double TEST_Y_POSITION = 1.0;
  private Feature feature;

  @Test
  void featureTest() {
    Feature feature = new Feature(1.0, 2.0);
    assertInstanceOf(Feature.class, feature);
  }

  @Test
  void testPositionFeature() {
    feature = new Feature(TEST_X_POSITION, TEST_Y_POSITION);
    assertEquals(TEST_X_POSITION, feature.getPositionX());
    assertEquals(TEST_Y_POSITION, feature.getPositionY());
  }

  @Test
  void testTypeFeature() {
    feature = new Feature(TEST_X_POSITION, TEST_Y_POSITION);
    assertFalse(feature.getBlocker());
    assertFalse(feature.getDestroyable());
  }
}

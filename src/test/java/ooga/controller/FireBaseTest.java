package ooga.controller;

import ooga.view.MapWrapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FireBaseTest {
    @Test
    void FireBaseTest() {
        FireBase fireBase = new FireBase();
        fireBase.update(new JSONObject());
        assertTrue(fireBase != null);
    }
}

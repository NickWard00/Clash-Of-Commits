package ooga.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FireBaseTest {

    FireBase testBase = new FireBase("Save_Test");

    @Test
    void testWebSaveAndLoad() throws IOException, ParseException {
        JSONObject file = (JSONObject) new JSONParser().parse(new FileReader( "data/Saves/Save_Test.json"));
        testBase.update(file);

        testBase.readData(new CallBack() {
            @Override
            public void onCallback(JSONObject value) {
                assertEquals(file, value);
            }
        });

    }

}

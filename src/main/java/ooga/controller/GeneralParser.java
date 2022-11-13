package ooga.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GeneralParser {
    public Properties getSimData(String url) throws IllegalStateException {
        File simFile = new File(url);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(simFile));
            return properties;
        }
        catch (IOException e) {
            throw new IllegalStateException("fileUploadError", e);
        }
    }

}

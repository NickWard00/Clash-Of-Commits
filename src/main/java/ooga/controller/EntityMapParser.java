package ooga.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EntityMapParser {
    private Properties properties;
    private File simFile;

    public EntityMapParser(File simFile) throws IllegalStateException {
        this.simFile = simFile;
        getSimData();
        properties.entrySet().forEach(entry->{
            String entityName = (String) entry.getKey();
            String[] entityDataArray = ((String) entry.getValue()).split(",");
        });
    }

    /**
     * Method that gets the simulation data
     */
    public void getSimData() throws IllegalStateException {
        properties = new Properties();
        try {
            properties.load(new FileReader(simFile));
        }
        catch (IOException e) {
            throw new IllegalStateException("fileUploadError", e);
        }
    }
}


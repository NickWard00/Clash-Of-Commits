package ooga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows for uploading and downloading a save file from the realtime database.
 *
 * @author Melanie Wang
 */


/**
 * Establishes connection with database
 */
public class FireBase {
    private final String DATABASE_URL = "https://ooga-team-6-default-rtdb.firebaseio.com";
    private final String GOOGLE_APPLICATION_CREDENTIALS = "src/main/resources/firebase/ooga-team-6-firebase-adminsdk-s9ddn-49ef12d240.json";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private FirebaseApp primaryApp;

    private List<FirebaseApp> firebaseAppList = new ArrayList<>();

    private JSONObject saveFileWeb;

    public FireBase() {

        try {
            File file = new File(GOOGLE_APPLICATION_CREDENTIALS);
            InputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();

            firebaseAppList = FirebaseApp.getApps();
            if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
                for (FirebaseApp app : firebaseAppList) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                        primaryApp = app;
                }
            } else {
                primaryApp = FirebaseApp.initializeApp(options);
            }
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);
            ref = firebaseDatabase.getReference("Save_4");

        } catch (IOException io) {
            System.out.println(io.getMessage());

        }
    }

    /**
     * once given our desired save file, this method will upload the data to the database.
     * @param file
     */
    public void update(JSONObject file) {
        ref.setValue(file, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    //System.out.println("Data Save Failure" + databaseError.getMessage());
                } else {
                   // System.out.println("Data Saved!");
                }
            }
        });
    }

    /**
     * Reads data from the online database and sends it to other classes via callBack.
     * @param callBack the interface used specifically to get around the asynchronous behavior of
     * value event listeners. Without using this, the saveFileWeb will always be null in other classes.
     */
    public void readData(CallBack callBack) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object s = dataSnapshot.getValue();
                try {
                    saveFileWeb = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(s));
                    callBack.onCallback(saveFileWeb);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               //log error here
            }
        });

    }
}



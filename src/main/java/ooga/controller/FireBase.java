package ooga.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FireBase {
    private final String DATABASE_URL = "https://ooga-team-6-default-rtdb.firebaseio.com";
    private final String GOOGLE_APPLICATION_CREDENTIALS = "src/main/resources/firebase/ooga-team-6-firebase-adminsdk-s9ddn-49ef12d240.json";
    private FirebaseDatabase firebaseDatabase;
    public FireBase() {
        try {
        File file = new File(GOOGLE_APPLICATION_CREDENTIALS);
        InputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://ooga-team-6-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);
            System.out.println(firebaseDatabase);
        } catch (IOException io) {
            System.out.println(io.getMessage());

        }
    }
        public void update(JSONObject file, String key) {
            DatabaseReference ref = firebaseDatabase.getReference(key);
            ref.setValue(file, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("Data could not be saved " + databaseError.getMessage());
                    } else {
                        System.out.println("Data saved successfully.");
                    }
                }});
        }
    }



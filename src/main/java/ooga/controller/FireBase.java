package ooga.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class FireBase {
    private final String DATABASE_URL = "firebase/ooga-team-6-firebase-adminsdk-s9ddn-49ef12d240.json";
    FileInputStream serviceAccount =
            new FileInputStream("firebase/ooga-team-6-firebase-adminsdk-s9ddn-49ef12d240.json");
    private FirebaseDatabase firebaseDatabase;
    public FireBase() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ooga-team-6-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        firebaseDatabase= FirebaseDatabase.getInstance(DATABASE_URL);
    }
        public void update(Object value, String key) {
            try {
                DatabaseReference ref = firebaseDatabase.getReference(key);
                final CountDownLatch latch = new CountDownLatch(1);
                ref.setValue(value, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved " + databaseError.getMessage());
                            latch.countDown();
                        } else {
                            System.out.println("Data saved successfully.");
                            latch.countDown();
                        }
                    }
                });
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



package com.akcitra.Auctionation.auction;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/java/com/akcitra/Auctionation/auction/secret.json");

        FirebaseOptions.Builder builder = FirebaseOptions.builder();
        FirebaseOptions options = builder
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://auctionation-3d056-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

}

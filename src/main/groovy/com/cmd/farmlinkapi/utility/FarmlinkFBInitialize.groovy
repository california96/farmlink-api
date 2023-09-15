package com.cmd.farmlinkapi.utility
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
class FarmlinkFBInitialize {

    @PostConstruct
    void initialize(){
        try{
            /*
            TODO: Find a way to store the serviceAccountKey json similar to Kubernetes Secrets.
            FixMe: For now, store and direct your copy of the serviceAccountKey.json outside the project directory.
             */
            FileInputStream serviceAccount =
                    new FileInputStream("")


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            println(e.getMessage())
        }
    }
}

package com.cmd.farmlinkapi

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
class FarmlinkApiApplication {

	static void main(String[] args) {
		SpringApplication.run(FarmlinkApiApplication, args)
	}
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

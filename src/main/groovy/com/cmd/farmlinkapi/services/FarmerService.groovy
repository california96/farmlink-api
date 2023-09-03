package com.cmd.farmlinkapi.services

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.Query
import com.google.cloud.firestore.QuerySnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.cloud.FirestoreClient
import groovy.json.JsonOutput
import org.springframework.stereotype.Service
import com.cmd.farmlinkapi.models.Farmer
import java.nio.charset.StandardCharsets
import java.util.concurrent.ExecutionException
import com.google.cloud.firestore.Firestore
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class FarmerService {
    static final String COL_NAME="farmers"

    String saveFarmer(Farmer farmer) throws InterruptedException, ExecutionException{
        Firestore dbFireStore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(COL_NAME).document(farmer.getName()).set(farmer)
        JsonOutput.toJson(collectionsApiFuture.get().getUpdateTime().toString())
    }

    Farmer getFarmerbyName(String name) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore()
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name)
        ApiFuture<DocumentSnapshot> future = documentReference.get()

        DocumentSnapshot document = future.get()
        if(document.exists()) {
            Farmer farmer = document.toObject(Farmer.class)
            farmer
        } else{
            //ToDo: deliberate with the team on what the 404 JSON response should look like
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found")
        }
    }
    String updateFarmer(String farmerId, Farmer updatedFarmer) {

        Firestore dbFirestore = FirestoreClient.getFirestore()

        CollectionReference collectionRef = dbFirestore.collection(COL_NAME)
        Query query = collectionRef.whereEqualTo("farmerId", farmerId)

        try {

            QuerySnapshot querySnapshot = query.get().get()

            if (!querySnapshot.isEmpty()) {

                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0)
                DocumentReference docRef = documentSnapshot.getReference()

                ApiFuture<WriteResult> updateResult = docRef.set(updatedFarmer)

                return "Farmer with farmerId " + farmerId + " updated successfully"
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer with farmerId " + farmerId + " not found")
            }
        } catch (InterruptedException | ExecutionException e) {
            JsonOutput.toJson("status": "error", "message": "An internal server error occurred: " + e.getMessage())
        }
    }


     String deleteFarmer(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(URLEncoder.encode(name, StandardCharsets.UTF_8.toString())).delete()
        try{
            writeResult.get()
            def responseMap = [message: "Farmer ${name} deleted!"]
            JsonOutput.toJson(responseMap)
        }catch (InterruptedException e) {
             JsonOutput.toJson("status": "error", "message": "An internal server error occurred: " + e.getMessage())
        }catch (ExecutionException e) {
            Throwable cause = e.getCause()
            JsonOutput.toJson("status": "error", "message": "An internal server error occurred: " + cause.getMessage())

        }
     }


}

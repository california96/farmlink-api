package com.cmd.farmlinkapi.services

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.cloud.FirestoreClient
import groovy.json.JsonOutput
import org.springframework.stereotype.Service
import com.cmd.farmlinkapi.models.Farmer
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
     String updateFarmer(Farmer farmer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(farmer.getName()).set(farmer)
         collectionsApiFuture.get().getUpdateTime().toString()
    }

     String deleteFarmer(Farmer farmer) {
        Firestore dbFirestore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(farmer.getId()).delete()
         "Document with Farmer ID "+id+" deleted"
    }

}

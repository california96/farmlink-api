package com.cmd.farmlinkapi.services

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service
import com.cmd.farmlinkapi.models.Farmer
import java.util.concurrent.ExecutionException
import com.google.cloud.firestore.Firestore;

@Service
class FarmerService {
    static final String COL_NAME="farmers"

    String saveFarmer(Farmer farmer) throws InterruptedException, ExecutionException{
        Firestore dbFireStore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection(COL_NAME).document(farmer.getName()).set(farmer)
        collectionsApiFuture.get().getUpdateTime().toString()
    }

    Farmer getFarmerbyName(String name) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Farmer farmer = null;

        if(document.exists()) {
            farmer = document.toObject(Farmer.class);
        }
        farmer
    }
     String updateFarmer(Farmer farmer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(farmer.getName()).set(farmer)
         collectionsApiFuture.get().getUpdateTime().toString();
    }

     String deleteFarmer(int id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
         "Document with Farmer ID "+id+" deleted"
    }

}

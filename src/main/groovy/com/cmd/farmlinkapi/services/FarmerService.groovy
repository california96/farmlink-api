package com.cmd.farmlinkapi.services

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
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
import java.lang.reflect.Field
import com.google.cloud.firestore.SetOptions

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
     /*String updateFarmer(Farmer farmer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(farmer.getName()).set(farmer)
         collectionsApiFuture.get().getUpdateTime().toString()
    }*/
    String updateFarmer(Farmer farmer, String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore()

        // Retrieve the existing Farmer document
        ApiFuture<Farmer> getFuture = dbFirestore.collection(COL_NAME)
                .document(name)
                .get(Farmer.class);

        Farmer existingFarmer = getFuture.get().toObject(Farmer.class);

        // Update fields that are present in the input farmer object
        Map<String, Object> updateFields = getUpdatedFields(existingFarmer, farmer);

        // Perform the partial update using set() with merge option
        ApiFuture<WriteResult> updateResult = dbFirestore.collection(COL_NAME)
                .document(name)
                .set(updateFields, SetOptions.merge());

        updateResult.get().getUpdateTime().toString();
    }

     String deleteFarmer(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore()
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(URLEncoder.encode(name, StandardCharsets.UTF_8.toString())).delete()
        try{
            writeResult.get()
            def responseMap = [message: "Farmer ${name} deleted!"]
            JsonOutput.toJson(responseMap)
        }catch (InterruptedException e) {
            return JsonOutput.toJson("status": "error", "message": "An internal server error occurred: " + e.getMessage())
        }catch (ExecutionException e) {
            e.printStackTrace();
            Throwable cause = e.getCause()
            JsonOutput.toJson("status": "error", "message": "An internal server error occurred: " + cause.getMessage())

        }
     }
    private Map<String, Object> getUpdatedFields(Farmer existingFarmer, Farmer newFarmer) throws IllegalAccessException {
        Map<String, Object> updatedFields = new HashMap<>();
        Field[] fields = Farmer.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object newValue = field.get(newFarmer);
            if (newValue != null) {
                updatedFields.put(field.getName(), newValue);
            }
        }

        updatedFields
    }

}

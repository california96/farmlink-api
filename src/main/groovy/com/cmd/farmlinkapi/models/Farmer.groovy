package com.cmd.farmlinkapi.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.google.cloud.firestore.annotation.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@IgnoreExtraProperties
class Farmer {
    private int id
    private String name
    private String location
    private String farmerId
    private String contactInfo
    private List<Product> products
    /*
    ToDo: Remember to exclude metaClass, because the compiler seems to complain about treating a List as Array
    Also see if removing @IgnoreExtraProperties will make a difference because Excluding metaClass has to stay
     */
    @Exclude
    MetaClass getMetaClass() {
        this.metaClass
    }
    Farmer(){}

    Farmer(int id, String name, String location, String farmerId, String contactInfo, List<Product> products) {
        this.id = id
        this.name = name
        this.location = location
        this.farmerId = farmerId
        this.contactInfo = contactInfo
        this.products = products
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

     String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

     String getLocation() {
        return location
    }

    void setLocation(String location) {
        this.location = location
    }

     String getFarmerId() {
        return farmerId
    }

    void setFarmerId(String farmerId) {
        this.farmerId = farmerId
    }

     String getContactInfo() {
        return contactInfo
    }

    void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo
    }

     List<Product> getProducts() {
        return products
    }

    void setProducts(List<Product> products) {
        this.products = products

    }
}

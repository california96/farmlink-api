package com.cmd.farmlinkapi.models

import com.google.cloud.firestore.annotation.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Product {
    private int productId
    private String farmerId
    private String name
    private String description
    private double price
    private String image //FIXME: This is image path
    private boolean availability //TODO: would this be boolean or int?
    /*
    ToDo: Remember to exclude metaClass, because the compiler seems to complain about treating a List as Array
     Also see if removing @IgnoreExtraProperties will make a difference because Excluding metaClass has to stay
     */

    @Exclude
     MetaClass getMetaClass() {
        return this.metaClass
    }
    Product(){

    }
    Product(int productId, String farmerId, String name, String description, double price, String image, boolean availability) {
        this.productId = productId
        this.farmerId = farmerId
        this.name = name
        this.description = description
        this.price = price
        this.image = image
        this.availability = availability
    }

    int getProductId() {
        return productId
    }

    void setProductId(int productId) {
        this.productId = productId
    }

    String getFarmerId() {
        return farmerId
    }

    void setFarmerId(String farmerId) {
        this.farmerId = farmerId
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getDescription() {
        return description
    }

    void setDescription(String description) {
        this.description = description
    }

    double getPrice() {
        return price
    }

    void setPrice(double price) {
        this.price = price
    }

    String getImage() {
        return image
    }

    void setImage(String image) {
        this.image = image
    }

    boolean getAvailability() {
        return availability
    }

    void setAvailability(boolean availability) {
        this.availability = availability
    }
}

package com.cmd.farmlinkapi.models

class Farmer {
    private int id
    private String name
    private String location
    private String farmerId
    private String contactInfo
    private ArrayList<Product> products

    Farmer(int id, String name, String location, String farmerId, String contactInfo, ArrayList<Product> products) {
        this.id = id
        this.name = name
        this.location = location
        this.farmerId = farmerId
        this.contactInfo = contactInfo
        this.products = products
    }

    private int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    private String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    private String getLocation() {
        return location
    }

    void setLocation(String location) {
        this.location = location
    }

    private String getFarmerId() {
        return farmerId
    }

    void setFarmerId(String farmerId) {
        this.farmerId = farmerId
    }

    private String getContactInfo() {
        return contactInfo
    }

    void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo
    }

    private ArrayList<Product> getProducts() {
        return products
    }

    void setProducts(ArrayList<Product> products) {
        this.products = products
    }
}

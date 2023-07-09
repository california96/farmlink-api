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
}

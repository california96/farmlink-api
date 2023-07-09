package com.cmd.farmlinkapi.models

class Product {
    private int productId
    private int farmerId
    private String name
    private String description
    private double price
    private String image //FIXME: This is image path
    private boolean availability //TODO: would this be boolean or int?

    Product(int productId, int farmerId, String name, String description, double price, String image, boolean availability) {
        this.productId = productId
        this.farmerId = farmerId
        this.name = name
        this.description = description
        this.price = price
        this.image = image
        this.availability = availability
    }

}

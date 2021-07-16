package model;

import lombok.Data;

@Data
public class Product {
    private String productName;
    private String brand;
    private String colors;
    private String price;
    private String articleID;

    public Product(String productName, String brand, String colors, String price, String articleID) {
        this.productName = productName;
        this.brand = brand;
        this.colors = colors;
        this.price = price;
        this.articleID = articleID;
    }
}

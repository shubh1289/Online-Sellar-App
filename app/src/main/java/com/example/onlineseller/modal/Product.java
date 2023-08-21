package com.example.onlineseller.modal;

public class Product {
    int image;
    String productname,productprice;

    public Product() {
    }
    public Product(int image, String productname, String productprice) {
        this.image = image;
        this.productname = productname;
        this.productprice = productprice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}

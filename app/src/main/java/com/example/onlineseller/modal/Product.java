package com.example.onlineseller.modal;

public class Product {
    String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Product(String pid, String productname, String productprice, String cat, String image) {
        this.pid = pid;
        this.productname = productname;
        this.productprice = productprice;
        this.cat = cat;
        this.image = image;
    }

    String productname;
    String productprice;
    String cat;
    String image;

    public Product() {
    }

    public Product(String image) {
        this.image = image;
    }

    public Product(String productname, String productprice, String cat, String image) {
        this.productname = productname;
        this.productprice = productprice;
        this.cat = cat;
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

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

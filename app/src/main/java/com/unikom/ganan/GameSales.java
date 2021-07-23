package com.unikom.ganan;

public class GameSales {
    String Image;
    String title;
    float priceSales, priceOri;
    int discount;
    String storeID;

    public GameSales(String image, String title, float priceSales, float priceOri, String storeID) {
        Image = image;
        this.title = title;
        this.priceSales = priceSales;
        this.priceOri = priceOri;
        this.storeID = storeID;
        discount = Math.round((priceOri - priceSales) / priceOri * 100);
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(float priceSales) {
        this.priceSales = priceSales;
    }

    public float getPriceOri() {
        return priceOri;
    }

    public void setPriceOri(float priceOri) {
        this.priceOri = priceOri;
    }

    public int getDiscount() { return discount; }

    public String getStoreID() { return storeID; }
}

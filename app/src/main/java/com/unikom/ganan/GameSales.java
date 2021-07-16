package com.unikom.ganan;

public class GameSales {
    int Image;
    String title;
    float priceSales, priceOri;
    float discount;

    public GameSales(int image, String title, float priceSales, float priceOri) {
        Image = image;
        this.title = title;
        this.priceSales = priceSales;
        this.priceOri = priceOri;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
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
}

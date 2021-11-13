package org.byby.model;

public class PriceItem {
    private double price;
    private double value;

    public PriceItem(double price, double value){
        this.setPrice(price);
        this.setValue(value);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

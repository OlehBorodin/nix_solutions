package com.changes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Product  {
    protected long id;
    protected boolean available;
    protected String title;
    protected double price;

    public Product(long id, boolean available, String title, double price) {
        this.id = id;
        this.available = available;
        this.title = title;
        this.price = price;
    }

    protected Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
package com.changes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBundle extends NotifiableProduct {
    protected int amount;

    public ProductBundle() {
        super();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductBundle{" +
                "amount=" + amount +
                ", channel='" + channel + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
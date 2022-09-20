package com.changes.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class NotifiableProduct extends Product {
    protected String channel;
    protected String email;


    public NotifiableProduct(long id, boolean available, String title, double price, String channel, String email) {
        super(id, available, title, price);
        this.channel = channel;
        this.email = email;
    }
    public NotifiableProduct() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = generateAddressForNotification();
    }

    private static final Random RANDOM = new Random();

    public String generateAddressForNotification() {
        int size = RANDOM.nextInt(4, 15);
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString + "@gmail.com";
    }

    @Override
    public String toString() {
        return "NotifiableProduct{" +
                "channel='" + channel + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
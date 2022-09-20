package com.changes;

import com.changes.repository.ProductRepository;
import com.changes.service.ProductService;
import com.changes.utils.ProductUtils;

public class Main {
    private static final ProductService PRODUCT_SERVICE = new ProductService(new ProductRepository());
    private static final ProductUtils PRODUCT_UTILS = new ProductUtils();
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            PRODUCT_SERVICE.save(PRODUCT_SERVICE.generateRandomProduct());
        }
        System.out.println(PRODUCT_SERVICE.getAll());
        System.out.println("Notifications sent: "
                + PRODUCT_UTILS.filterNotifiableProductsAndSendNotifications(PRODUCT_SERVICE.getAll()));
    }
}

package com.changes.utils;

import com.changes.model.NotifiableProduct;
import com.changes.model.Product;
import com.changes.model.ProductBundle;
import com.changes.repository.ProductRepository;

import java.util.List;

public class ProductUtils {
    private ProductRepository repository = new ProductRepository();

    public void saveNotifiableProduct(NotifiableProduct product) {
        repository.save(product);
    }

    public void saveProductBundle(ProductBundle product) {
        repository.save(product);
    }

        public long filterNotifiableProductsAndSendNotifications(List<Product> products) {
            return products.stream()
                    .filter(it -> it.getClass().equals(NotifiableProduct.class))
                    .count();
        }

    public List<Product> getAll() {
        return repository.getAll();
    }


}

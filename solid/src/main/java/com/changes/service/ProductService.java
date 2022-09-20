package com.changes.service;

import com.changes.model.NotifiableProduct;
import com.changes.model.Product;
import com.changes.model.ProductBundle;
import com.changes.repository.ProductRepository;

import java.util.List;
import java.util.Random;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public Product generateRandomProduct() {
        Random random = new Random();
        ProductBundle productBundle = new ProductBundle();
        NotifiableProduct notifiableProduct = new NotifiableProduct();
        if (random.nextBoolean()) {
            productBundle.setId(random.nextLong());
            productBundle.setAvailable(random.nextBoolean());
            productBundle.setTitle(random.nextFloat() + "" + random.nextDouble());
            productBundle.setPrice(random.nextDouble());
            productBundle.setChannel(random.nextBoolean() + "" + random.nextDouble());
            productBundle.setEmail("");
            productBundle.setAmount(random.nextInt(15));
            return productBundle;
        } else {
            notifiableProduct.setId(random.nextLong());
            notifiableProduct.setAvailable(random.nextBoolean());
            notifiableProduct.setTitle(random.nextFloat() + "" + random.nextDouble());
            notifiableProduct.setPrice(random.nextDouble());
            notifiableProduct.setChannel(random.nextBoolean() + "" + random.nextDouble());
            notifiableProduct.setEmail("");
            return notifiableProduct;
        }
    }
}

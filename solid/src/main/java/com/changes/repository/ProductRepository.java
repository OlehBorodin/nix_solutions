package com.changes.repository;

import com.changes.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> storage = new HashMap<>();

    public Product save(Product product) {
        return storage.put(product.getId(), product);
    }

    public List<Product> getAll() {
        return new ArrayList<>(storage.values());
    }
}

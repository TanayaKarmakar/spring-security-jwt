package com.app.jwt.services;

import com.app.jwt.models.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAll();
}

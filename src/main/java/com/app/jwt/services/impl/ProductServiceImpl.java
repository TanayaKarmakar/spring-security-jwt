package com.app.jwt.services.impl;

import com.app.jwt.exception.NotFoundException;
import com.app.jwt.models.entity.Product;
import com.app.jwt.repository.ProductRepository;
import com.app.jwt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with ID " + productId + " " +
                        "doesn't exist in the system"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

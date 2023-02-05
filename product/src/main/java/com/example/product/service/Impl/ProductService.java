package com.example.product.service.Impl;

import com.example.product.model.Category;
import com.example.product.model.Product;
import com.example.product.repository.IProductRepository;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
      productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
       productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }
}

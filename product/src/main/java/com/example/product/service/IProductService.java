package com.example.product.service;

import com.example.product.model.Category;
import com.example.product.model.Product;

public interface IProductService extends IGerneService<Product, Long>{
    Iterable<Product> findAllCategory(Category category);
    Iterable<Product> findAllByNameContaining(String name);
}

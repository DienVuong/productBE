package com.example.product.service;

import com.example.product.model.Product;

import java.util.Optional;

public interface IGerneService<E, K> {
     Iterable<E> findAll();

    Optional<E> findById(Long id);

    void save(E e);

    void remove(Long id);
}

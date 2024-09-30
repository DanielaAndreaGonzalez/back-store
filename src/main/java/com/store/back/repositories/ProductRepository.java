package com.store.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.back.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

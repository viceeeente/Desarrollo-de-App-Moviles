package com.example.lvlupbackend.product.repository;

import com.example.lvlupbackend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}


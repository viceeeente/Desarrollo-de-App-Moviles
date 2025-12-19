package com.example.lvlupbackend.repository;

import com.example.lvlupbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}


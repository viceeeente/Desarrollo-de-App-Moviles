package com.example.lvlupbackend.product.service;

import com.example.lvlupbackend.product.model.Product;
import com.example.lvlupbackend.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService (ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }


}

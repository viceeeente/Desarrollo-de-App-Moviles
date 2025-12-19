package com.example.lvlupbackend.repository;

import com.example.lvlupbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}

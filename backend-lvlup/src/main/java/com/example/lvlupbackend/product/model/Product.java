package com.example.lvlupbackend.product.model;

import com.example.lvlupbackend.categories.model.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private String img;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

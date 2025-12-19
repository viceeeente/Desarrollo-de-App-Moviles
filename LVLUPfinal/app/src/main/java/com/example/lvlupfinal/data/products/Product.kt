package com.example.lvlupfinal.data.products

import com.example.lvlupfinal.data.categories.Category

data class Product(
    val id: Int?,
    val name: String,
    val description: String,
    val price: Int,
    val stock: Int,
    val img: String,          // ✅ ahora String
    val category: Category    // ✅ objeto Category
)
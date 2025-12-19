package com.example.lvlupfinal.data.products

import com.example.lvlupfinal.data.RetrofitInstance

class ProductRepository {

    suspend fun getProducts(): List<Product> {
        // ✅ llamada directa al backend
        return RetrofitInstance.productApi.getProducts()
    }

    suspend fun getByCategory(categoria: String): List<Product> {
        return getProducts().filter { it.category.name.equals(categoria, ignoreCase = true) }
    }
}
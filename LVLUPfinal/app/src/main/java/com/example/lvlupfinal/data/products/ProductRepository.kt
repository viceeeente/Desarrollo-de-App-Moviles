package com.example.lvlupfinal.data.products

import com.example.lvlupfinal.data.RetrofitInstance

class ProductRepository {

    suspend fun  getProducts(): List<Product> {
        return RetrofitInstance.productApi.getProducts()
    }


}
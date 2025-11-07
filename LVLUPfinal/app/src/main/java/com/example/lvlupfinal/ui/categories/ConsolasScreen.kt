package com.example.lvlupfinal.ui.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.products.ProductRepository
import com.example.lvlupfinal.ui.products.ProductCard
import com.example.lvlupfinal.products.Product

@Composable
fun ConsolasScreen(modifier: Modifier = Modifier) {
    val productosConsolas = remember {
        ProductRepository().getByCategory("Consola")
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Consolas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        productosConsolas.forEach { producto ->
            ProductCard(producto)
        }
    }
}
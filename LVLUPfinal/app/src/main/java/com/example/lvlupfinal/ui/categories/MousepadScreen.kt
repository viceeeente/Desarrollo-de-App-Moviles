package com.example.lvlupfinal.ui.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.products.ProductRepository
import com.example.lvlupfinal.ui.products.ProductCard

@Composable
fun MousepadScreen(modifier: Modifier = Modifier) {
    val productos = remember { ProductRepository().getByCategory("Mousepad") }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Mousepad", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        productos.forEach { ProductCard(it) }
    }
}
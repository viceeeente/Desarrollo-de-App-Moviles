package com.example.lvlupfinal.ui.carrito

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.data.products.Product

@Composable
fun CarritoScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel(),
    onBackToHome: () -> Unit
) {
    val productosEnCarrito = viewModel.carrito

    Column(modifier = modifier.padding(16.dp)) {
        Text("Carrito", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBackToHome) {
            Text("Volver al Home")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (productosEnCarrito.isEmpty()) {
            Text("Tu carrito está vacío.")
        } else {
            productosEnCarrito.forEach { producto ->
                ProductoEnCarrito(producto)
            }
        }
    }
}

@Composable
fun ProductoEnCarrito(producto: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(producto.name, style = MaterialTheme.typography.titleMedium)
            Text("Precio: \$${producto.price}", style = MaterialTheme.typography.bodyMedium)
            if (producto.description.isNotBlank()) {
                Text(producto.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
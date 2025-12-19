package com.example.lvlupfinal.ui.shoppingcart

import androidx.compose.foundation.layout.*          // Column, Spacer, padding, height, fillMaxWidth
import androidx.compose.material3.*                 // MaterialTheme, Text, Button, Card, CardDefaults
import androidx.compose.runtime.*                   // @Composable, remember, mutableStateOf, collectAsState, getValue, setValue
import androidx.compose.ui.Modifier                 // Modifier
import androidx.compose.ui.unit.dp                  // dp para padding y height
import androidx.lifecycle.viewmodel.compose.viewModel // viewModel() para obtener SharedViewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel // tu ViewModel compartido
import com.example.lvlupfinal.data.products.Product // modelo Product

@Composable
fun CarritoScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel(),
    onBackToHome: () -> Unit
) {
    val productosEnCarrito by viewModel.carrito.collectAsState()

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
            productosEnCarrito.forEach { producto: Product ->
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
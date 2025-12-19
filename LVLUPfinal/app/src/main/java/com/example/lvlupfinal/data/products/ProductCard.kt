package com.example.lvlupfinal.data.products
import coil.compose.AsyncImage

import androidx.compose.foundation.layout.*          // Column, Row, Spacer, Modifier.size, Modifier.padding, etc.
import androidx.compose.material3.*                // Card, CardDefaults, Text, Button, MaterialTheme
import androidx.compose.runtime.Composable         // @Composable annotation
import androidx.compose.ui.Modifier                // Modifier
import androidx.compose.ui.unit.dp                 // dp for spacing
import androidx.compose.ui.unit.sp                 // sp for font size
import coil.compose.AsyncImage                     // ✅ para cargar imágenes desde URL
import com.example.lvlupfinal.data.products.Product // tu modelo Product

@Composable
fun ProductCard(
    producto: Product,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                AsyncImage(
                    model = producto.img,              // ✅ ahora String (URL)
                    contentDescription = producto.name,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(producto.name, style = MaterialTheme.typography.titleMedium)
                    Text("Precio: \$${producto.price}", fontSize = 14.sp)
                    if (producto.description.isNotBlank()) {
                        Text(producto.description, fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onAddToCart) {
                Text("Agregar al carrito")
            }
        }
    }
}
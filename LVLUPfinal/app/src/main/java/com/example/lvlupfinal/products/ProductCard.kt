package com.example.lvlupfinal.ui.products

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lvlupfinal.products.Product

@Composable
fun ProductCard(producto: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(
                painter = painterResource(id = producto.img),
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
    }
}
package com.example.lvlupfinal.ui.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.data.products.ProductRepository
import com.example.lvlupfinal.ui.products.ProductCard
import androidx.compose.runtime.LaunchedEffect
import com.example.lvlupfinal.viewmodel.SharedViewModel

@Composable
fun ConsolasScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
    val productos = remember { ProductRepository().getByCategory("Consola") }
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var mensajePendiente by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(mensajePendiente) {
        mensajePendiente?.let {
            snackbarHostState.showSnackbar(it)
            mensajePendiente = null
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Consolas", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            productos.forEach { producto ->
                ProductCard(producto) {
                    if (isLoggedIn) {
                        viewModel.agregarAlCarrito(producto)
                        mensajePendiente = "Producto agregado al carrito"
                    } else {
                        mensajePendiente = "Debes iniciar sesión para agregar al carrito"
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
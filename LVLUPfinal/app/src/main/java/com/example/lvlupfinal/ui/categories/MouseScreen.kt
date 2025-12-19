package com.example.lvlupfinal.ui.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.lvlupfinal.data.products.ProductCard
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.data.products.Product

@Composable
fun MouseScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
    val productosMouse by viewModel.productosMouse.observeAsState(initial = emptyList<Product>())
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
            Text("Mouse", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            productosMouse.forEach { producto ->
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
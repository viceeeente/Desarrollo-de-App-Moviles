package com.example.lvlupfinal.ui.shoppingcart
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.lvlupfinal.model.Screen

/**
 * Composable que muestra la pantalla de detalle de un item seleccionado.
 *
 * @param modifier Modifier para personalizar el layout desde el padre
 * @param viewModel SharedViewModel que contiene el estado de la app
 * @param onBack Lambda que se ejecuta al presionar el botón de regresar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCart(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    val selectedItem by viewModel.selectedItemId.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Detalle del item: ${selectedItem ?: "Ninguno"}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )
}
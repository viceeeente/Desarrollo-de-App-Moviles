package com.example.lvlupfinal.ui.detail
// Importamos librerías necesarias de Compose y Material3
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
/**
 * Composable que muestra la pantalla de detalle de un item seleccionado.
 *
 * @param modifier Modifier para personalizar el layout desde el padre
 * @param viewModel SharedViewModel que contiene el estado de la app
 * @param onBack Lambda que se ejecuta al presionar el botón de regresar
 */
@OptIn(ExperimentalMaterial3Api::class) // Para usar TopAppBar de Material3 experimental
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
// Observamos el item seleccionado desde el ViewModel
    val selectedItem by viewModel.selectedItemId.collectAsState()
// Scaffold nos permite tener TopBar, BottomBar y contenido de manera estructurada
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Detalle") }, // Título de la pantalla
                navigationIcon = {
// Botón para regresar
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, // Icono de flecha
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        content = { padding ->
// Contenido principal de la pantalla
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding) // Padding del Scaffold
                    .padding(16.dp), // Padding interno adicional
                verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre elementos
            ) {
// Tarjeta que muestra el detalle del item
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium, // Bordes redondeados
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Detalle del item: ${selectedItem ?: "Ninguno"}", // Texto dinámico
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    )
}
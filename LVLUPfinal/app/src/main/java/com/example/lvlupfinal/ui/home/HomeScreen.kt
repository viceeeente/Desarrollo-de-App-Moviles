package com.example.lvlupfinal.ui.home

// Importamos librerías necesarias de Compose y Material3
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
/**
 * Composable que muestra la pantalla principal (Home) con una lista de items.
 *
 * @param modifier Modifier para personalizar el layout desde el padre
 * @param viewModel SharedViewModel que contiene el estado de la app
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
// Observamos el estado de Home desde el ViewModel
    val state = viewModel.homeState.collectAsState().value
// LazyColumn nos permite mostrar una lista con scroll eficiente
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp), // Padding general de la lista
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre cada item
    ) {
// Iteramos por cada item del estado
        items(state.items) { id ->
// Cada item se representa como una Card clicable
            Card(
                modifier = Modifier
                    .fillMaxWidth() // La tarjeta ocupa todo el ancho
                    .clickable { viewModel.onItemClick(id) }, // Al hacer click llamamos al ViewModel
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Elevación para sombra
                shape = MaterialTheme.shapes.medium // Bordes redondeados
            ) {
// Contenido de la Card con padding interno
                Box(modifier = Modifier.padding(16.dp)) {
// Mostramos el texto del item
                    Text(
                        text = "Item $id",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
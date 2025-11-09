package com.example.lvlupfinal.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.LatLng

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
    val categorias = listOf("Consolas", "Juegos", "Mouse", "Mousepad", "PC", "Poleras", "Silla Gamer")
    val melipilla = LatLng(-33.6745, -71.2154)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(melipilla, 14f)
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Bienvenido a LevelUp",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        CategoryCarousel(categories = categorias) { selected ->
            viewModel.onBottonNavSelected(selected.lowercase())
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Nuestra ubicación",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = melipilla),
                title = "Melipilla",
                snippet = "Aquí estamos"
            )
        }
    }
}
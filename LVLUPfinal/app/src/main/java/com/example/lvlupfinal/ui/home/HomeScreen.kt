package com.example.lvlupfinal.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
    val currentUser by viewModel.currentUser.collectAsState()
    val categories = listOf("Consolas", "Juegos", "Mouse", "Mousepad", "PC", "Poleras", "Silla Gamer")

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = if (currentUser != null) "Hola, ${currentUser?.name}" else "Bienvenido a LvlUpGamer",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleMedium
        )

        CategoryCarousel(categories = categories) { selectedCategory ->
            val route = when (selectedCategory.lowercase()) {
                "consolas" -> "consolas"
                "juegos" -> "juegos"
                "mouse" -> "mouse"
                "mousepad" -> "mousepad"
                "pc" -> "pc"
                "poleras" -> "poleras"
                "silla gamer" -> "silla"
                else -> "home"
            }
            viewModel.onBottonNavSelected(route)
        }
    }
}
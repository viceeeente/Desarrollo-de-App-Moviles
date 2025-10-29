package com.example.lvlupfinal.ui.common

import androidx.compose.runtime.Composable
import com.example.lvlupfinal.model.Screen
import androidx.compose.material3.*
/**
 * Composable que crea la barra de navegación inferior (BottomNavigation).
 *
 * @param items Lista de pantallas (Screen) que queremos mostrar en la barra
 * @param currentScreen Pantalla actualmente seleccionada
 * @param onItemSelected Lambda que se ejecuta cuando se selecciona un item
 */
@Composable
fun BottonNavBar(
    items: List<Screen>, // Las pantallas que queremos mostrar como botones
    currentScreen: Screen, // La pantalla actualmente activa (resaltada)
    onItemSelected: (Screen) -> Unit // Callback al hacer click en un item
) {
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = screen == currentScreen,
                onClick = { onItemSelected(screen) }
            )
        }
    }
}
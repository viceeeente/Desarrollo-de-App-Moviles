package com.example.lvlupfinal.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.lvlupfinal.model.Screen
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
// NavigationBar es el componente de Material3 para barra inferior
    NavigationBar {
// Iteramos por cada pantalla y creamos un NavigationBarItem
        items.forEach { screen ->
            NavigationBarItem(
// Icono de la pantalla
                icon = { Icon(screen.icon, contentDescription = screen.title) },
// Etiqueta de la pantalla
                label = { Text(screen.title) },
// Determina si este item está seleccionado
                selected = screen == currentScreen,
// Acción al hacer click: llamamos al callback pasando la pantalla
                onClick = { onItemSelected(screen) }
            )
        }
    }
}
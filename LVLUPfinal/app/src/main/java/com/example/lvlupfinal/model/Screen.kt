package com.example.lvlupfinal.model
// Importamos iconos de Material Design y el tipo ImageVector para representarlos en Compose
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
/**
 * Clase sellada que representa todas las pantallas de la aplicación.
 * Usamos "sealed class" para que solo podamos definir aquí las pantallas válidas.
 * Cada pantalla tiene:
 * - route: identificador único para navegación
 * - title: título que se mostrará en la UI
 * - icon: icono que se mostrará en la BottomNavigation o menú
 */
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    // Pantalla principal/Home
    object Home : Screen(
        route = "home", // Identificador único de la pantalla
        title = "Home", // Título visible en la UI
        icon = Icons.Default.Home // Icono que se mostrará en la barra de navegación
    )
    // Pantalla de detalles/List
    object Detail : Screen(
        route = "detail", // Identificador único
        title = "Detalle", // Título que se muestra
        icon = Icons.AutoMirrored.Filled.List // Icono de la barra inferior
    )
}
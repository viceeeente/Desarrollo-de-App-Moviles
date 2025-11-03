package com.example.lvlupfinal.model
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
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
    object ShoppingCart : Screen(
        route = "shoppingcart", // Identificador único
        title = "Carrito", // Título que se muestra
        icon = Icons.Filled.ShoppingCart // Icono de la barra inferior
    )


    object Register : Screen(
        route = "register",
        title = "Registro",
        icon = Icons.Filled.AccountBox
    )
    object MoreOptions : Screen(
        route = "moreoptions",
        title = "Más",
        icon = Icons.Filled.Menu
    )


}
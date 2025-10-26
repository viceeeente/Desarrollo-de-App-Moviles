package com.example.lvlupfinal.ui.navigation
// Importamos las librerías necesarias de Compose y nuestras pantallas/componentes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen;
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.ui.detail.DetailScreen
/**
 * Composable principal que gestiona la navegación entre pantallas.
 *
 * Usa un ViewModel compartido (SharedViewModel) para:
 * - Controlar qué pantalla está activa.
 * - Pasar datos entre pantallas.
 */
@Composable
fun AppNavigation(viewModel: SharedViewModel = viewModel()) {
// Observamos la pantalla actual desde el ViewModel
    val currentScreenRoute by viewModel.currentScreen.collectAsState()
// Scaffold estructura la UI: nos da BottomBar, TopBar y el contenido
    Scaffold(
        bottomBar = {
// Barra de navegación inferior
            BottonNavBar(
                items = listOf(Screen.Home, Screen.Detail), // Pantallas que se muestran en el menú
                currentScreen = if (currentScreenRoute == "home") Screen.Home else Screen.Detail,
                onItemSelected = { screen ->
// Cuando el usuario selecciona una pantalla, actualizamos el estado
                    viewModel.onBottonNavSelected(screen.route)
                }
            )
        }
    ) { padding ->
// Dependiendo de la pantalla actual, mostramos el contenido correspondiente
        when (currentScreenRoute) {
// Pantalla principal (Home)
            "home" -> HomeScreen(
                modifier = Modifier.padding(padding), // Respetamos el padding del Scaffold
                viewModel = viewModel
            )
// Pantalla de detalle
            "detail" -> DetailScreen(
                modifier = Modifier.padding(padding),
                viewModel = viewModel
            ) {
// Acción de volver atrás (por ejemplo, desde el botón en el TopBar de Detail)
                viewModel.onBottonNavSelected("home")
            }
        }
    }
}
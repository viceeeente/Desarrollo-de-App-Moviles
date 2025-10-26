package com.example.lvlupfinal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lvlupfinal.ui.navigation.AppNavigation
// MainActivity es el punto de entrada de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Activa el modo Edge-to-Edge (contenido puede ocupar toda la pantalla,
// debajo de la barra de estado y de navegación)
        enableEdgeToEdge()
// setContent define la UI de esta Activity usando Jetpack Compose
        setContent {
// Llamamos a nuestro sistema de navegación,
// que decide qué pantalla mostrar (Home o Detail)
            AppNavigation()
        }
    }
}
package com.example.lvlupfinal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen
import com.example.lvlupfinal.ui.detail.DetailScreen
import com.example.lvlupfinal.ui.users.UserScreen
import com.example.lvlupfinal.model.Screen

@Composable
fun AppNavigation(viewModel: SharedViewModel = viewModel()) {
    val currentScreenRoute by viewModel.currentScreen.collectAsState()

    Scaffold(
        bottomBar = {
            BottonNavBar(
                items = listOf(Screen.Home, Screen.Detail, Screen.Users), 
                currentScreen = when (currentScreenRoute) {
                    "home" -> Screen.Home
                    "detail" -> Screen.Detail
                    "users" -> Screen.Users
                    else -> Screen.Home
                },
                onItemSelected = { screen ->
                    viewModel.onBottonNavSelected(screen.route)
                }
            )
        }
    ) { padding ->
        when (currentScreenRoute) {
            "home" -> HomeScreen(
                modifier = Modifier.padding(padding),
                viewModel = viewModel
            )
            "detail" -> DetailScreen(
                modifier = Modifier.padding(padding),
                viewModel = viewModel
            ) {
                viewModel.onBottonNavSelected("home")
            }
            "users" -> UserScreen(
                modifier = Modifier.padding(padding),
            )
        }
    }
}
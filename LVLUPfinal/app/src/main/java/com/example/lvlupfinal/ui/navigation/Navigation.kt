package com.example.lvlupfinal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen
import com.example.lvlupfinal.ui.detail.DetailScreen
import com.example.lvlupfinal.ui.users.UserScreen
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.ui.users.RegisterScreen
import com.example.lvlupfinal.ui.users.ResumeScreen
import com.example.lvlupfinal.viewmodel.UserViewModel

@Composable
fun AppNavigation(
    navController: NavController,
    viewModel: SharedViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val currentScreenRoute by viewModel.currentScreen.collectAsState()

    Scaffold(
        bottomBar = {
            BottonNavBar(
                items = listOf(Screen.Home, Screen.Detail, Screen.Users, Screen.Resume, Screen.Register),
                currentScreen = when (currentScreenRoute) {
                    "home" -> Screen.Home
                    "detail" -> Screen.Detail
                    "users" -> Screen.Users
                    "resume" -> Screen.Resume
                    "register" -> Screen.Register
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
            "resume" -> ResumeScreen(
                modifier = Modifier.padding(padding),
                navController = navController,
                viewModel = userViewModel
            )
            "register" -> RegisterScreen(
                modifier = Modifier.padding(padding),
                sharedViewModel = viewModel,
                viewModel = userViewModel
            )
        }
    }
}
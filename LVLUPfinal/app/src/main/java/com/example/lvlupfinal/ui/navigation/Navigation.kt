package com.example.lvlupfinal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen
import com.example.lvlupfinal.ui.detail.DetailScreen
import com.example.lvlupfinal.ui.users.UserScreen
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.ui.users.RegisterScreen
import com.example.lvlupfinal.ui.users.ResumeScreen
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel

@Composable
fun AppNavigation(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel
) {
    val currentScreenRoute by sharedViewModel.currentScreen.collectAsState()
    val isLoggedIn by sharedViewModel.isLoggedIn.collectAsState()

    val allItems = listOf(
        Screen.Home,
        Screen.Register,
        Screen.Resume,
        Screen.Detail,
        Screen.Users
    )

    val visibleItems = if (isLoggedIn) {
        allItems.filterNot { it.route == Screen.Register.route || it.route == Screen.Resume.route || it.route == Screen.Users.route }
    } else {
        allItems
    }

    val currentScreen = visibleItems.find { it.route == currentScreenRoute }?: Screen.Home

    Scaffold(
        bottomBar = {
            BottonNavBar(
                items = visibleItems,
                currentScreen = visibleItems.find { it.route == currentScreenRoute } ?: Screen.Home,
                onItemSelected = { screen ->
                    sharedViewModel.onBottonNavSelected(screen.route)
                }
            )
        }
    ) { contentPadding ->
        when (currentScreenRoute) {
            Screen.Home.route -> HomeScreen(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            )
            Screen.Detail.route -> DetailScreen(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            ) {
                sharedViewModel.onBottonNavSelected(Screen.Home.route)
            }
            Screen.Users.route -> UserScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                viewModel = userViewModel
            )
            Screen.Resume.route -> ResumeScreen(
                modifier = Modifier.padding(contentPadding),
                navController = navController,
                viewModel = userViewModel
            )
            Screen.Register.route -> RegisterScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                viewModel = userViewModel
            )
            else -> HomeScreen(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            )
        }
    }
}
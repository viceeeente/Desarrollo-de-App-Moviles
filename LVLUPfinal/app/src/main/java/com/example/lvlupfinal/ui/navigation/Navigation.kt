package com.example.lvlupfinal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.more.MoreOptions
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import  com.example.lvlupfinal.ui.shoppingcart.ShoppingCart
import com.example.lvlupfinal.ui.users.Register


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
        Screen.ShoppingCart,
        Screen.MoreOptions
    )

    Scaffold(
        bottomBar = {
            BottonNavBar(
                items = allItems,
                currentScreen = allItems.find { it.route == currentScreenRoute } ?: Screen.Home,
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
            Screen.ShoppingCart.route -> ShoppingCart(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            ) {
                sharedViewModel.onBottonNavSelected(Screen.Home.route)
            }
            Screen.MoreOptions.route -> MoreOptions(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            )
            Screen.Register.route -> Register(
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
package com.example.lvlupfinal.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lvlupfinal.ui.common.BottonNavBar
import com.example.lvlupfinal.ui.home.HomeScreen
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.ui.more.AboutScreen
import com.example.lvlupfinal.ui.more.MoreOptions
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import com.example.lvlupfinal.ui.shoppingcart.ShoppingCart
import com.example.lvlupfinal.ui.users.Register
import com.example.lvlupfinal.ui.more.account.AccountScreen
import com.example.lvlupfinal.ui.more.account.ChangePasswordScreen
import com.example.lvlupfinal.ui.more.account.EditProfileScreen
import com.example.lvlupfinal.ui.users.LoginScreen

@Composable
fun AppNavigation(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel
) {
    val currentScreenRoute by sharedViewModel.currentScreen.collectAsState()
    val isLoggedIn by sharedViewModel.isLoggedIn.collectAsState()

    val loggedOutItems = listOf(Screen.Home, Screen.MoreOptions)
    val loggedInItems = listOf(Screen.Home, Screen.ShoppingCart, Screen.MoreOptions)
    val visibleItems = if (isLoggedIn) loggedInItems else loggedOutItems

    Scaffold(
        bottomBar = {
            if (currentScreenRoute != Screen.Login.route && currentScreenRoute != Screen.Register.route) {
                BottonNavBar(
                    items = visibleItems,
                    currentScreen = visibleItems.find { it.route == currentScreenRoute } ?: visibleItems.first(),
                    onItemSelected = { screen ->
                        val protected = setOf(Screen.ShoppingCart.route, Screen.Account.route)
                        if (!isLoggedIn && screen.route in protected) {
                            sharedViewModel.onBottonNavSelected(Screen.Login.route)
                        } else {
                            sharedViewModel.onBottonNavSelected(screen.route)
                        }
                    }
                )
            }
        }
    ) { contentPadding ->
        val protectedRoutes = setOf(
            Screen.ShoppingCart.route, Screen.Account.route,
            Screen.EditProfile.route, Screen.ChangePassword.route
        )
        val effectiveRoute = if (!isLoggedIn && currentScreenRoute in protectedRoutes) {
            Screen.Login.route
        } else currentScreenRoute

        when (effectiveRoute) {
            Screen.Home.route -> HomeScreen(modifier = Modifier.padding(contentPadding), viewModel = sharedViewModel)

            Screen.ShoppingCart.route -> ShoppingCart(
                modifier = Modifier.padding(contentPadding),
                viewModel = sharedViewModel
            ) { sharedViewModel.onBottonNavSelected(Screen.Home.route) }

            Screen.MoreOptions.route -> MoreOptions(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel,
                onNavigate = { route -> sharedViewModel.onBottonNavSelected(route) }
            )

            Screen.Register.route -> Register(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                viewModel = userViewModel
            )

            Screen.Login.route -> LoginScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel,
                onNavigate = { route -> sharedViewModel.onBottonNavSelected(route) }
            )

            Screen.Account.route -> AccountScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                onEdit = { sharedViewModel.onBottonNavSelected(Screen.EditProfile.route) }
            )

            Screen.EditProfile.route -> EditProfileScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel,
                onDone = { sharedViewModel.onBottonNavSelected(Screen.Account.route) }
            )

            Screen.ChangePassword.route -> ChangePasswordScreen(
                modifier = Modifier.padding(contentPadding),
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel,
                onDone = { sharedViewModel.onBottonNavSelected(Screen.Account.route) }
            )

            Screen.About.route -> AboutScreen(modifier = Modifier.padding(contentPadding))

            else -> HomeScreen(modifier = Modifier.padding(contentPadding), viewModel = sharedViewModel)
        }
    }
}
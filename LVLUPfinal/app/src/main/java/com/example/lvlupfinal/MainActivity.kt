package com.example.lvlupfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.lvlupfinal.data.users.AppDatabase
import com.example.lvlupfinal.data.users.UserRepository
import com.example.lvlupfinal.ui.navigation.AppNavigation
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import com.example.lvlupfinal.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getInstance(applicationContext)
        val userDao = database.userDao()
        val userRepository = UserRepository(userDao)
        val userViewModelFactory = UserViewModelFactory(userRepository)
        val userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            AppNavigation(
                navController = navController,
                sharedViewModel = sharedViewModel,
                userViewModel = userViewModel
            )
        }
    }
}
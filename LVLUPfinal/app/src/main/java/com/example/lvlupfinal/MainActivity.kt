package com.example.lvlupfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.lvlupfinal.data.local.AppDatabase
import com.example.lvlupfinal.data.local.UserDao
import com.example.lvlupfinal.data.repository.UserRepository
import com.example.lvlupfinal.ui.navigation.AppNavigation
import com.example.lvlupfinal.viewmodel.UserViewModel
import com.example.lvlupfinal.viewmodel.UserViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getInstance(applicationContext)
        val userDao = database.userDao()
        val userRepository = UserRepository(userDao)
        val userViewModelFactory = UserViewModelFactory(userRepository)
        val userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            AppNavigation(
                navController = navController,
                userViewModel = userViewModel
            )
        }
    }
}
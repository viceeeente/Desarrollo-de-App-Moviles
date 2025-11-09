package com.example.lvlupfinal.ui.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import com.example.lvlupfinal.model.Screen

@Composable
fun MoreOptions(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    onNavigate: (String) -> Unit
) {
    val isLoggedIn by sharedViewModel.isLoggedIn.collectAsState()
    Column(modifier = modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(150.dp))

        Button(onClick = { onNavigate(Screen.About.route) }, modifier = Modifier.fillMaxWidth()) {
            Text("Sobre Nosotros")
        }

        Spacer(modifier = Modifier.height(24.dp))
        if (!isLoggedIn) {
            Button(onClick = { onNavigate(Screen.Login.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("Iniciar sesión")
            }
        } else {
            Button(onClick = { onNavigate(Screen.Account.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("Mi cuenta")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                sharedViewModel.setCurrentUser(null)
                sharedViewModel.setLoggedIn(false)
                sharedViewModel.onBottonNavSelected(Screen.Home.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Cerrar sesión")
            }
        }
    }
}
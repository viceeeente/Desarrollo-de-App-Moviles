package com.example.lvlupfinal.ui.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import com.example.lvlupfinal.model.Screen

@Composable
fun MoreOptions(
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentUser = sharedViewModel.currentUser.collectAsState().value

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = {
            if (currentUser != null) onNavigate(Screen.Account.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Cuenta")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (currentUser != null) onNavigate(Screen.EditProfile.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Editar perfil")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (currentUser != null) onNavigate(Screen.ChangePassword.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Cambiar contraseña")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            onNavigate(Screen.About.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Sobre nosotros")
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (currentUser != null) {
                sharedViewModel.setCurrentUser(null)
                sharedViewModel.setLoggedIn(false)
                onNavigate(Screen.Home.route)
            } else {
                onNavigate(Screen.Login.route)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(if (currentUser != null) "Cerrar sesión" else "Iniciar sesión")
        }

        if (currentUser == null) {
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { onNavigate(Screen.Register.route) }) {
                Text("Si no tienes una cuenta, regístrate")
            }
        }
    }
}
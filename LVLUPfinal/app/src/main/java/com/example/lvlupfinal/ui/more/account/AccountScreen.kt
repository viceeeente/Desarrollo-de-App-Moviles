package com.example.lvlupfinal.ui.more.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.viewmodel.SharedViewModel

@Composable
fun AccountScreen(
    sharedViewModel: SharedViewModel,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = sharedViewModel.currentUser.collectAsState().value

    if (user == null) {
        Column(modifier = modifier.padding(16.dp)) {
            Text("No hay sesión iniciada")
        }
        return
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Nombre: ${user.name}")
        Text("Email: ${user.email}")
        Text("Dirección: ${user.address}")
        Text("Edad: ${user.age}")
        Button(onClick = onEdit, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text("Editar perfil")
        }
    }
}
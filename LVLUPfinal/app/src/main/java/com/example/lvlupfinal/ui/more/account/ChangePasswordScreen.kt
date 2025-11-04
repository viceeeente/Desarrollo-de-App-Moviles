package com.example.lvlupfinal.ui.more.account


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = sharedViewModel.currentUser.collectAsState().value
    val scope = rememberCoroutineScope()
    var current by remember { mutableStateOf("") }
    var nov by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    if (user == null) {
        Text("No hay sesión iniciada", modifier = Modifier.padding(16.dp))
        return
    }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(value = current, onValueChange = { current = it }, label = { Text("Contraseña actual") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = nov, onValueChange = { nov = it }, label = { Text("Nueva contraseña") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = confirm, onValueChange = { confirm = it }, label = { Text("Confirmar nueva") }, modifier = Modifier.fillMaxWidth())

        error?.let { Text(it, modifier = Modifier.padding(top = 8.dp)) }

        Button(onClick = {
            error = null
            if (nov != confirm) { error = "Las contraseñas no coinciden"; return@Button }
            scope.launch {
                val ok = userViewModel.changePasswordIfMatches(user, currentPassword = current, newPassword = nov)
                if (ok) {
                    val refreshed = userViewModel.getUserByIdSusp(user.id)
                    if (refreshed != null) sharedViewModel.setCurrentUser(refreshed)
                    onDone()
                } else {
                    error = "Contraseña actual incorrecta"
                }
            }
        }, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text("Cambiar contraseña")
        }
    }
}
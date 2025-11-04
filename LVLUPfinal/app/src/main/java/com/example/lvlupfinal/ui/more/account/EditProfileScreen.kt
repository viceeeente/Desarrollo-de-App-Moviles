package com.example.lvlupfinal.ui.more.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val user = sharedViewModel.currentUser.collectAsState().value
    val scope = rememberCoroutineScope()

    if (user == null) {
        Text("No hay sesión iniciada", modifier = Modifier.padding(16.dp))
        return
    }

    var name by remember { mutableStateOf(user.name) }
    var age by remember { mutableStateOf(user.age.toString()) }
    var address by remember { mutableStateOf(user.address) }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = age, onValueChange = { age = it.filter { ch -> ch.isDigit() } }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            val newAge = age.toIntOrNull() ?: user.age
            scope.launch {
                userViewModel.updateProfile(user, newName = name.trim(), newAge = newAge, newAddress = address.trim())
                val refreshed = userViewModel.getUserByIdSusp(user.id)
                if (refreshed != null) sharedViewModel.setCurrentUser(refreshed)
                onDone()
            }
        }, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text("Guardar")
        }
    }
}
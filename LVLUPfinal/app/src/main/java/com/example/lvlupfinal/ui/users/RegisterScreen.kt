package com.example.lvlupfinal.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.data.users.User
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Register(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    viewModel: UserViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Registrar",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                if (errorMessage != null) errorMessage = null
            },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                if (errorMessage != null) errorMessage = null
            },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (errorMessage != null) errorMessage = null
            },
            label = { Text("Contraseña") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
                if (errorMessage != null) errorMessage = null
            },
            label = { Text("Dirección") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ageText,
            onValueChange = {
                ageText = it.filter { ch -> ch.isDigit() }
                if (errorMessage != null) errorMessage = null
            },
            label = { Text("Edad") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(
                onClick = {
                    val age = ageText.toIntOrNull() ?: 0

                    when {
                        name.isBlank() -> {
                            errorMessage = "El nombre no puede estar vacío"
                            return@Button
                        }
                        email.isBlank() || !email.contains("@") -> {
                            errorMessage = "Ingrese un email válido"
                            return@Button
                        }
                        password.length < 6 -> {
                            errorMessage = "La contraseña debe tener al menos 6 caracteres"
                            return@Button
                        }
                        address.isBlank() -> {
                            errorMessage = "La dirección no puede estar vacía"
                            return@Button
                        }
                        age <= 0 -> {
                            errorMessage = "Ingresa una edad válida"
                            return@Button
                        }
                        age < 18 -> {
                            errorMessage = "Debes ser mayor o igual a 18 años"
                            return@Button
                        }
                    }

                    isSubmitting = true
                    errorMessage = null

                    scope.launch {
                        val created: User? = viewModel.addUserReturn(
                            name = name.trim(),
                            email = email.trim(),
                            password = password,
                            address = address.trim(),
                            age = age
                        )

                        withContext(Dispatchers.Main) {
                            if (created != null) {
                                sharedViewModel.setCurrentUser(created)
                                sharedViewModel.onBottonNavSelected(Screen.Home.route)
                                sharedViewModel.setLoggedIn(true)
                            } else {
                                errorMessage = "Error al crear o encontrar el usuario"
                            }
                            isSubmitting = false
                        }
                    }
                },
                enabled = !isSubmitting,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }

        if (!errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
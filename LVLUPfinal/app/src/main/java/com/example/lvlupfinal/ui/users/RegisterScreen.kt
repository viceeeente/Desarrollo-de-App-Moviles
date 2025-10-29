package com.example.lvlupfinal.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.data.local.User
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    viewModel: UserViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
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
            text = "Iniciar Sesión",
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
                        val created: User? = viewModel.addUserReturn(name.trim(), age)

                        withContext(Dispatchers.Main) {
                            if (created != null) {
                                sharedViewModel.setCurrentUser(created)
                                sharedViewModel.onBottonNavSelected(Screen.Home.route)
                            } else {
                                errorMessage = "Error al crear el usuario"
                            }
                            isSubmitting = false
                        }
                    }
                },
                enabled = !isSubmitting,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isSubmitting) "Registrando..." else "Registrar / Entrar")
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
package com.example.lvlupfinal.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.model.UserErrors
import com.example.lvlupfinal.model.UserUiState
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel,
    onNavigate: (String) -> Unit
) {
    val currentUser = sharedViewModel.currentUser.collectAsState().value
    var uiState by remember { mutableStateOf(UserUiState()) }
    var isSubmitting by remember { mutableStateOf(false) }
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

        if (currentUser != null) {
            Text("Sesión iniciada como ${currentUser.name}" , modifier = Modifier.align(Alignment.CenterHorizontally))
            Button(
                onClick = {
                    sharedViewModel.setCurrentUser(null)
                    sharedViewModel.setLoggedIn(false)
                    sharedViewModel.onBottonNavSelected(Screen.Home.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
            return@Column
        }

        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                uiState = uiState.copy(email = it, errores = uiState.errores.copy(email = null))
            },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errores.email != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        uiState.errores.email?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
                uiState = uiState.copy(password = it, errores = uiState.errores.copy(password = null))
            },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errores.password != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        uiState.errores.password?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Button(
            onClick = {
                val errores = UserErrors(
                    email = if (uiState.email.isBlank()) "El correo no puede estar vacío" else null,
                    password = if (uiState.password.isBlank()) "La contraseña no puede estar vacía" else null
                )
                if (errores.email != null || errores.password != null) {
                    uiState = uiState.copy(errores = errores)
                    return@Button
                }

                isSubmitting = true
                scope.launch {
                    val email = uiState.email.trim()
                    val password = uiState.password

                    android.util.Log.d("LoginDebug", "Intentando login con: $email / $password")

                    val user = try {
                        withContext(Dispatchers.IO) {
                            userViewModel.getUserByEmailAndPassword(email, password)
                        }
                    } catch (e: Exception) {
                        android.util.Log.e("LoginDebug", "Error al consultar DB", e)
                        null
                    }

                    android.util.Log.d("LoginDebug", "Resultado de login: $user")

                    if (user != null) {
                        sharedViewModel.setCurrentUser(user)
                        sharedViewModel.setLoggedIn(true)
                        sharedViewModel.onBottonNavSelected(Screen.Home.route)
                    } else {
                        uiState = uiState.copy(
                            errores = uiState.errores.copy(email = "Usuario o contraseña incorrectos")
                        )
                    }

                    isSubmitting = false
                }
            },
            enabled = !isSubmitting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSubmitting) "Ingresando..." else "Entrar")
        }
    }
}
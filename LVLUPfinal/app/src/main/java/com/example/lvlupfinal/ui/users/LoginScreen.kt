package com.example.lvlupfinal.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
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

    var passwordVisible by remember { mutableStateOf(false) }

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
            Text(
                "Sesión iniciada como ${currentUser.name}",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    sharedViewModel.setCurrentUser(null)
                    sharedViewModel.setLoginState(false)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconText = if (passwordVisible) "🙈" else "👁️"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(iconText)
                }
            }
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
                        sharedViewModel.setLoginState(true)
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

        if (currentUser == null) {
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = { onNavigate(Screen.Register.route) }
            ) {
                Text("Si no tienes una cuenta, registrate aquí")
            }
        }
    }
}
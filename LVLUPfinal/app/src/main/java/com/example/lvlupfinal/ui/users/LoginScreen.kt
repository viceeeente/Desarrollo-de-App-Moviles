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
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    userViewModel: UserViewModel = viewModel()
){
    var uiState by remember { mutableStateOf(UserUiState()) }
    var isSubmitting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                uiState = uiState.copy(email = it, errores = uiState.errores.copy(email = null))
            },
            label = {Text("Correo electrónico")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errores.email != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (uiState.errores.email != null) {
            Text(
                text = uiState.errores.email!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
                uiState = uiState.copy(password = it, errores = uiState.errores.copy(password = null))
            },
            label = { Text("Contraseña") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.errores.password != null
        )
        if (uiState.errores.password != null) {
            Text(
                text = uiState.errores.password!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick ={
                val errores = UserErrors(
                    email = if(uiState.email.isBlank()) "El correo no puede estar vacío" else null,
                    password = if(uiState.password.isBlank()) "La contraseña no puede estar vacía" else null
                )
                if(errores.email!= null || errores.password!=null) {
                    uiState = uiState.copy(errores = errores)
                    return@Button
                }
                isSubmitting = true

                scope.launch {
                    val user = userViewModel.users.value.find {
                        it.email == uiState.email.trim() && it.password == uiState.password
                    }
                    if(user != null) {
                        sharedViewModel.setCurrentUser(user)
                        sharedViewModel.onBottonNavSelected(Screen.Home.route)
                    }else {
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
            Text(if(isSubmitting) "Ingresando..." else "Entrar")
        }
    }
}
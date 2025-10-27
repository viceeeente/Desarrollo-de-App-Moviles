package com.example.lvlupfinal.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lvlupfinal.viewmodel.SharedViewModel
import com.example.lvlupfinal.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    viewModel: UserViewModel
){
    val state by viewModel.state.collectAsState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =  Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Nombre") },
            isError = state.errores.name != null,
            supportingText = {
                state.errores.name?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Correo Electrónico")},
            isError = state.errores.email!= null,
            supportingText = {
                state.errores.email?.let{
                    Text(it,color= MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            label = {Text("Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            isError = state.errores.password!= null,
            supportingText = {
                state.errores.password?.let{
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.address,
            onValueChange = viewModel::onAddresChange,
            label = {Text("Dirección")},
            isError = state.errores.address!= null,
            supportingText = {
                state.errores.address?.let{
                    Text(it,color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = state.acceptTerms,
                onCheckedChange = viewModel::onAcceptTermns
            )
            Spacer(Modifier.width(8.dp))
            Text("Acepto lo términos y condiciones")
        }

        Button(
            onClick = {
                if(viewModel.formValidation()){
                    sharedViewModel.onBottonNavSelected("home")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
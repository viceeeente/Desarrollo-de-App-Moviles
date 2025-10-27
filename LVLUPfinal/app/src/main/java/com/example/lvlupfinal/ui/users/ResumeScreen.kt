package com.example.lvlupfinal.ui.users

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lvlupfinal.viewmodel.UserViewModel

@Composable
fun ResumeScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: UserViewModel
){
    val state by viewModel.state.collectAsState()

    Column (
        modifier = modifier.padding(all = 16.dp)
    ){
        Text(text = "Resumen del registro", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Nombre: ${state.name}")
        Text(text = "Correo: ${state.email}" )
        Text(text = "Direccion: ${state.address}")
        Text(text = "Contraseña: ${state.password}" )
        Text(text = "Terminos: ${if(state.acceptTerms) "Aceptados" else "No aceptados"}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if(viewModel.formValidation()){
                    navController.navigate(route = "registro")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver")
        }
    }

}
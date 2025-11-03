package com.example.lvlupfinal.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.model.Screen
import com.example.lvlupfinal.viewmodel.SharedViewModel

@Composable
fun MoreOptions(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
){
    val options = listOf(
        "Cuenta",
        "Editar perfil",
        "Cambiar contraseña",
        "Sobre nosotros",
        "Iniciar sesión"
    )
    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(options) { option ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{
                        when(option) {
                            "Iniciar sesión" -> {
                                viewModel.setCurrentUser(null)
                                viewModel.onBottonNavSelected(Screen.Register.route)
                            }
                            else -> {
                                Unit
                            }
                        }
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = MaterialTheme.shapes.medium
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
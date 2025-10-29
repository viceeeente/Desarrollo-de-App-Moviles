package com.example.lvlupfinal.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvlupfinal.viewmodel.SharedViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = viewModel()
) {
    val state by viewModel.homeState.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()

    val user = currentUser

    Column(modifier = modifier.padding(16.dp)) {
        if (currentUser != null) {
            Text(
                text = "Hola, ${user?.name?:""}",
                style = MaterialTheme.typography.headlineSmall
            )
        } else {
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(state.items) { id ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.onItemClick(id) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Item $id",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
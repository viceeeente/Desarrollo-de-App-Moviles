package com.example.lvlupfinal.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lvlupfinal.ui.categories.getCategoryIcon

@Composable
fun CategoryCarousel(
    categories: List<String>,
    onCategorySelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(categories) { category ->
                ElevatedButton(
                    onClick = { onCategorySelected(category) },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(200.dp)
                        .widthIn(min = 160.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = getCategoryIcon(category)),
                            contentDescription = category,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = category,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
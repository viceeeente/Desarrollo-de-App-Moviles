package com.example.lvlupfinal.ui.categories

import androidx.compose.runtime.Composable
import com.example.lvlupfinal.R

@Composable
fun getCategoryIcon(category: String): Int {
    return when (category.lowercase()) {
        "consolas" -> R.drawable.consolaslogo
        "juegos" -> R.drawable.juegoslogo
        "mouse" -> R.drawable.mouselogo
        "mousepad" -> R.drawable.mousepadlogo
        "pc" -> R.drawable.pclogo
        "poleras" -> R.drawable.poleralogo
        "silla gamer" -> R.drawable.sillalogo
        else -> R.drawable.logo
    }
}
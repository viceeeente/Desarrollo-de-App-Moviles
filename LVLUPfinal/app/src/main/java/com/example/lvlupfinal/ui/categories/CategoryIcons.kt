package com.example.lvlupfinal.ui.categories

import com.example.lvlupfinal.R

fun getCategoryIcon(category: String): Int {
    return when (category.lowercase()) {
        "consola" -> R.drawable.consolaslogo
        "juegos de mesa" -> R.drawable.juegoslogo
        "mouse" -> R.drawable.mouselogo
        "alfombrilla" -> R.drawable.mousepadlogo
        "pc" -> R.drawable.pclogo
        "polera" -> R.drawable.poleralogo
        "silla gamer" -> R.drawable.sillalogo
        else -> R.drawable.logo
    }
}
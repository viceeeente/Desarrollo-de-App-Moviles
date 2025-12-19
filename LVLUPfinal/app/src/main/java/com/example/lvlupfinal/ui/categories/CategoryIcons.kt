package com.example.lvlupfinal.ui.categories

import com.example.lvlupfinal.R

fun getCategoryIcon(category: String): Int {
    return when (category.lowercase()) {
        "consolas" -> R.drawable.consolaslogo
        "juegos de mesa" -> R.drawable.juegoslogo
        "mouse" -> R.drawable.mouselogo
        "mousepad" -> R.drawable.mousepadlogo
        "computadores" -> R.drawable.pclogo
        "poleras" -> R.drawable.poleralogo
        "sillas" -> R.drawable.sillalogo
        else -> R.drawable.logo
    }
}
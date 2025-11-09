package com.example.lvlupfinal.products

import com.example.lvlupfinal.R
class ProductRepository {

    val productos = listOf(
        Product("CO001", "PlayStation 5", "Consola", 499990, "Consola de última generación de Sony...", R.drawable.play_station_5_removebg_preview),
        Product("CO002", "Xbox Series X", "Consola", 449990, "La consola más potente de Microsoft...", R.drawable.xbox_series_x_removebg_preview),

        Product("JM001", "Catan", "Juegos de Mesa", 29900, ".", R.drawable.catan),
        Product("JM002", "Carcassonne", "Juegos de Mesa", 24990, "", R.drawable.carcassonne_removebg_preview),

        Product("MS001", "Mouse Logitech G502 HERO", "Mouse", 49990, "Sensor HERO 25K, 11 botones...", R.drawable.mouse_logitech_removebg_preview),
        Product("MS002", "Mouse Razer DeathAdder V2", "Mouse", 29990, "Sensor óptico 8500 dpi, ergonómico...", R.drawable.mouse_razer_removebg_preview),

        Product("MP001", "Mousepad Razer Goliathus", "Alfombrilla", 29990, "Iluminación RGB, superficie optimizada...", R.drawable.mousepad_razer),
        Product("MP002", "Mousepad Corsair MM300", "Alfombrilla", 19990, "Tela resistente, bordes cosidos...", R.drawable.mousepad_corsair_removebg_preview),

        Product("PC001", "PC Gamer Ryzen 7 RTX 4060", "PC", 899990, "Gaming 2K, multitareas exigentes...", R.drawable.pc_amd_removebg_preview),
        Product("PC002", "Notebook ASUS TUF Gaming", "PC", 749990, "Pantalla 144Hz, Ryzen 5, GTX 1650...", R.drawable.pc_asus_removebg_preview),

        Product("PP001", "Polera 'Level Up'", "Poleras", 14990, "", R.drawable.polera1),
        Product("PP002", "Polera 'Game On'", "Poleras", 14990, "", R.drawable.polera2_removebg_preview),

        Product("SG001", "Silla Secretlab TITAN", "Silla Gamer", 349990, "Comodidad, soporte lumbar, 4D...", R.drawable.silla_secret_lab),
        Product("SG002", "Silla DXRacer Formula", "Silla Gamer", 399990, "", R.drawable.silla_dxracer)
    )

    fun getByCategory(categoria: String): List<Product> {
        return productos.filter {
            it.category.trim().lowercase().contains(categoria.trim().lowercase())
        }
    }
}
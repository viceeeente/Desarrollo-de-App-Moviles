package com.example.lvlupfinal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int
)
/*
Aquí tengo que agregar contraseña igual y que sea de más de 6 caracteres
*/
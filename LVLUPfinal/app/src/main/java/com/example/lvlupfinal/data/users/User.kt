package com.example.lvlupfinal.data.users

data class User(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val age: Int
)
package com.example.lvlupfinal.model

data class UserUiState (
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val address: String ="",
    val acceptTerms: Boolean = false,
    val errores: UserErrors = UserErrors()
)

data class UserErrors(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val address: String? = null
)

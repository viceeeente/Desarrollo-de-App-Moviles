package com.example.lvlupfinal.data.users


data class PasswordUpdateRequest(
    val newPassword: String
)

data class PasswordUpdateResponse(
    val success: Boolean
)
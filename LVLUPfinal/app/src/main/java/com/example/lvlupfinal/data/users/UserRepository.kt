package com.example.lvlupfinal.data.users

import com.example.lvlupfinal.data.RetrofitInstance

class UserRepository {

    suspend fun getUsers(): List<User> {
        return RetrofitInstance.userApi.getUsers()
    }

    suspend fun createUser(user: User) {
        RetrofitInstance.userApi.createUser(user)
    }

    suspend fun deleteUser(id: Int) {
        RetrofitInstance.userApi.deleteUser(id)
    }

    suspend fun updatePassword(id: Int, newPassword: String): Boolean {
        return try {
            val response = RetrofitInstance.userApi.updatePassword(id, PasswordUpdateRequest(newPassword))
            response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getUserById(id: Int): User {
        return RetrofitInstance.userApi.getUserById(id)
    }

    // ✅ Nuevo: actualizar perfil
    suspend fun updateUserProfile(id: Int, name: String, age: Int, address: String): User {
        return RetrofitInstance.userApi.updateUserProfile(
            id,
            UserProfileUpdateRequest(name, age, address)
        )
    }
}
package com.example.lvlupfinal.data.users

import com.example.lvlupfinal.data.RetrofitInstance

class UserRepository {
    suspend fun getUsers() = RetrofitInstance.userApi.getUsers()
    suspend fun createUser(user: User) = RetrofitInstance.userApi.createUser(user)
    suspend fun deleteUser(id: Int) = RetrofitInstance.userApi.deleteUser(id)
}
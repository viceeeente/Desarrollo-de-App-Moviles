package com.example.lvlupfinal.data.users

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Body
import retrofit2.http.Path

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}
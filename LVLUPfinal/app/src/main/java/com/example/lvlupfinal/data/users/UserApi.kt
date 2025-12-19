package com.example.lvlupfinal.data.users

import retrofit2.http.*

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: User)

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)

    // ✅ Nuevo: actualizar contraseña
    @PUT("users/{id}/password")
    suspend fun updatePassword(
        @Path("id") id: Int,
        @Body request: PasswordUpdateRequest
    ): PasswordUpdateResponse

    // ✅ Nuevo: obtener usuario por ID
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @PUT("users/{id}/profile")
    suspend fun updateUserProfile(
        @Path("id") id: Int,
        @Body request: UserProfileUpdateRequest
    ): User
}
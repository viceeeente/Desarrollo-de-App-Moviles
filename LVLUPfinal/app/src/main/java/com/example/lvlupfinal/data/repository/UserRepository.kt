package com.example.lvlupfinal.data.repository

import com.example.lvlupfinal.data.local.User
import com.example.lvlupfinal.data.local.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val users: Flow<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User): Long = userDao.insertUser(user)
    suspend fun update(user: User) = userDao.updateUser(user)
    suspend fun delete(user: User) = userDao.deleteUser(user)

    suspend fun getUserById(id: Int): User? = userDao.getUserById(id)
    suspend fun getLastUser(): User? = userDao.getLastUser()
}
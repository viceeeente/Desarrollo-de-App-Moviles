package com.example.lvlupfinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvlupfinal.data.local.User
import com.example.lvlupfinal.data.repository.UserRepository
import com.example.lvlupfinal.model.UserErrors
import com.example.lvlupfinal.model.UserUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(UserUiState())

    val state: StateFlow<UserUiState> = _state

    val users = repository.users.stateIn(
        viewModelScope,
        SharingStarted.Companion.WhileSubscribed(),
        emptyList()
    )

    fun addUser(name: String, email: String, password: String, address: String, age: Int) {
        viewModelScope.launch {
            repository.insert(
                User(
                    name = name,
                    email = email,
                    password = password,
                    address = address,
                    age = age
                )
            )
        }
    }



    suspend fun addUserReturn(
        name: String,
        email: String,
        password: String,
        address: String,
        age: Int
    ): User? {
        return withContext(Dispatchers.IO) {
            try {
                val newUser = User(
                    name = name,
                    email = email,
                    password = password,
                    address = address,
                    age = age
                )
                val id = repository.insert(newUser)
                android.util.Log.d("UserVM", "insert returned id=$id for user=$newUser")
                if (id > 0L) {
                    val byId = repository.getUserById(id.toInt())
                    android.util.Log.d("UserVM", "getUserById returned $byId")
                    repository.getUserById(id.toInt()) ?: repository.getLastUser()
                } else {
                    android.util.Log.e("UserVM", "insert returned id <= 0")
                    null
                }
            } catch (e: Exception) {
                android.util.Log.e("UserVM", "addUserReturn exception", e)
                null
            }
        }
    }



    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.delete(user)
        }
    }

    fun updateUser(user: User, newName: String, newAge: Int) {
        viewModelScope.launch {
            repository.update(user.copy(name=newName,age=newAge))
        }
    }

    fun onNameChange(value: String) {
        _state.update { it.copy(name = value, errores = it.errores.copy(name = null)) }
    }

    fun onEmailChange(value: String){
        _state.update { it.copy(email = value, errores = it.errores.copy(email = null)) }
    }

    fun onPasswordChange(value: String){
        _state.update { it.copy(password = value, errores = it.errores.copy(password = null)) }
    }

    fun onAddresChange(value: String){
        _state.update { it.copy(address = value, errores = it.errores.copy(address = null)) }
    }

    fun onAcceptTermns(value: Boolean){
        _state.update { it.copy(acceptTerms = value) }
    }

    fun validateForLogin(): Boolean {
        val s = _state.value
        val errors = UserErrors(
            name = null,
            email = if (s.email.isBlank() || !s.email.contains("@")) "Correo inválido" else null,
            password = if (s.password.isBlank()) "La contraseña no puede estar vacía" else null,
            address = null
        )
        _state.update { it.copy(errores = errors) }
        return listOfNotNull(errors.email, errors.password).isEmpty()
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            try {
                repository.getByEmailAndPassword(email.trim(), password)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun setLoginError(message: String) {
        _state.update { it.copy(errores = it.errores.copy(email = message)) }
    }

}
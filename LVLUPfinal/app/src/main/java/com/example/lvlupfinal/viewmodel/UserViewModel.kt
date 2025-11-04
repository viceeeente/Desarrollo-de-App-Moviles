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
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    suspend fun addUserReturn(
        name: String,
        email: String,
        password: String,
        address: String,
        age: Int
    ): User? = withContext(Dispatchers.IO) {
        try {
            val newUser = User(
                name = name,
                email = email,
                password = password,
                address = address,
                age = age
            )
            val id = repository.insert(newUser)
            if (id > 0L) {
                repository.getUserById(id.toInt()) ?: repository.getLastUser()
            } else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                repository.getByEmailAndPassword(email.trim(), password)
            } catch (e: Exception) {
                null
            }
        }

    suspend fun getUserByIdSusp(id: Int): User? = withContext(Dispatchers.IO) {
        try {
            repository.getUserById(id)
        } catch (e: Exception) {
            null
        }
    }

    fun updateProfile(user: User, newName: String, newAge: Int, newAddress: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val updated = user.copy(
                name = newName,
                age = newAge,
                address = newAddress ?: user.address
            )
            repository.update(updated)
        }
    }

    suspend fun changePasswordIfMatches(user: User, currentPassword: String, newPassword: String): Boolean =
        withContext(Dispatchers.IO) {
            if (user.password != currentPassword) return@withContext false
            val updated = user.copy(password = newPassword)
            repository.update(updated)
            true
        }

    fun onNameChange(value: String) {
        _state.update { it.copy(name = value, errores = it.errores.copy(name = null)) }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value, errores = it.errores.copy(email = null)) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value, errores = it.errores.copy(password = null)) }
    }

    fun onAddresChange(value: String) {
        _state.update { it.copy(address = value, errores = it.errores.copy(address = null)) }
    }

    fun onAcceptTermns(value: Boolean) {
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

    fun setLoginError(message: String) {
        _state.update { it.copy(errores = it.errores.copy(email = message)) }
    }
}
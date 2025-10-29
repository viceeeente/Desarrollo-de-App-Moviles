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

    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            repository.insert(User(name=name,age=age))
        }
    }

    suspend fun addUserReturn(name: String, age: Int): User? {
        return withContext(Dispatchers.IO) {
            try {
                val id = repository.insert(User(name = name, age = age))
                if (id > 0L) {
                    repository.getUserById(id.toInt()) ?: repository.getLastUser()
                } else {
                    null
                }
            } catch (e: Exception) {
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

    fun formValidation(): Boolean {
        val actualState = _state.value
        val errors = UserErrors(
            name = if(actualState.name.isBlank()) "Campo obligatorio" else null,
            email = if(!actualState.email.contains("@")) "Correo invalido, falta @" else null,
            password = if (actualState.password.length < 6) "La contraseña debe tener al menos 6 carácteres" else null,
            address = if(actualState.address.isBlank())"Campo obligatorio" else null
        )

        val thereAreErrors = listOfNotNull(
            errors.name,
            errors.password,
            errors.address
        ).isNotEmpty()

        _state.update { it.copy(errores = errors) }

        return !thereAreErrors
    }
}
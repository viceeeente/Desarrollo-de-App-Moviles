import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvlupfinal.data.users.User
import com.example.lvlupfinal.data.users.UserRepository
import com.example.lvlupfinal.model.UserErrors
import com.example.lvlupfinal.model.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(UserUiState())
    val state: StateFlow<UserUiState> = _state

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val result = repository.getUsers()
                _users.value = result
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }

    fun addUser(name: String, email: String, password: String, address: String, age: Int) {
        viewModelScope.launch {
            try {
                val newUser = User(
                    name = name,
                    email = email,
                    password = password,
                    address = address,
                    age = age
                )
                repository.createUser(newUser)
                loadUsers() // refresca lista
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteUser(id)
                loadUsers()
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }

    // Mantén tus funciones de validación de UIState (onNameChange, onEmailChange, etc.)
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
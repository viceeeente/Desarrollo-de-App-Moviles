package com.example.lvlupfinal.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvlupfinal.data.local.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Estado de la pantalla Home (lista de items de ejemplo)
data class HomeUiState(
    val items: List<String> = listOf("1", "2", "3")
)
// ViewModel compartido entre Home y Detail
// Nos permite mantener y compartir el estado de navegación y de selección de items
class SharedViewModel : ViewModel() {
    // -------------------------
// Estado de la pantalla Home
// -------------------------
// Estado interno (privado y mutable)
    private val _homeState = MutableStateFlow(HomeUiState())
    // Estado expuesto (público y solo lectura)
    val homeState: StateFlow<HomeUiState> = _homeState.asStateFlow()
    // -------------------------
// Estado de navegación (BottomNavigation)
// -------------------------
// Pantalla actual (por defecto "home")
    private val _currentScreen = MutableStateFlow<String>("home")
    // Expuesto a la UI (solo lectura)
    val currentScreen: StateFlow<String> = _currentScreen.asStateFlow()
    // -------------------------
// Estado del item seleccionado (para Detail)
// -------------------------
// Guardamos el ID del item seleccionado (puede ser null si no hay selección)
    private val _selectedItemId = MutableStateFlow<String?>(null)
    // Expuesto a la UI (solo lectura)
    val selectedItemId: StateFlow<String?> = _selectedItemId.asStateFlow()
// -------------------------
// Eventos / Acciones del usuario
// -------------------------
    /**
     * Cuando el usuario hace clic en un item de la lista en Home:
     * - Guardamos el id del item seleccionado
     * - Cambiamos la pantalla a "detail"
     */
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    // Estado mínimo para el usuario activo
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun onItemClick(id: String) {
        _selectedItemId.value = id
        _currentScreen.value = "detail"
    }

    fun onBottonNavSelected(route: String) {
        _currentScreen.value = route
    }

    fun setLoggedIn(value: Boolean) {
        _isLoggedIn.value = value
    }

    fun setCurrentUser(user: User?) {
        android.util.Log.d("SharedVM","setCurrentUser llamado con: $user")
        _currentUser.value = user }

    fun setCurrentUserFromRepo(load: suspend () -> User?) {
        viewModelScope.launch {
            val user = load()
            _currentUser.value = user
            _isLoggedIn.value = user != null
        }
    }



}
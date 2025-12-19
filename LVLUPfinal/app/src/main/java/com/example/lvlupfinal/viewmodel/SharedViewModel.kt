package com.example.lvlupfinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.lvlupfinal.data.RetrofitInstance
import com.example.lvlupfinal.data.users.User
import com.example.lvlupfinal.data.products.Product
import com.example.lvlupfinal.model.UserErrors
import com.example.lvlupfinal.model.UserUiState
import com.example.lvlupfinal.model.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    // ✅ filtros por categoría usando category.name
    val productosConsola: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Consola", ignoreCase = true) }
    }

    val productosJuegos: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Juegos de Mesa", ignoreCase = true) }
    }

    val productosMouse: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Mouse", ignoreCase = true) }
    }

    val productosMousepad: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Alfombrilla", ignoreCase = true) }
    }

    val productosPC: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("PC", ignoreCase = true) }
    }

    val productosPolera: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Polera", ignoreCase = true) }
    }

    val productosSillaGamer: LiveData<List<Product>> = _products.map { list ->
        list.filter { p -> p.category.name.equals("Silla Gamer", ignoreCase = true) }
    }

    // --- Estado de login ---
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun setLoginState(loggedIn: Boolean) {
        _isLoggedIn.value = loggedIn
    }

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun logout() {
        _currentUser.value = null
        _isLoggedIn.value = false
        limpiarCarrito()
        onBottonNavSelected(Screen.Home.route)
    }

    // --- Carrito ---
    private val _carrito = MutableStateFlow<List<Product>>(emptyList())
    val carrito: StateFlow<List<Product>> = _carrito

    fun agregarAlCarrito(producto: Product) {
        _carrito.value = _carrito.value + producto
    }

    fun eliminarDelCarrito(producto: Product) {
        _carrito.value = _carrito.value.filter { it.id != producto.id }
    }

    fun limpiarCarrito() {
        _carrito.value = emptyList()
    }

    // --- Estado UI ---
    private val _userUiState = MutableLiveData(UserUiState())
    val userUiState: LiveData<UserUiState> = _userUiState

    private val _currentScreen = MutableStateFlow(Screen.Home.route)
    val currentScreen: StateFlow<String> = _currentScreen

    fun onBottonNavSelected(route: String) {
        _currentScreen.value = route
    }

    // --- Operaciones con usuarios ---
    fun loadUsers() {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.userApi.getUsers()
                _users.postValue(result)
            } catch (e: Exception) {
                _users.postValue(emptyList())
            }
        }
    }

    // --- Operaciones con productos ---
    fun loadProducts() {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.productApi.getProducts()
                _products.postValue(result)
            } catch (e: Exception) {
                _products.postValue(emptyList())
            }
        }
    }
}
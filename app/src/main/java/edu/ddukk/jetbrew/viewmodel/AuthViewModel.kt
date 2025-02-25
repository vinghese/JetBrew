package edu.ddukk.jetbrew.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.ddukk.jetbrew.repository.AuthRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import  edu.ddukk.jetbrew.model.User

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _loginSuccess

    private val _registrationSuccess = mutableStateOf(false)
    val registrationSuccess: State<Boolean> = _registrationSuccess

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(User(email, password))
            _registrationSuccess.value = true
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginSuccess.value = repository.loginUser(email, password)
        }
    }
}

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}
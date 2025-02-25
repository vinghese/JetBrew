package edu.ddukk.jetbrew.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.ddukk.jetbrew.repository.AuthRepository
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import  edu.ddukk.jetbrew.model.User
import edu.ddukk.jetbrew.repository.AuthRepositoryHilt
import javax.inject.Inject

@HiltViewModel
class AuthViewModelHilt @Inject constructor(private val repository: AuthRepositoryHilt) : ViewModel() {
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
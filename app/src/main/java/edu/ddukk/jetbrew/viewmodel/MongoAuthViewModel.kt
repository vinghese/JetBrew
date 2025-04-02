package edu.ddukk.jetbrew.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import edu.ddukk.jetbrew.model.MongoAuthResponse
import edu.ddukk.jetbrew.model.MongoUser
import edu.ddukk.jetbrew.repository.MongoAuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MongoAuthViewModel @Inject constructor(private val repository: MongoAuthRepository) : ViewModel() {
    var authResponse by mutableStateOf<MongoAuthResponse?>(null)
        private set

    private val _authEvent = MutableLiveData<String?>()
    val authEvent: LiveData<String?> get() = _authEvent

    fun loginWithMongoDB(email: String, password: String, onAuthSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(MongoUser(email, password))
            Log.d("Server -<>>>>>>>>>>>>>>>>>>>>>>>><", response.toString())
            authResponse = response
            if (response.success) {
                _authEvent.postValue("Login Successful")
                onAuthSuccess(response.message)
            }
            else{
                _authEvent.postValue("Login Failed: ${response.message}")
            }
        }
    }

    fun registerWithMongoDB(email: String, password: String, onAuthSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val response = repository.register(MongoUser(email, password))
            authResponse = response
            if (response.success) {
                _authEvent.postValue("Registration Successful")
                onAuthSuccess(response.message)
            }else{
                _authEvent.postValue("Registration Failed: ${response.message}")
            }
        }
    }
}
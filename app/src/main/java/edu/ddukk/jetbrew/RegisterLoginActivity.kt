package edu.ddukk.jetbrew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import edu.ddukk.jetbrew.room.AppDatabase
import edu.ddukk.jetbrew.model.User
import edu.ddukk.jetbrew.repository.AuthRepository
import edu.ddukk.jetbrew.repository.AuthRepositoryHilt
import edu.ddukk.jetbrew.ui.theme.JetBrewTheme
import edu.ddukk.jetbrew.viewmodel.AuthViewModel
import edu.ddukk.jetbrew.viewmodel.AuthViewModelFactory
import edu.ddukk.jetbrew.viewmodel.AuthViewModelHilt
import edu.ddukk.jetbrew.dao.UserDao as UserDao1

lateinit var innerPaddingGlobal: PaddingValues

@AndroidEntryPoint
class RegisterLoginActivity : ComponentActivity() {

    private val db by lazy { AppDatabase.getDatabase(applicationContext) }
    private val repository by lazy { AuthRepository(db.userDao()) }
    private val viewModel: AuthViewModel by viewModels { AuthViewModelFactory(repository) }

    private val viewModelHilt: AuthViewModelHilt by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        try {


            setContent {
                JetBrewTheme {
                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text("Register/Login (ROOM)") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                        )
                    }) {
//                    innerPadding ->
//                    innerPaddingGlobal= innerPadding
//                    AuthScreen(viewModel, innerPadding)
//                    AuthScreen(viewModel) {
                        AuthScreen(viewModelHilt) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun AuthScreen(viewModel: AuthViewModel?, innerPadding: PaddingValues?) {
//fun AuthScreen(viewModel: AuthViewModel?, onLoginSuccess: () -> Unit) {
fun AuthScreen(viewModel: AuthViewModelHilt?, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("anu@gmail.com") }
    var password by remember { mutableStateOf("123") }
    var isLogin by remember { mutableStateOf(true) }


    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Register/Login", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment
                .CenterHorizontally
        ) {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(onClick = {
                if (isLogin) viewModel?.loginUser(email, password)
                else viewModel?.registerUser(email, password)
            }) {
                Text(if (isLogin) "Login" else "Register", modifier = Modifier.wrapContentSize(), textAlign = TextAlign.Center)
            }

            TextButton(onClick = { isLogin = !isLogin }) {
                Text(if (isLogin) "Don't have an account? Register" else "Already have an account? Login")
            }

            if (viewModel?.loginSuccess?.value == true) {
                Text("Login Successful!", color = MaterialTheme.colorScheme.primary)
                Toast.makeText(LocalContext.current, "Login Successful!", Toast.LENGTH_SHORT).show()
                onLoginSuccess()
            }
            if (viewModel?.registrationSuccess!!.value) {
                Text("Registration Successful!", color = MaterialTheme.colorScheme.secondary)
                Toast.makeText(LocalContext.current, "Registration Successfyl", Toast.LENGTH_LONG).show()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AuthScreenPreview() {
//    AuthScreen(
//        viewModel = AuthViewModel(AuthRepository(object : UserDao1 {
//            override suspend fun insertUser(user: User) {}
//            override suspend fun getUser(email: String, password: String): User? = null
//        }))
//    ) {}
//}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    val repository = AuthRepositoryHilt(object : UserDao1 {
        override suspend fun insertUser(user: User) {}
        override suspend fun getUser(email: String, password: String): User? = null
    })
    val viewModel = AuthViewModelHilt(repository)
    AuthScreen(viewModel, onLoginSuccess = {})
}

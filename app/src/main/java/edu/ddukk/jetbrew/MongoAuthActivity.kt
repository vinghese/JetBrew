package edu.ddukk.jetbrew

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import edu.ddukk.jetbrew.api.MongoAuthApi
import edu.ddukk.jetbrew.model.MongoAuthResponse
import edu.ddukk.jetbrew.model.MongoUser
import edu.ddukk.jetbrew.repository.MongoAuthRepository
import edu.ddukk.jetbrew.ui.theme.JetBrewTheme
import edu.ddukk.jetbrew.viewmodel.MongoAuthViewModel

@AndroidEntryPoint
class MongoAuthActivity : ComponentActivity() {

    private val viewModel: MongoAuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MongoAuthScreen(viewModel) { message ->
                Log.d("Activity ---------->>>>>>>>>>>>", message.toString())
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MongoAuthScreen(viewModel: MongoAuthViewModel, onAuthSuccess: (String?) -> Unit) {
    var email by remember { mutableStateOf("admin@gmail.com") }
    var password by remember { mutableStateOf("....") }
    val context = LocalContext.current

    val authEvent by viewModel.authEvent.observeAsState()

    LaunchedEffect(authEvent) {
        authEvent?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                viewModel.loginWithMongoDB(email, password, onAuthSuccess)
            }) {
                Text("Login")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                viewModel.registerWithMongoDB(email, password, onAuthSuccess)
            }) {
                Text("Register")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MongoAuthScreenPreview() {
    MongoAuthScreen(viewModel = MongoAuthViewModel(MongoAuthRepository(object : MongoAuthApi {
        override suspend fun login(user: MongoUser): MongoAuthResponse {
            return MongoAuthResponse(success = true, message = "Mock Login Success")
        }

        override suspend fun register(user: MongoUser): MongoAuthResponse {
            return MongoAuthResponse(success = true, message = "Mock Registration Success")
        }
    }))) {}
}
package edu.ddukk.jetbrew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ddukk.jetbrew.ui.theme.JetBrewTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetBrewTheme {
                JetpackDemo()
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun JetpackDemo(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var onSelected by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Jetpack Compose Demo's", color = Color.White)
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Button(onClick = { onSelected = "LayoutDemoActivity" }) { Text("Layout Demo") }
                }
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Button(onClick = { onSelected = "RegisterLogin" }) { Text("Register Login") }
                }
//                Button(onClick = { onSelected = "Row" }) { Text("Row") }
//                Button(onClick = { onSelected = "Box" }) { Text("Box") }
//                Button(onClick = { onSelected = "LazyColumn" }) { Text("LazyColumn") }
//                Button(onClick = { onSelected = "Constraint" }) { Text("Constraint") }
            }




            Spacer(modifier = Modifier.height(16.dp))
            when (onSelected) {
                "LayoutDemoActivity" -> {
                    val intent = Intent(context, LayoutActivityDemo::class.java)
                    context.startActivity(intent)
                }
                "RegisterLogin" -> {

                    val intent = Intent(context,RegisterLoginActivity::class.java)
                    context.startActivity(intent)
                }
                "Box" -> BoxLayoutDemo()
                "LazyColumn" -> LazyColumnDemo()
                "Constraint" -> ConstraintLayoutDemo()
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetBrewTheme {
        JetpackDemo()
    }
}
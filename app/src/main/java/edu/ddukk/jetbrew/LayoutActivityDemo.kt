package edu.ddukk.jetbrew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

import edu.ddukk.jetbrew.ui.theme.JetBrewTheme

class LayoutActivityDemo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetBrewTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Layout Demo in JetPack Compose",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                LayoutDemoApp()
//                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LayoutDemoApp() {
    var selectedLayout by remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Compose Layouts") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary))
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement
            = Arrangement.spacedBy(8.dp)){
                Button(onClick = { selectedLayout = "Column" }) { Text("Column") }
                Button(onClick = { selectedLayout = "Row" }) { Text("Row") }
                Button(onClick = { selectedLayout = "Box" }) { Text("Box") }
                Button(onClick = { selectedLayout = "LazyColumn" }) { Text("LazyColumn") }
                Button(onClick = { selectedLayout = "Constraint" }) { Text("Constraint") }
                Button(onClick = { selectedLayout = "Card" }) { Text("Card") }
            }
            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = "Jetpack Compose Layouts", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            when (selectedLayout) {
                "Column" -> ColumnLayoutDemo()
                "Row" -> RowLayoutDemo()
                "Box" -> BoxLayoutDemo()
                "LazyColumn" -> LazyColumnDemo()
                "Constraint" -> ConstraintLayoutDemo()
                "Card" -> CardLayoutDemo()
            }

//            Spacer(modifier = Modifier.height(16.dp))
//            ColumnLayoutDemo()
//            Spacer(modifier = Modifier.height(16.dp))
//            RowLayoutDemo()
//            Spacer(modifier = Modifier.height(16.dp))
//            BoxLayoutDemo()
//            Spacer(modifier = Modifier.height(16.dp))
//            LazyColumnDemo()
//            Spacer(modifier = Modifier.height(16.dp))
//            ConstraintLayoutDemo()
        }
    }
}


@Composable
fun ColumnLayoutDemo() {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Column Layout:")
            repeat(3) { Text(text = "Item $it") }
        }
    }
}

@Composable
fun RowLayoutDemo() {
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Row 1")
            Text(text = "Row 2")
            Text(text = "Row 3")
        }
    }
}

@Composable
fun BoxLayoutDemo() {
    Surface(
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)) {
            Text(
                text = "Box Layout (centered)",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun LazyColumnDemo() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight(0.3f)) {
            items(5) { index ->
                Text(
                    text = "Lazy Item $index",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ConstraintLayoutDemo() {
    Surface(
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (text1, text2) = createRefs()
            Text(
                text = "Constraint 1",
                modifier = Modifier.constrainAs(text1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
            Text(
                text = "Constraint 2",
                modifier = Modifier.constrainAs(text2) {
                    top.linkTo(text1.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                })
        }
    }
}

@Composable
fun CardLayoutDemo() {
    Surface(color = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Card Layout", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "This is an example of a Card in Jetpack Compose.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLayoutDemoApp() {
    LayoutDemoApp()
}


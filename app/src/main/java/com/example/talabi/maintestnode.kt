package com.example.talabi
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    var message by remember { mutableStateOf("Loading...") }

    // Fetch data from the backend
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            message = NetworkService.fetchMessage()
        }
    }

    // UI
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Node.js + Kotlin Jetpack Compose") })
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Message from Backend:")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = message)
            }
        }
    }
}
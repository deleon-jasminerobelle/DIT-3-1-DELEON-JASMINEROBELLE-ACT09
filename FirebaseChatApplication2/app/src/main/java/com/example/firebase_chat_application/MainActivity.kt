package com.example.firebase_chat_application  // Adjust to match your namespace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

// Mock data for chat
data class MockMessage(
    val sender: String,
    val text: String,
    val timestamp: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)  // <-- ADD THIS TO SUPPRESS WARNING
@Composable
fun ChatApp() {
    val navController = rememberNavController()
    var userEmail by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLogin = { email ->
                userEmail = email
                navController.navigate("chat")
            })
        }
        composable("chat") {
            ChatScreen(userEmail = userEmail, onLogout = {
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)  // <-- ADD THIS IF NEEDED (or keep on ChatApp)
@Composable
fun LoginScreen(onLogin: (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLogin(email)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)  // <-- ADD THIS IF NEEDED (or keep on ChatApp)
@Composable
fun ChatScreen(userEmail: String, onLogout: () -> Unit) {
    var messages by remember {
        mutableStateOf(
            listOf(
                MockMessage("Alice", "Hello!", getCurrentTimestamp()),
                MockMessage("Bob", "Hi there!", getCurrentTimestamp())
            )
        )
    }
    var newMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Text("Logout")  // Simple text button; replace with Icon if needed
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(messages) { msg ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "${msg.sender}: ${msg.text}")
                            Text(text = msg.timestamp, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newMessage,
                    onValueChange = { newMessage = it },
                    label = { Text("Type a message...") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (newMessage.isNotEmpty()) {
                        messages = messages + MockMessage(userEmail, newMessage, getCurrentTimestamp())
                        newMessage = ""
                    }
                }) {
                    Text("Send")
                }
            }
        }
    }
}

fun getCurrentTimestamp(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return sdf.format(Date())
}
package com.example.logincalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.logincalculatorapp.ui.theme.LoginCalculatorAppTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: ""
        setContent {
            LoginCalculatorAppTheme {
                CalculatorScreen(username)
            }
        }
    }
}

@Composable
fun CalculatorScreen(username: String) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido, $username",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                val n1 = num1.toFloatOrNull() ?: 0f
                val n2 = num2.toFloatOrNull() ?: 0f
                result = (n1 + n2).toString()
            }) {
                Text("+")
            }
            Button(onClick = {
                val n1 = num1.toFloatOrNull() ?: 0f
                val n2 = num2.toFloatOrNull() ?: 0f
                result = (n1 - n2).toString()
            }) {
                Text("-")
            }
            Button(onClick = {
                val n1 = num1.toFloatOrNull() ?: 0f
                val n2 = num2.toFloatOrNull() ?: 0f
                result = (n1 * n2).toString()
            }) {
                Text("×")
            }
            Button(onClick = {
                val n1 = num1.toFloatOrNull()
                val n2 = num2.toFloatOrNull()
                result = if (n1 != null && n2 != null && n2 != 0f) {
                    (n1 / n2).toString()
                } else {
                    "Error"
                }
            }) {
                Text("÷")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = result,
            onValueChange = {},
            label = { Text("Resultado") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            activity?.finish()
        }) {
            Text("Salir")
        }
    }
}

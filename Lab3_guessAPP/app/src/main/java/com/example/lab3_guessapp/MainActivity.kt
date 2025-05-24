package com.example.lab3_guessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                GuessNumberGameWithTimer()
            }
        }
    }
}

@Composable
fun GuessNumberGameWithTimer() {
    var guess by remember { mutableStateOf("") }
    var target by remember { mutableIntStateOf(Random.nextInt(0, 101)) }
    var message by remember { mutableStateOf("Tiene 3 intentos para adivinar") }
    var attempts by remember { mutableIntStateOf(3) }
    var finished by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableIntStateOf(60) }

    // Temporizador de 60 segundos
    LaunchedEffect(key1 = timeLeft, key2 = finished) {
        if (timeLeft > 0 && !finished) {
            delay(1000)
            timeLeft--
        } else if (timeLeft == 0 && !finished) {
            message = "⏰ Tiempo agotado. El número era $target"
            finished = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text("Juego de Adivinar\nel Número", fontSize = 24.sp, lineHeight = 28.sp)
        Spacer(modifier = Modifier.height(12.dp))

        Text("⏳ Tiempo restante: $timeLeft segundos")
        Text("🔁 Intentos restantes: $attempts")

        Spacer(modifier = Modifier.height(20.dp))

        Text(message, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Número a Adivinar") },
            enabled = !finished
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val number = guess.toIntOrNull()
                guess = "" // Limpia el campo siempre

                if (number == null) {
                    message = "Por favor ingrese un número válido"
                    return@Button
                }

                if (number == target) {
                    message = "🎉 ¡Correcto! Has adivinado el número"
                    finished = true
                } else {
                    attempts--
                    if (attempts == 0) {
                        message = "❌ Se acabaron los intentos. El número era $target"
                        finished = true
                    } else {
                        message = if (number < target) "El número es mayor" else "El número es menor"
                    }
                }
            },
            enabled = !finished && timeLeft > 0,
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Adivinar")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                guess = ""
                target = Random.nextInt(0, 101)
                message = "Tiene 3 intentos para adivinar"
                attempts = 3
                finished = false
                timeLeft = 60
            },
            modifier = Modifier.fillMaxWidth(0.6f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text("Reiniciar")
        }
    }
}

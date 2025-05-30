package com.example.frase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContadorDeLetrasScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContadorDeLetrasScreen(modifier: Modifier = Modifier) {
    var frase by remember { mutableStateOf("") }
    var cantidadLetras by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = frase,
            onValueChange = { frase = it },
            label = { Text("Ingresa una frase") }
        )

        Button(onClick = {
            cantidadLetras = frase.count { it.isLetter() }
        }) {
            Text("Contar letras")
        }

        cantidadLetras?.let {
            Text(
                text = "Cantidad de letras: $it",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorDeLetrasPreview() {
    MaterialTheme {
        ContadorDeLetrasScreen()
    }
}
